package com.qushop.musicains.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tb_pdiscountconfig")
public class PDiscountconfig implements Serializable{

	@Id
	@GenericGenerator(name="dctId",strategy="assigned")
	@GeneratedValue(generator="dctId")
	private String dctId;
	private Short month;
	private Double discountRate;
	private String productId;
	private Date lastUpdateTime;
	private Short validflag;
	public String getDctId() {
		return dctId;
	}
	public void setDctId(String dctId) {
		this.dctId = dctId;
	}
	public Short getMonth() {
		return month;
	}
	public void setMonth(Short month) {
		this.month = month;
	}
	public Double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
