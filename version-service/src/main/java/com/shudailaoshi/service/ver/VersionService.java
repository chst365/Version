package com.shudailaoshi.service.ver;

import com.shudailaoshi.entity.ver.Version;
import com.shudailaoshi.pojo.query.ver.VersionQuery;
import com.shudailaoshi.pojo.vos.common.PageVO;
import com.shudailaoshi.service.base.BaseService;

public interface VersionService extends BaseService<Version> {

	/**
	 * 查询版本
	 * 
	 * @createAuthor bishufu
	 * @createDate 2018-04-11
	 * @modifyAuthor bishufu
	 * @modifyDate 2018-04-11
	 * @param versionQuery
	 * @param start
	 * @param limit
	 * @return
	 */
	PageVO pageVersion(VersionQuery versionQuery, Integer start, Integer limit);

	/**
	 * 新增版本
	 * 
	 * @createAuthor bishufu
	 * @createDate 2018-04-11
	 * @modifyAuthor bishufu
	 * @modifyDate 2018-04-11
	 * @param version
	 */
	void saveVersion(Version version);
	
	/**
	 * 更新版本
	 * 
	 * @createAuthor bishufu
	 * @createDate 2018-04-11
	 * @modifyAuthor bishufu
	 * @modifyDate 2018-04-11
	 * @param version
	 */
	void updateVersion(Version version);

	/**
	 * 删除版本
	 * 
	 * @createAuthor bishufu
	 * @createDate 2018-04-11
	 * @modifyAuthor bishufu
	 * @modifyDate 2018-04-11
	 * @param id
	 */
	void removeVersion(long id);

	/**
	 * 冻结版本
	 * 
	 * @createAuthor bishufu
	 * @createDate 2018-04-11
	 * @modifyAuthor bishufu
	 * @modifyDate 2018-04-11
	 * @param id
	 */
	void freeze(long id);

	/**
	 * 解冻版本
	 * 
	 * @createAuthor bishufu
	 * @createDate 2018-04-11
	 * @modifyAuthor bishufu
	 * @modifyDate 2018-04-11
	 * @param id
	 */
	void unfreeze(long id);

}
