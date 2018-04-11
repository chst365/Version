package com.shudailaoshi.dao.ver;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.shudailaoshi.dao.base.BaseDao;
import com.shudailaoshi.entity.ver.Version;
import com.shudailaoshi.pojo.query.ver.VersionQuery;

public interface VersionDao extends BaseDao<Version> {

	/**
	 * 版本集合查询
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
	List<Version> listVersion(@Param("versionQuery") VersionQuery versionQuery, @Param("start") Integer start, @Param("limit") Integer limit);

	/**
	 * 版本总量查询
	 * 
	 * @createAuthor bishufu
	 * @createDate 2018-04-11
	 * @modifyAuthor bishufu
	 * @modifyDate 2018-04-11
	 * @param versionQuery
	 * @return
	 */
	long countVersion(@Param("versionQuery") VersionQuery versionQuery);

}