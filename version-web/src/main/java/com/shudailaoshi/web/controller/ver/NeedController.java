package com.shudailaoshi.web.controller.ver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shudailaoshi.entity.ver.Need;
import com.shudailaoshi.pojo.query.ver.NeedQuery;
import com.shudailaoshi.pojo.annotation.Comment;
import com.shudailaoshi.pojo.vos.common.ResultVO;
import com.shudailaoshi.service.ver.NeedService;
import com.shudailaoshi.web.controller.base.BaseController;
import com.shudailaoshi.web.utils.ResultUtil;

@RequestMapping("ver/need")
@Controller
public class NeedController extends BaseController {

	@Autowired
	private NeedService needService;

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
	@RequestMapping("pageNeed")
	@ResponseBody
	public ResultVO pageNeed(NeedQuery needQuery, Integer start, Integer limit) {
		try {
			return ResultUtil.success(this.needService.pageNeed(needQuery, start, limit));
		} catch (Exception e) {
			return ResultUtil.error(log, e);
		}
	}

	/**
	 * 新增需求
	 * 
	 * @createAuthor luohao
	 * @createDate 2018-04-11
	 * @modifyAuthor luohao
	 * @modifyDate 2018-04-11
	 * @param need
	 * @return
	 */
	@Comment("新增需求")
	@RequestMapping(value = "saveNeed", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO saveNeed(Need need) {
		try {
			this.needService.saveNeed(need);
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(log, e);
		}
	}
	
	/**
	 * 更新需求
	 * 
	 * @createAuthor luohao
	 * @createDate 2018-04-11
	 * @modifyAuthor luohao
	 * @modifyDate 2018-04-11
	 * @param need
	 * @return
	 */
	@Comment("更新需求")
	@RequestMapping(value = "updateNeed", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO updateNeed(Need need) {
		try {
			this.needService.updateNeed(need);
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(log, e);
		}
	}

	/**
	 * 删除需求
	 * 
	 * @createAuthor luohao
	 * @createDate 2018-04-11
	 * @modifyAuthor luohao
	 * @modifyDate 2018-04-11
	 * @param id
	 * @return
	 */
	@Comment("删除需求")
	@RequestMapping(value = "removeNeed", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO removeNeed(long id) {
		try {
			this.needService.removeNeed(id);
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(log, e);
		}
	}

	/**
	 * 冻结
	 * 
	 * @createAuthor luohao
	 * @createDate 2018-04-11
	 * @modifyAuthor luohao
	 * @modifyDate 2018-04-11
	 * @param id
	 * @return
	 */
	@Comment("冻结需求")
	@RequestMapping(value = "freeze", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO freeze(long id) {
		try {
			this.needService.freeze(id);
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(log, e);
		}
	}

	/**
	 * 解冻需求
	 * 
	 * @createAuthor luohao
	 * @createDate 2018-04-11
	 * @modifyAuthor luohao
	 * @modifyDate 2018-04-11
	 * @param id
	 * @return
	 */
	@Comment("解冻需求")
	@RequestMapping(value = "unfreeze", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO unfreeze(long id) {
		try {
			this.needService.unfreeze(id);
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(log, e);
		}
	}

}