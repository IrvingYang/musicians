package com.qushop.trip.entity;

/***********************************************************************
 * Module:  tb_recommendline.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_recommendline
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
 * 推荐线路
 * 
 */
@Entity
@Table(name="tb_recommendline")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Recommendline implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String recommendlineid;
	private String providerId;
	private String title;
	private String introduction;
	private String feature;
	private String priceexplain;
	private String oneselfexpense;
	private String travelprompt;
	private String share;
	private Date beginapplytime;
	private Date endapplytime;
	private Date departuretime;
	private short status;
	private double price;
	private String coverimageid;
	private int days;
	private String linetypeid;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;

	@Transient
	private short applyadultCount;
	@Transient
	private short applychildCount;
	@Transient
	private short applyCount;
	
	@Transient
	private Recommendlinetype recommendlinetype;
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

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getRecommendlineid() {
		return recommendlineid;
	}

	public void setRecommendlineid(String recommendlineid) {
		this.recommendlineid = recommendlineid;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getPriceexplain() {
		return priceexplain;
	}

	public void setPriceexplain(String priceexplain) {
		this.priceexplain = priceexplain;
	}

	public String getOneselfexpense() {
		return oneselfexpense;
	}

	public void setOneselfexpense(String oneselfexpense) {
		this.oneselfexpense = oneselfexpense;
	}

	public String getTravelprompt() {
		return travelprompt;
	}

	public void setTravelprompt(String travelprompt) {
		this.travelprompt = travelprompt;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
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


	public Date getDeparturetime() {
		return departuretime;
	}

	public void setDeparturetime(Date departuretime) {
		this.departuretime = departuretime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCoverimageid() {
		return coverimageid;
	}

	public void setCoverimageid(String coverimageid) {
		this.coverimageid = coverimageid;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getLinetypeid() {
		return linetypeid;
	}

	public void setLinetypeid(String linetypeid) {
		this.linetypeid = linetypeid;
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

	public Recommendlinetype getRecommendlinetype() {
		return recommendlinetype;
	}

	public void setRecommendlinetype(Recommendlinetype recommendlinetype) {
		this.recommendlinetype = recommendlinetype;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


}