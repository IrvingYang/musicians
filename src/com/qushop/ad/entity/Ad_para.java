package com.qushop.ad.entity;

/***********************************************************************
 * Module:  tb_ad_para.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_ad_para
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

/**
 * 广告登记表
 */
@Entity
@Table(name="tb_ad_para")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Ad_para implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String adid;
	private String adname;
	private String adimageid;
	private short adtype;
	private String adlink;
	private String catlistid;
	private Date adbegintime;
	private Date adendtime;
	private short ischarge;
	private short chargemode;
	private Double price;
	private String adarea;
	private short adserial;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;

	@Transient
	private List<Ad_image> ad_imagesList;
	@Transient
	private Cat_list catlist;


	public List<Ad_image> getAd_imagesList() {
		return ad_imagesList;
	}

	public void setAd_imagesList(List<Ad_image> ad_imagesList) {
		this.ad_imagesList = ad_imagesList;
	}

	public String getAdid() {
		return adid;
	}

	public void setAdid(String adid) {
		this.adid = adid;
	}

	public String getAdname() {
		return adname;
	}

	public void setAdname(String adname) {
		this.adname = adname;
	}

	public String getAdimageid() {
		return adimageid;
	}

	public void setAdimageid(String adimageid) {
		this.adimageid = adimageid;
	}

	public short getAdtype() {
		return adtype;
	}

	public void setAdtype(short adtype) {
		this.adtype = adtype;
	}

	public String getAdlink() {
		return adlink;
	}

	public void setAdlink(String adlink) {
		this.adlink = adlink;
	}

	public String getCatlistid() {
		return catlistid;
	}

	public void setCatlistid(String catlistid) {
		this.catlistid = catlistid;
	}

	public Date getAdbegintime() {
		return adbegintime;
	}

	public void setAdbegintime(Date adbegintime) {
		this.adbegintime = adbegintime;
	}

	public Date getAdendtime() {
		return adendtime;
	}

	public void setAdendtime(Date adendtime) {
		this.adendtime = adendtime;
	}

	public short getIscharge() {
		return ischarge;
	}

	public void setIscharge(short ischarge) {
		this.ischarge = ischarge;
	}

	public short getChargemode() {
		return chargemode;
	}

	public void setChargemode(short chargemode) {
		this.chargemode = chargemode;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAdarea() {
		return adarea;
	}

	public void setAdarea(String adarea) {
		this.adarea = adarea;
	}

	public short getAdserial() {
		return adserial;
	}

	public void setAdserial(short adserial) {
		this.adserial = adserial;
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


	public Cat_list getCatlist() {
		return catlist;
	}

	public void setCatlist(Cat_list catlist) {
		this.catlist = catlist;
	}
}