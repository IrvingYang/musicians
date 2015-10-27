package com.qushop.prod.entity;

/***********************************************************************
 * Module:  tb_product_ext_hotProduct.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_product_ext_hotProduct
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
 * 商品热度排行（定时统计）
 * 
 */
@Entity
@Table(name="tb_product_ext_hotproduct")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Product_ext_hotProduct implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String productId;
	private Date staticTime;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;

	@Transient
	private Product_ext_shop product_ext_shop;
	
	public Product_ext_shop getProduct_ext_shop() {
		return product_ext_shop;
	}

	public void setProduct_ext_shop(Product_ext_shop product_ext_shop) {
		this.product_ext_shop = product_ext_shop;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}


	public Date getStaticTime() {
		return staticTime;
	}

	public void setStaticTime(Date staticTime) {
		this.staticTime = staticTime;
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


}