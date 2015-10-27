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
 * 回退租赁信息
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_rollbackLease")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class RollbackLease implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="leaseId")
	@GenericGenerator(name="leaseId",strategy="assigned")
	private String leaseId;//回退租赁ID
	private Date createTime;
	private Short isexpire;//是否满期 0否 1是
	private Double deposit;//回退定金
	private Double rent;//回退租金
	private Double rollbackMoney;
	private Date lastUpdateTime;
	private Short validflag;
	
	
	public String getLeaseId() {
		return leaseId;
	}
	public void setLeaseId(String leaseId) {
		this.leaseId = leaseId;
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
	public Double getDeposit() {
		return deposit;
	}
	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
	public Double getRent() {
		return rent;
	}
	public void setRent(Double rent) {
		this.rent = rent;
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
