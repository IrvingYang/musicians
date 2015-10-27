package com.qushop.trip.entity;

/***********************************************************************
 * Module:  tb_trip_module_recommend.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_trip_module_recommend
 ***********************************************************************/

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 旅游板块实体推荐表
 * 
 */
@Entity
@Table(name="tb_trip_Module_Recommend")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TripModuleRecommend implements Serializable{
	
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String entitytype;
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String entityid;
	private String title;
	private Date recommBeginTime;
	private Date recommEndTime;
	private String operid;
	private Date lastUpdateTime;
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private short validflag;
	
	@Transient
	private List<Recommendline> recommendlinesList;
	@Transient
	private List<Blogs> blogsList;
	@Transient
	private List<Activity> activitiesList;
	
	
	public List<Recommendline> getRecommendlinesList() {
		return recommendlinesList;
	}
	public void setRecommendlinesList(List<Recommendline> recommendlinesList) {
		this.recommendlinesList = recommendlinesList;
	}
	public List<Blogs> getBlogsList() {
		return blogsList;
	}
	public void setBlogsList(List<Blogs> blogsList) {
		this.blogsList = blogsList;
	}
	public List<Activity> getActivitiesList() {
		return activitiesList;
	}
	public void setActivitiesList(List<Activity> activitiesList) {
		this.activitiesList = activitiesList;
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
	public Date getRecommBeginTime() {
		return recommBeginTime;
	}
	public void setRecommBeginTime(Date recommBeginTime) {
		this.recommBeginTime = recommBeginTime;
	}
	public Date getRecommEndTime() {
		return recommEndTime;
	}
	public void setRecommEndTime(Date recommEndTime) {
		this.recommEndTime = recommEndTime;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}