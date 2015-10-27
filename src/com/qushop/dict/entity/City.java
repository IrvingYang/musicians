package com.qushop.dict.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/***********************************************************************
 * Module:  tb_city.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_city
 ***********************************************************************/

/**
 * 城市编码字典表
 * 
 */
@Entity
@Table(name="tb_city")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class City implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String cityId;
	private String cityName;
	private String stateId;
	private short validflag;

	@Transient
    private State state;
	@Transient
	Location location;
	

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public short getValidflag() {
		return validflag;
	}

	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
    
}