package com.qushop.prod.entity;

/***********************************************************************
 * Module:  tb_product.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_product
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
 * 商品基础信息表
 * 
 */
@Entity
@Table(name="tb_product_ext_relation")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Product_ext_relation implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String productId1;
	
	@Id
	@GeneratedValue(generator="genericGenerator1")
	@GenericGenerator(name="genericGenerator1",strategy="assigned")
	private String productId2;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;

	@Transient
	private Product_ext_shop product_ext_shop1;
	@Transient
	private Product_ext_shop product_ext_shop2;
	
	public String getProductId1() {
		return productId1;
	}
	public void setProductId1(String productId1) {
		this.productId1 = productId1;
	}
	public String getProductId2() {
		return productId2;
	}
	public void setProductId2(String productId2) {
		this.productId2 = productId2;
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
	public Product_ext_shop getProduct_ext_shop1() {
		return product_ext_shop1;
	}
	public void setProduct_ext_shop1(Product_ext_shop product_ext_shop1) {
		this.product_ext_shop1 = product_ext_shop1;
	}
	public Product_ext_shop getProduct_ext_shop2() {
		return product_ext_shop2;
	}
	public void setProduct_ext_shop2(Product_ext_shop product_ext_shop2) {
		this.product_ext_shop2 = product_ext_shop2;
	}
	

}