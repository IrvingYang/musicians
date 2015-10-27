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

@Entity
@Table(name="tb_district")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class District implements Serializable {

	@Id
	@GeneratedValue(generator="myGeneric")
	@GenericGenerator(strategy="assigned",name="myGeneric")
	private String districtId;
	private String districtName;
	private short validflag;
	private String cityId;
	
	@Transient
	private City city;
	
	
	
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public short getValidflag() {
		return validflag;
	}
	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
}
