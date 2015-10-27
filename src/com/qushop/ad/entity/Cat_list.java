package com.qushop.ad.entity;

/***********************************************************************
 * Module:  tb_cat_list.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_cat_list
 ***********************************************************************/

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 推广产品列表
 * 
 */
@Entity
@Table(name="tb_cat_list")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Cat_list {

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String catlistid;
	private String productId;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;

	public String getCatlistid() {
		return catlistid;
	}

	public void setCatlistid(String catlistid) {
		this.catlistid = catlistid;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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