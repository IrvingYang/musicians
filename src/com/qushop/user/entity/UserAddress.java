package com.qushop.user.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.qushop.dict.entity.District;

/***********************************************************************
 * Module:  tb_userAddress.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_userAddress
 ***********************************************************************/


/**
 * 用户地址信息表
 * 
 */
@Entity
@Table(name="tb_useraddress")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class UserAddress  implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String userAddressId;
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String userId;
	private String countryId = "086";
	private String stateId;
	private String cityId;
	private String districtId;
	private String street;
	private String postCode;
	private String telephone;
	private String name;
	private short defaultflag;
	private String deliveryschedule;
	private short validflag;
	private String areaId;

	@Transient
	private User_Ext_Personal user_Ext_Personal;
	@Transient
	private District district;
	
	
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	public String getAreaId() {
		return areaId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public User_Ext_Personal getUser_Ext_Personal() {
		return user_Ext_Personal;
	}
	public void setUser_Ext_Personal(User_Ext_Personal user_Ext_Personal) {
		this.user_Ext_Personal = user_Ext_Personal;
	}
	public String getUserAddressId() {
		return userAddressId;
	}
	public void setUserAddressId(String userAddressId) {
		this.userAddressId = userAddressId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public short getDefaultflag() {
		return defaultflag;
	}
	public void setDefaultflag(short defaultflag) {
		this.defaultflag = defaultflag;
	}
	public String getDeliveryschedule() {
		return deliveryschedule;
	}
	public void setDeliveryschedule(String deliveryschedule) {
		this.deliveryschedule = deliveryschedule;
	}
	public short getValidflag() {
		return validflag;
	}
	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}

    

}