package com.qushop.user.entity;

/***********************************************************************
 * Module:  tb_url_list.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_url_list
 ***********************************************************************/

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

/**
 * 点击信息表
 * 
 */
@Entity
@Table(name="tb_url_List")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class UrlList  implements Serializable{
	
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String urlId;
	private String urlLink;
	private String description;
	private String parentId;
	private short validflag;
	
	@Transient
	List<UrlList> urlList;

	public List<UrlList> getUrlList() {
		return urlList;
	}
	public void setUrlList(List<UrlList> urlList) {
		this.urlList = urlList;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUrlId() {
		return urlId;
	}
	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}
	public String getUrlLink() {
		return urlLink;
	}
	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public short getValidflag() {
		return validflag;
	}
	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}



}