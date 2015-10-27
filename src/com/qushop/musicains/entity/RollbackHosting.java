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
 * 托管回退信息
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_rollbackhosting")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class RollbackHosting implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="htId")
	@GenericGenerator(name="htId",strategy="assigned")
	private String htId;// 托管回退
	private Date createTime;
	private Short isexpire;//是否期满 0否 1是
	private Short isregoods;//是否退货0否 1是
	private Double rollbackMoney;//回退总金额
	private Date lastUpdateTime;
	private Short validflag;
	
	public String getHtId() {
		return htId;
	}
	public void setHtId(String htId) {
		this.htId = htId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Short getIsexpire() {
		return isexpire;
	}
	public void setIsexpire(Short isexpire) {
		this.isexpire = isexpire;
	}
	public Short getIsregoods() {
		return isregoods;
	}
	public void setIsregoods(Short isregoods) {
		this.isregoods = isregoods;
	}
	public Double getRollbackMoney() {
		return rollbackMoney;
	}
	public void setRollbackMoney(Double rollbackMoney) {
		this.rollbackMoney = rollbackMoney;
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
	
}
