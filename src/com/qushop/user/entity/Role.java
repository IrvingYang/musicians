package com.qushop.user.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.qushop.provider.entity.Provider;

/***********************************************************************
 * Module:  tb_role.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_role
 ***********************************************************************/

/**
 * 用户角色
 * 
 */
@Entity
@Table(name="tb_role")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Role implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String roleId;
	private String roledesc;
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String providerId;
	private short validflag;

	@Transient
	private Provider provider;
	@Transient
	private List<RoleAuthority> roleauthoritiesList;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoledesc() {
		return roledesc;
	}

	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
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

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public List<RoleAuthority> getRoleauthoritiesList() {
		return roleauthoritiesList;
	}

	public void setRoleauthoritiesList(List<RoleAuthority> roleauthoritiesList) {
		this.roleauthoritiesList = roleauthoritiesList;
	}


}