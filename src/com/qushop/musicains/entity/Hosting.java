package com.qushop.musicains.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 托管
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_hosting")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Hosting implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2296937989484538374L;

	@Id
	@GeneratedValue(generator="htId")
	@GenericGenerator(name="htId",strategy="assigned")
	private String htId;//托管ID
	
	@Id
	@GeneratedValue(generator="userId")
	@GenericGenerator(name="userId",strategy="assigned")
	private String userId;//用户ID
	private String productId;//产品ID
	private String providerId;
	private Date createTime;//创建时间
	private Short htCycle;//托管时间（月为单位）
	private Double totalamt;//金额
	private Short hType;
	private Short status;
	private Date shouldEndTime;
	private Date realEndTime;
	private Date lastUpdateTime;
	private Short validflag;
	
	
	
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public Short gethType() {
		return hType;
	}
	public void sethType(Short hType) {
		this.hType = hType;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Date getShouldEndTime() {
		return shouldEndTime;
	}
	public void setShouldEndTime(Date shouldEndTime) {
		this.shouldEndTime = shouldEndTime;
	}
	public Date getRealEndTime() {
		return realEndTime;
	}
	public void setRealEndTime(Date realEndTime) {
		this.realEndTime = realEndTime;
	}
	public String getHtId() {
		return htId;
	}
	public void setHtId(String htId) {
		this.htId = htId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Short getHtCycle() {
		return htCycle;
	}
	public void setHtCycle(Short htCycle) {
		this.htCycle = htCycle;
	}
	public Double getTotalamt() {
		return totalamt;
	}
	public void setTotalamt(Double totalamt) {
		this.totalamt = totalamt;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Short getValidflag() {
		return validflag;
	}
	public void setValidflag(Short validflag) {
		this.validflag = validflag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
