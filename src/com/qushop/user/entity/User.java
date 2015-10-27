package com.qushop.user.entity;

/***********************************************************************
 * Module:  tb_user.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_user
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

/**
 * 用户信息表
 * 
 */
@Entity
@Table(name="tb_user")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String userId;
	private String userName;
	private String password;
	private String email;
	private short userType;
	private short loginStatus;
	private Date lastLoginTime;
	private String operid;
	private Date lastUpdateTime;
	private short status;
	private short validflag;
	
	@Transient
	User_Ext_Personal personal;
	@Transient
	User_Ext_Enterprise enterprise;
   
	public User_Ext_Personal getPersonal() {
		return personal;
	}

	public void setPersonal(User_Ext_Personal personal) {
		this.personal = personal;
	}

	public User_Ext_Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(User_Ext_Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public short getUserType() {
		return userType;
	}

	public void setUserType(short userType) {
		this.userType = userType;
	}

	public short getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(short loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
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

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getValidflag() {
		return validflag;
	}

	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}



}