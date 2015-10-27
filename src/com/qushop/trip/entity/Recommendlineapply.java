package com.qushop.trip.entity;

/***********************************************************************
 * Module:  tb_recommendlineapply.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_recommendlineapply
 ***********************************************************************/

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.qushop.user.entity.User;

/**
 * 趣旅游申请
 * 
 */
@Entity
@Table(name="tb_recommendlineapply")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Recommendlineapply  implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String userid;
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String recommendlineid;
	private String name;
	private String phone;
	private int adultcount;
	private int childcount;
	private Date createtime;
	private int status;
	private String remark;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;
	@Transient
	private Recommendline recommendline;

	@Transient	
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRecommendlineid() {
		return recommendlineid;
	}

	public void setRecommendlineid(String recommendlineid) {
		this.recommendlineid = recommendlineid;
	}

	public int getAdultcount() {
		return adultcount;
	}

	public void setAdultcount(int adultcount) {
		this.adultcount = adultcount;
	}

	public int getChildcount() {
		return childcount;
	}

	public void setChildcount(int childcount) {
		this.childcount = childcount;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Recommendline getRecommendline() {
		return recommendline;
	}

	public void setRecommendline(Recommendline recommendline) {
		this.recommendline = recommendline;
	}


}