package com.shudailaoshi.service.ver;

import com.shudailaoshi.entity.ver.Need;
import com.shudailaoshi.pojo.query.ver.NeedQuery;
import com.shudailaoshi.pojo.vos.common.PageVO;
import com.shudailaoshi.service.base.BaseService;

public interface NeedService extends BaseService<Need> {

	/**
	 * 查询需求
	 * 
	 * @createAuthor luohao
	 * @createDate 2018-04-11
	 * @modifyAuthor luohao
	 * @modifyDate 2018-04-11
	 * @param needQuery
	 * @param start
	 * @param limit
	 * @return
	 */
	PageVO pageNeed(NeedQuery needQuery, Integer start, Integer limit);

	/**
	 * 新增需求
	 * 
	 * @createAuthor luohao
	 * @createDate 2018-04-11
	 * @modifyAuthor luohao
	 * @modifyDate 2018-04-11
	 * @param need
	 */
	void saveNeed(Need need);
	
	/**
	 * 更新需求
	 * 
	 * @createAuthor luohao
	 * @createDate 2018-04-11
	 * @modifyAuthor luohao
	 * @modifyDate 2018-04-11
	 * @param need
	 */
	void updateNeed(Need need);

	/**
	 * 删除需求
	 * 
	 * @createAuthor luohao
	 * @createDate 2018-04-11
	 * @modifyAuthor luohao
	 * @modifyDate 2018-04-11
	 * @param id
	 */
	void removeNeed(long id);

	/**
	 * 冻结需求
	 * 
	 * @createAuthor luohao
	 * @createDate 2018-04-11
	 * @modifyAuthor luohao
	 * @modifyDate 2018-04-11
	 * @param id
	 */
	void freeze(long id);

	/**
	 * 解冻需求
	 * 
	 * @createAuthor luohao
	 * @createDate 2018-04-11
	 * @modifyAuthor luohao
	 * @modifyDate 2018-04-11
	 * @param id
	 */
	void unfreeze(long id);

}
