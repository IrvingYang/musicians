package com.qushop.trip.entity;

/***********************************************************************
 * Module:  tb_entityimage.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_entityimage
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
 * 图片实体表
 * 
 */
@Entity
@Table(name="tb_entityimage")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Entityimage  implements Serializable {

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String imageid;
	private String imagepath;
	private String entitytype;
	private String entityid;
	private short imgType;
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

	public String getEntitytype() {
		return entitytype;
	}

	public void setEntitytype(String entitytype) {
		this.entitytype = entitytype;
	}

	public String getEntityid() {
		return entityid;
	}

	public void setEntityid(String entityid) {
		this.entityid = entityid;
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