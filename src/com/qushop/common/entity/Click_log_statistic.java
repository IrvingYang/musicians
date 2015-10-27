package com.qushop.common.entity;

/***********************************************************************
 * Module:  tb_click_log_statistic.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_click_log_statistic
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
 * 点击信息统计表
 * 
 */
@Entity
@Table(name="tb_click_log_statistic")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Click_log_statistic implements Serializable  {

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String clicktype;
	
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String entityid;
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private Date stattime;
	private int count;

	public String getClicktype() {
		return clicktype;
	}

	public void setClicktype(String clicktype) {
		this.clicktype = clicktype;
	}

	public String getEntityid() {
		return entityid;
	}

	public void setEntityid(String entityid) {
		this.entityid = entityid;
	}

	public Date getStattime() {
		return stattime;
	}

	public void setStattime(Date stattime) {
		this.stattime = stattime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


}