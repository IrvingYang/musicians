package com.qushop.trip.entity;

/***********************************************************************
 * Module:  tb_activity.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_activity
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

import com.qushop.provider.entity.Provider;

/**
 * 趣活动列表
 * 
 */
@Entity
@Table(name="tb_activity")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Activity  implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String activityid;
	private String providerId;
	private String title;
	private String coverimageid;
	private Double price;
	private Date beginapplytime;
	private Date endapplytime;
	private Date begintime;
	private short status;
	private String introduction;
	private int bookcount;
	private String schedule;
	private String highlight;
	private String route;
	private String pricedesc;
	private String operid;
	private Date lastUpdateTime;
	private short isvip;
	private short validflag;

	@Transient
	private short applyadultCount;
	@Transient
	private short applychildCount;
	@Transient
	private short applyCount;

	@Transient
    private List<Entityimage> entityimage;
	@Transient
    private Provider provider;

	
	public short getApplyadultCount() {
		return applyadultCount;
	}

	public void setApplyadultCount(short applyadultCount) {
		this.applyadultCount = applyadultCount;
	}

	public short getApplychildCount() {
		return applychildCount;
	}

	public void setApplychildCount(short applychildCount) {
		this.applychildCount = applychildCount;
	}

	public short getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(short applyCount) {
		this.applyCount = applyCount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getActivityid() {
		return activityid;
	}

	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCoverimageid() {
		return coverimageid;
	}

	public void setCoverimageid(String coverimageid) {
		this.coverimageid = coverimageid;
	}

	public Date getBeginapplytime() {
		return beginapplytime;
	}

	public void setBeginapplytime(Date beginapplytime) {
		this.beginapplytime = beginapplytime;
	}

	public Date getEndapplytime() {
		return endapplytime;
	}

	public void setEndapplytime(Date endapplytime) {
		this.endapplytime = endapplytime;
	}

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public int getBookcount() {
		return bookcount;
	}

	public void setBookcount(int bookcount) {
		this.bookcount = bookcount;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getPricedesc() {
		return pricedesc;
	}

	public void setPricedesc(String pricedesc) {
		this.pricedesc = pricedesc;
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

	public short getIsvip() {
		return isvip;
	}

	public void setIsvip(short isvip) {
		this.isvip = isvip;
	}

	public short getValidflag() {
		return validflag;
	}

	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}

	public List<Entityimage> getEntityimage() {
		return entityimage;
	}

	public void setEntityimage(List<Entityimage> entityimage) {
		this.entityimage = entityimage;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}



}