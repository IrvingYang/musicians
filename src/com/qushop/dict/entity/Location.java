package com.qushop.dict.entity;

/***********************************************************************
 * Module:  tb_location.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_location
 ***********************************************************************/

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 地理信息
 * 
 */
@Entity
@Table(name="tb_location")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Location implements Serializable {

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String entitytype;
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String entityid;
	private double longitude;
	private double latitude;
	private String locationName;
	
	public String getEntitytype() {
		return entitytype;
	}
	public void setEntitytype(String entitytype) {
		this.entitytype = entitytype;
	}
	public String getEntityid() {
		return entityid;
	}
	public void setEntityid(String entityid) {
		this.entityid = entityid;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}




}