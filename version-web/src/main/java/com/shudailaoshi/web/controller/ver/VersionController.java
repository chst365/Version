package com.shudailaoshi.web.controller.ver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shudailaoshi.entity.ver.Version;
import com.shudailaoshi.pojo.query.ver.VersionQuery;
import com.shudailaoshi.pojo.annotation.Comment;
import com.shudailaoshi.pojo.vos.common.ResultVO;
import com.shudailaoshi.service.ver.VersionService;
import com.shudailaoshi.web.controller.base.BaseController;
import com.shudailaoshi.web.utils.ResultUtil;

@RequestMapping("ver/version")
@Controller
public class VersionController extends BaseController {

	@Autowired
	private VersionService versionService;

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
	@RequestMapping("pageVersion")
	@ResponseBody
	public ResultVO pageVersion(VersionQuery versionQuery, Integer start, Integer limit) {
		try {
			return ResultUtil.success(this.versionService.pageVersion(versionQuery, start, limit));
		} catch (Exception e) {
			return ResultUtil.error(log, e);
		}
	}

	/**
	 * 新增版本
	 * 
	 * @createAuthor bishufu
	 * @createDate 2018-04-11
	 * @modifyAuthor bishufu
	 * @modifyDate 2018-04-11
	 * @param version
	 * @return
	 */
	@Comment("新增版本")
	@RequestMapping(value = "saveVersion", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO saveVersion(Version version) {
		try {
			this.versionService.saveVersion(version);
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(log, e);
		}
	}
	
	/**
	 * 更新版本
	 * 
	 * @createAuthor bishufu
	 * @createDate 2018-04-11
	 * @modifyAuthor bishufu
	 * @modifyDate 2018-04-11
	 * @param version
	 * @return
	 */
	@Comment("更新版本")
	@RequestMapping(value = "updateVersion", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO updateVersion(Version version) {
		try {
			this.versionService.updateVersion(version);
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(log, e);
		}
	}

	/**
	 * 删除版本
	 * 
	 * @createAuthor bishufu
	 * @createDate 2018-04-11
	 * @modifyAuthor bishufu
	 * @modifyDate 2018-04-11
	 * @param id
	 * @return
	 */
	@Comment("删除版本")
	@RequestMapping(value = "removeVersion", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO removeVersion(long id) {
		try {
			this.versionService.removeVersion(id);
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(log, e);
		}
	}

	/**
	 * 冻结
	 * 
	 * @createAuthor bishufu
	 * @createDate 2018-04-11
	 * @modifyAuthor bishufu
	 * @modifyDate 2018-04-11
	 * @param id
	 * @return
	 */
	@Comment("冻结版本")
	@RequestMapping(value = "freeze", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO freeze(long id) {
		try {
			this.versionService.freeze(id);
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(log, e);
		}
	}

	/**
	 * 解冻版本
	 * 
	 * @createAuthor bishufu
	 * @createDate 2018-04-11
	 * @modifyAuthor bishufu
	 * @modifyDate 2018-04-11
	 * @param id
	 * @return
	 */
	@Comment("解冻版本")
	@RequestMapping(value = "unfreeze", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO unfreeze(long id) {
		try {
			this.versionService.unfreeze(id);
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(log, e);
		}
	}

}