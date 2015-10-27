package com.qushop.prod.entity;


/***********************************************************************
 * Module:  tb_productImg.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_productImg
 ***********************************************************************/

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/** 商品图片表
 * 
 */
@Entity
@Table(name="tb_productimg")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class ProductImg implements Serializable{
   
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String productId;
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private short serialNumber;
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private short imgType;
	private String path;
	private Date uploadTime;
	private short validflag;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public short getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(short serialNumber) {
		this.serialNumber = serialNumber;
	}

	public short getImgType() {
		return imgType;
	}

	public void setImgType(short imgType) {
		this.imgType = imgType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public short getValidflag() {
		return validflag;
	}

	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}

}