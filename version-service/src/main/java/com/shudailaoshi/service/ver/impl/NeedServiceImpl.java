package com.shudailaoshi.service.ver.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shudailaoshi.dao.ver.NeedDao;
import com.shudailaoshi.entity.enums.StatusEnum;
import com.shudailaoshi.entity.ver.Need;
import com.shudailaoshi.pojo.exceptions.ExceptionEnum;
import com.shudailaoshi.pojo.exceptions.ServiceException;
import com.shudailaoshi.pojo.query.ver.NeedQuery;
import com.shudailaoshi.pojo.vos.common.PageVO;
import com.shudailaoshi.service.base.impl.BaseServiceImpl;
import com.shudailaoshi.service.ver.NeedService;
import com.shudailaoshi.utils.DateUtil;

@Service("needService")
public class NeedServiceImpl extends BaseServiceImpl<Need> implements NeedService {

	@Autowired
	private NeedDao needDao;

	@Override
	public PageVO pageNeed(NeedQuery needQuery, Integer start, Integer limit) {
		return new PageVO(start, limit, this.needDao.countNeed(needQuery), this.needDao.listNeed(needQuery, start, limit));
	}

	@Override
	public void saveNeed(Need need) {
		long time = DateUtil.getTime();
		need.setCreateTime(time);
		need.setModifyTime(time);
		need.setStatus(StatusEnum.NORMAL.getValue());
		this.needDao.save(need);
	}
	
	@Override
	public void updateNeed(Need need) {
		need.setModifyTime(DateUtil.getTime());
		this.needDao.updateByPrimaryKeySelective(need);
	}

	@Override
	public void removeNeed(long id) {
		try {
			this.needDao.removeByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceException(ExceptionEnum.USERD_NOT_ALLOW_DELETE);
		}
	}
	
	@Override
	public void freeze(long id) {
		Need need = new Need();
		need.setId(id);
		need.setModifyTime(DateUtil.getTime());
		need.setStatus(StatusEnum.FREEZE.getValue());
		this.needDao.updateByPrimaryKeySelective(need);
	}

	@Override
	public void unfreeze(long id) {
		Need need = new Need();
		need.setId(id);
		need.setModifyTime(DateUtil.getTime());
		need.setStatus(StatusEnum.NORMAL.getValue());
		this.needDao.updateByPrimaryKeySelective(need);
	}

}
