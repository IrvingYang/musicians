package com.qushop.user.entity;

/***********************************************************************
 * Module:  tb_oper.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_oper
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
 * 操作员信息表
 * 
 */
@Entity
@Table(name="tb_oper")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Oper  implements Serializable{
	
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String operId;
	private String operName;
	private String roleId;
	private String password;
	private String email;
	private short sex;
	private String telephone;
	private short loginStatus;
	private Date lastLoginTime;
	private String providerId;
	private short partnerflag;
	private short validflag;

	@Transient
	private Role role;
	
	@Transient
	private Provider provider;
	

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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

	public short getPartnerflag() {
		return partnerflag;
	}

	public void setPartnerflag(short partnerflag) {
		this.partnerflag = partnerflag;
	}


	public short getValidflag() {
		return validflag;
	}

	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


}