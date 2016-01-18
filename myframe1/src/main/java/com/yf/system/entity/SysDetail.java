package com.yf.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.cykj.grcloud.mybatis.annotation.Column;
import com.cykj.grcloud.mybatis.annotation.Table;

@Table(tabName="T_S_DETAIL",autoIncrement="detailId",pkId="detailId")
public class SysDetail implements Serializable {
	private static final long serialVersionUID = -2939687590595282954L;

	@Column
	private Long detailId;

	@Column
	private Integer dictId;

	@Column
	private String detailName;

	@Column
	private String detailValue;

	@Column
	private String detailDesc;

	@Column
	private Integer detailStatus;

	@Column
	private Date createTime;

	@Column
	private Date updateTime;

	public Long getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public Integer getDictId() {
		return this.dictId;
	}

	public void setDictId(Integer dictId) {
		this.dictId = dictId;
	}

	public String getDetailName() {
		return this.detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName == null ? null : detailName.trim();
	}

	public String getDetailValue() {
		return this.detailValue;
	}

	public void setDetailValue(String detailValue) {
		this.detailValue = detailValue;
	}

	public String getDetailDesc() {
		return this.detailDesc;
	}

	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc == null ? null : detailDesc.trim();
	}

	public Integer getDetailStatus() {
		return this.detailStatus;
	}

	public void setDetailStatus(Integer detailStatus) {
		this.detailStatus = detailStatus;
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
