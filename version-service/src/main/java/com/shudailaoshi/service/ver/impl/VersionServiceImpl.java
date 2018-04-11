package com.shudailaoshi.service.ver.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shudailaoshi.dao.ver.VersionDao;
import com.shudailaoshi.entity.enums.StatusEnum;
import com.shudailaoshi.entity.ver.Version;
import com.shudailaoshi.pojo.exceptions.ExceptionEnum;
import com.shudailaoshi.pojo.exceptions.ServiceException;
import com.shudailaoshi.pojo.query.ver.VersionQuery;
import com.shudailaoshi.pojo.vos.common.PageVO;
import com.shudailaoshi.service.base.impl.BaseServiceImpl;
import com.shudailaoshi.service.ver.VersionService;
import com.shudailaoshi.utils.DateUtil;

@Service("versionService")
public class VersionServiceImpl extends BaseServiceImpl<Version> implements VersionService {

	@Autowired
	private VersionDao versionDao;

	@Override
	public PageVO pageVersion(VersionQuery versionQuery, Integer start, Integer limit) {
		return new PageVO(start, limit, this.versionDao.countVersion(versionQuery), this.versionDao.listVersion(versionQuery, start, limit));
	}

	@Override
	public void saveVersion(Version version) {
		long time = DateUtil.getTime();
		version.setCreateTime(time);
		version.setModifyTime(time);
		version.setStatus(StatusEnum.NORMAL.getValue());
		this.versionDao.save(version);
	}
	
	@Override
	public void updateVersion(Version version) {
		version.setModifyTime(DateUtil.getTime());
		this.versionDao.updateByPrimaryKeySelective(version);
	}

	@Override
	public void removeVersion(long id) {
		try {
			this.versionDao.removeByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceException(ExceptionEnum.USERD_NOT_ALLOW_DELETE);
		}
	}
	
	@Override
	public void freeze(long id) {
		Version version = new Version();
		version.setId(id);
		version.setModifyTime(DateUtil.getTime());
		version.setStatus(StatusEnum.FREEZE.getValue());
		this.versionDao.updateByPrimaryKeySelective(version);
	}

	@Override
	public void unfreeze(long id) {
		Version version = new Version();
		version.setId(id);
		version.setModifyTime(DateUtil.getTime());
		version.setStatus(StatusEnum.NORMAL.getValue());
		this.versionDao.updateByPrimaryKeySelective(version);
	}

}
