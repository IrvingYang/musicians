package com.qushop.dict.entity;

/***********************************************************************
 * Module:  tb_brand_vendor.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_brand_vendor
 ***********************************************************************/

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 品牌字典表
 * 
 */
@Entity
@Table(name="tb_brand_vendor")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Brand_vendor implements Serializable {

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String brandid;
	private String brandname;
	private String imagepath;
	private String url;
	private short validflag;


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public short getValidflag() {
		return validflag;
	}

	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}


}