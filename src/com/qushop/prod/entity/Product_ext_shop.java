package com.qushop.prod.entity;


/***********************************************************************
 * Module:  tb_product_ext_shop.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_product_ext_shop
 ***********************************************************************/

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/** 商品商城扩展表
 * 
 */
@Entity
@Table(name="tb_product_ext_shop")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Product_ext_shop  implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String productId;
	private Date onTime;
	private Date offTime;
	private double originalPrice;
	
	private Double promotePrice;
	private short promoteflag;
	private String unit;
	private String salesarea;
	
	@Column(nullable=true)
	private Date promoteStartTime;
	
	@Column(nullable=true)
	private Date promoteEndTime;
	private String operid;
	
	@Column(nullable=true)
	private Date lastUpdateTime;
	private short validflag;

	@Transient
	private Product product;
	

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Date getOnTime() {
		return onTime;
	}

	public void setOnTime(Date onTime) {
		this.onTime = onTime;
	}

	public Date getOffTime() {
		return offTime;
	}

	public void setOffTime(Date offTime) {
		this.offTime = offTime;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Double getPromotePrice() {
		return promotePrice;
	}

	public void setPromotePrice(Double promotePrice) {
		this.promotePrice = promotePrice;
	}

	public short getPromoteflag() {
		return promoteflag;
	}

	public void setPromoteflag(short promoteflag) {
		this.promoteflag = promoteflag;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSalesarea() {
		return salesarea;
	}

	public void setSalesarea(String salesarea) {
		this.salesarea = salesarea;
	}

	public Date getPromoteStartTime() {
		return promoteStartTime;
	}

	public void setPromoteStartTime(Date promoteStartTime) {
		this.promoteStartTime = promoteStartTime;
	}

	public Date getPromoteEndTime() {
		return promoteEndTime;
	}

	public void setPromoteEndTime(Date promoteEndTime) {
		this.promoteEndTime = promoteEndTime;
	}


	public String getOperid() {
		return operid;
	}

	public void setOperid(String operid) {
		this.operid = operid;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public short getValidflag() {
		return validflag;
	}

	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


}