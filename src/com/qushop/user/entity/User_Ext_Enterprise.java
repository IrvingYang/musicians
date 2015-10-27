package com.qushop.user.entity;

/***********************************************************************
 * Module:  tb_enterprise_user.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_enterprise_user
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
 * 企业用户信息表
 * 
 */
@Entity
@Table(name="tb_user_ext_enterprise")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class User_Ext_Enterprise  implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String userId;
	private String enterprisename;
	private String contactman;
	private String telephone;
	private String certid1;
	private String certid1imageid;
	private String certid2;
	private String certid2imageid;
	private String certid3;
	private String certid3imageid;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;

	@Transient
	private User user;
	
	@Transient
	private List<Enterprise_image> enterprise_image;
	
	@Transient
	private List<User_Ext_Enterprise_Account> user_account;

	
	public String getEnterprisename() {
		return enterprisename;
	}

	public void setEnterprisename(String enterprisename) {
		this.enterprisename = enterprisename;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContactman() {
		return contactman;
	}

	public void setContactman(String contactman) {
		this.contactman = contactman;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCertid1() {
		return certid1;
	}

	public void setCertid1(String certid1) {
		this.certid1 = certid1;
	}

	public String getCertid1imageid() {
		return certid1imageid;
	}

	public void setCertid1imageid(String certid1imageid) {
		this.certid1imageid = certid1imageid;
	}

	public String getCertid2() {
		return certid2;
	}

	public void setCertid2(String certid2) {
		this.certid2 = certid2;
	}

	public String getCertid2imageid() {
		return certid2imageid;
	}

	public void setCertid2imageid(String certid2imageid) {
		this.certid2imageid = certid2imageid;
	}

	public String getCertid3() {
		return certid3;
	}

	public void setCertid3(String certid3) {
		this.certid3 = certid3;
	}

	public String getCertid3imageid() {
		return certid3imageid;
	}

	public void setCertid3imageid(String certid3imageid) {
		this.certid3imageid = certid3imageid;
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

	public List<Enterprise_image> getEnterprise_image() {
		return enterprise_image;
	}

	public void setEnterprise_image(List<Enterprise_image> enterprise_image) {
		this.enterprise_image = enterprise_image;
	}

	public List<User_Ext_Enterprise_Account> getUser_account() {
		return user_account;
	}

	public void setUser_account(List<User_Ext_Enterprise_Account> user_account) {
		this.user_account = user_account;
	}




}