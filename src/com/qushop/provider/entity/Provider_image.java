package com.qushop.provider.entity;

/***********************************************************************
 * Module:  tb_provider_image.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_provider_image
 ***********************************************************************/

import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 销售主体图片表
 * 
 */
@Entity
@Table(name="tb_provider_image")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Provider_image implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String imageid;
	private String imagepath;
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private short imgType;
	private String providerId;
	private Date uploadtime;
	
	public String getImageid() {
		return imageid;
	}
	public void setImageid(String imageid) {
		this.imageid = imageid;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public Date getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}
	public short getImgType() {
		return imgType;
	}
	public void setImgType(short imgType) {
		this.imgType = imgType;
	}

	

}