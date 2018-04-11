package com.shudailaoshi.pojo.query.ver;

import com.shudailaoshi.pojo.annotation.Comment;
import com.shudailaoshi.pojo.query.base.BaseQuery;

/**
 * @createAuthor bishufu
 * @createDate 2018-04-11
 * @modifyAuthor bishufu
 * @modifyDate 2018-04-11
 */
public class VersionQuery extends BaseQuery {

	private static final long serialVersionUID = 1L;

	@Comment("版本号")
	private Long versionNumber;
	@Comment("系统")
	private Long systemId;
	@Comment("预计开始时间")
	private Long predictStartTime;
	@Comment("预计发布时间")
	private Long predictPublishTime;
	@Comment("开发时间")
	private Long developmentTime;
	@Comment("上线时间")
	private Long uptime;
	

	public Long getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}
	public Long getSystemId() {
		return systemId;
	}
	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}
	public Long getPredictStartTime() {
		return predictStartTime;
	}
	public void setPredictStartTime(Long predictStartTime) {
		this.predictStartTime = predictStartTime;
	}
	public Long getPredictPublishTime() {
		return predictPublishTime;
	}
	public void setPredictPublishTime(Long predictPublishTime) {
		this.predictPublishTime = predictPublishTime;
	}
	public Long getDevelopmentTime() {
		return developmentTime;
	}
	public void setDevelopmentTime(Long developmentTime) {
		this.developmentTime = developmentTime;
	}
	public Long getUptime() {
		return uptime;
	}
	public void setUptime(Long uptime) {
		this.uptime = uptime;
	}
	

}