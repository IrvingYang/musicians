package com.qushop.prod.entity;

/***********************************************************************
 * Module:  tb_product_ext_groupBuy.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_product_ext_groupBuy
 ***********************************************************************/

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 商品团购扩展表
 * 
 */
@Entity
@Table(name="tb_product_ext_groupbuy")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Product_ext_groupBuy  implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String groupbuyid;
	private short groupBuyType;
	private String productId;
	private double groupBuyPrice;
	private String groupbuyhint;
	private int stockNumber;
	private String unit;
	private String salesarea;
	private Date groupBuyStartTime;
	private Date groupBuyEndTime;
	private int minBuyCount;
	private int targetCount;
	private short targetType;
	private Short openresult;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;

	@Transient
	private Product product;
	
	public String getGroupbuyid() {
		return groupbuyid;
	}

	public void setGroupbuyid(String groupbuyid) {
		this.groupbuyid = groupbuyid;
	}

	public short getGroupBuyType() {
		return groupBuyType;
	}

	public void setGroupBuyType(short groupBuyType) {
		this.groupBuyType = groupBuyType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public double getGroupBuyPrice() {
		return groupBuyPrice;
	}

	public void setGroupBuyPrice(double groupBuyPrice) {
		this.groupBuyPrice = groupBuyPrice;
	}

	public String getGroupbuyhint() {
		return groupbuyhint;
	}

	public void setGroupbuyhint(String groupbuyhint) {
		this.groupbuyhint = groupbuyhint;
	}

	public int getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
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

	public Date getGroupBuyStartTime() {
		return groupBuyStartTime;
	}

	public void setGroupBuyStartTime(Date groupBuyStartTime) {
		this.groupBuyStartTime = groupBuyStartTime;
	}

	public Date getGroupBuyEndTime() {
		return groupBuyEndTime;
	}

	public void setGroupBuyEndTime(Date groupBuyEndTime) {
		this.groupBuyEndTime = groupBuyEndTime;
	}

	public int getMinBuyCount() {
		return minBuyCount;
	}

	public void setMinBuyCount(int minBuyCount) {
		this.minBuyCount = minBuyCount;
	}

	public int getTargetCount() {
		return targetCount;
	}

	public void setTargetCount(int targetCount) {
		this.targetCount = targetCount;
	}

	public short getTargetType() {
		return targetType;
	}

	public void setTargetType(short targetType) {
		this.targetType = targetType;
	}

	public Short getOpenresult() {
		return openresult;
	}

	public void setOpenresult(Short openresult) {
		this.openresult = openresult;
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