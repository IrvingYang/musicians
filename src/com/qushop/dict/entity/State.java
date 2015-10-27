package com.qushop.dict.entity;

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

/***********************************************************************
 * Module:  tb_state.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_state
 ***********************************************************************/


/**
 * 省份数据字典表
 * 
 */

@Entity
@Table(name="tb_state")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class State implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String stateId;
	private String stateName;
	private String countryId;
	private short validflag;
	
	@Transient
	private List<City> citysList;

	public List<City> getCitysList() {
		return citysList;
	}

	public void setCitysList(List<City> citysList) {
		this.citysList = citysList;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public short getValidflag() {
		return validflag;
	}

	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}



}