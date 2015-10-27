package com.qushop.trip.entity;

/***********************************************************************
 * Module:  tb_recommendlinetype.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_recommendlinetype
 ***********************************************************************/

import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 推荐旅游线路类别
 * 
 */
@Entity
@Table(name="tb_recommendlinetype")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Recommendlinetype implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String linetypeid;
	private String title;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;


	public String getLinetypeid() {
		return linetypeid;
	}

	public void setLinetypeid(String linetypeid) {
		this.linetypeid = linetypeid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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


}