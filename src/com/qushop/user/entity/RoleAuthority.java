package com.qushop.user.entity;

/***********************************************************************
 * Module:  tb_role_authority.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_role_authority
 ***********************************************************************/

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;


/**
 * 操作员信息表
 * 
 */
@Entity
@Table(name="tb_role_authority")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class RoleAuthority  implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String roleId;
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String providerId;
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String urlId;
	private short validflag;
	
	@Transient
	UrlList urllist; 

	
	
	public String getUrlId() {
		return urlId;
	}
	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public short getValidflag() {
		return validflag;
	}
	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}
	public UrlList getUrllist() {
		return urllist;
	}
	public void setUrllist(UrlList urllist) {
		this.urllist = urllist;
	}

}