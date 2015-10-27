package com.qushop.trip.entity;

/***********************************************************************
 * Module:  tb_blogstype.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_blogstype
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
 * 游记类别
 * 
 */
@Entity
@Table(name="tb_blogstype")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Blogstype implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String blogstypeid;
	private String blogstypename;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;
	
	public String getBlogstypeid() {
		return blogstypeid;
	}
	public void setBlogstypeid(String blogstypeid) {
		this.blogstypeid = blogstypeid;
	}
	public String getBlogstypename() {
		return blogstypename;
	}
	public void setBlogstypename(String blogstypename) {
		this.blogstypename = blogstypename;
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