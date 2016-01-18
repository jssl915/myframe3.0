package com.yf.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.cykj.grcloud.mybatis.annotation.Column;
import com.cykj.grcloud.mybatis.annotation.Table;

@Table(tabName="T_S_DICT",autoIncrement="dictId",pkId="dictId")
public class SysDict implements Serializable {
	
	private static final long serialVersionUID = -5036950269644360016L;

	@Column
	private Long dictId;

	@Column
	private String dictName;

	@Column
	private String dictDesc;

	@Column
	private Integer dictStatus;

	@Column
	private Date createTime;

	@Column
	private Date updateTime;

	public Long getDictId() {
		return this.dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	public String getDictName() {
		return this.dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName == null ? null : dictName.trim();
	}

	public String getDictDesc() {
		return this.dictDesc;
	}

	public void setDictDesc(String dictDesc) {
		this.dictDesc = dictDesc == null ? null : dictDesc.trim();
	}

	public Integer getDictStatus() {
		return this.dictStatus;
	}

	public void setDictStatus(Integer dictStatus) {
		this.dictStatus = dictStatus;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
