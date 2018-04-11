package com.shudailaoshi.entity.ver;

import javax.persistence.Table;
import com.shudailaoshi.entity.base.BaseEntity;
import com.shudailaoshi.pojo.annotation.Comment;

/**
 * @createAuthor luohao
 * @createDate 2018-04-11
 * @modifyAuthor luohao
 * @modifyDate 2018-04-11
 */
@Table(name = "ver_need")
public class Need extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Comment("需求名称")
	private String needName;
	@Comment("优先级")
	private Integer take;
	@Comment("开发人")
	private String developName;
	@Comment("所属系统")
	private Long systemId;
	@Comment("版本号")
	private Long versionId;
	@Comment("测试状态")
	private String testStatus;
	@Comment("测试人")
	private String testName;
	

	public String getNeedName() {
		return needName;
	}
	public void setNeedName(String needName) {
		this.needName = needName;
	}
	public Integer getTake() {
		return take;
	}
	public void setTake(Integer take) {
		this.take = take;
	}
	public String getDevelopName() {
		return developName;
	}
	public void setDevelopName(String developName) {
		this.developName = developName;
	}
	public Long getSystemId() {
		return systemId;
	}
	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}
	public Long getVersionId() {
		return versionId;
	}
	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	

}