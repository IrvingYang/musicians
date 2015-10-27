package com.qushop.user.entity;

/***********************************************************************
 * Module:  tb_user.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_user
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
 * 用户信息表
 * 
 */
@Entity
@Table(name="tb_user_ext_personal")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class User_Ext_Personal implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String userId;
	private short  grade;
	private short  sex;
	private String telephone;
	private short  subscribe_status;
	private String certTypeID;
	private String certNumber;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;

	@Transient
	private User user;
	
	@Transient
	private List<UserAddress> userAddress;
	
	@Transient
	private List<Wishlist> wishlist;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public short getGrade() {
		return grade;
	}

	public void setGrade(short grade) {
		this.grade = grade;
	}

	public short getSex() {
		return sex;
	}

	public void setSex(short sex) {
		this.sex = sex;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public short getSubscribe_status() {
		return subscribe_status;
	}

	public void setSubscribe_status(short subscribe_status) {
		this.subscribe_status = subscribe_status;
	}

	public String getCertTypeID() {
		return certTypeID;
	}

	public void setCertTypeID(String certTypeID) {
		this.certTypeID = certTypeID;
	}

	public String getCertNumber() {
		return certNumber;
	}

	public void setCertNumber(String certNumber) {
		this.certNumber = certNumber;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<UserAddress> getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(List<UserAddress> userAddress) {
		this.userAddress = userAddress;
	}

	public List<Wishlist> getWishlist() {
		return wishlist;
	}

	public void setWishlist(List<Wishlist> wishlist) {
		this.wishlist = wishlist;
	}



}