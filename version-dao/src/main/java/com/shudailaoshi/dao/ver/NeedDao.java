package com.shudailaoshi.dao.ver;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.shudailaoshi.dao.base.BaseDao;
import com.shudailaoshi.entity.ver.Need;
import com.shudailaoshi.pojo.query.ver.NeedQuery;

public interface NeedDao extends BaseDao<Need> {

	/**
	 * 需求集合查询
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
	List<Need> listNeed(@Param("needQuery") NeedQuery needQuery, @Param("start") Integer start, @Param("limit") Integer limit);

	/**
	 * 需求总量查询
	 * 
	 * @createAuthor luohao
	 * @createDate 2018-04-11
	 * @modifyAuthor luohao
	 * @modifyDate 2018-04-11
	 * @param needQuery
	 * @return
	 */
	long countNeed(@Param("needQuery") NeedQuery needQuery);

}