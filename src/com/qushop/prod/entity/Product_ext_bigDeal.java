package com.qushop.prod.entity;

/***********************************************************************
 * Module:  tb_product_ext_bigdeal.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_product_ext_bigDeal
 ***********************************************************************/

import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 商品大宗交易扩展表
 * 
 */
@Entity
@Table(name="tb_product_ext_bigdeal")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Product_ext_bigDeal  implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String bigdealId;
	private String productId;
	private double referencePrice;
	private String unit;
	private String salesarea;
	private String contactMan;
	private String contactTelephone;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;

	@Transient
	private Product product;

	public String getBigdealId() {
		return bigdealId;
	}

	public void setBigdealId(String bigdealId) {
		this.bigdealId = bigdealId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public double getReferencePrice() {
		return referencePrice;
	}

	public void setReferencePrice(double referencePrice) {
		this.referencePrice = referencePrice;
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

	public String getContactMan() {
		return contactMan;
	}

	public void setContactMan(String contactMan) {
		this.contactMan = contactMan;
	}

	public String getContactTelephone() {
		return contactTelephone;
	}

	public void setContactTelephone(String contactTelephone) {
		this.contactTelephone = contactTelephone;
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