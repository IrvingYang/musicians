package com.qushop.provider.entity;

/***********************************************************************
 * Module:  tb_provider.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_provider
 ***********************************************************************/

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.qushop.dict.entity.City;
import com.qushop.dict.entity.Location;
import com.qushop.prod.entity.Product;

/**
 * 销售主体列表（含合作社）
 * 
 */
@Entity
@Table(name = "tb_provider")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Provider implements Serializable {

	@Id
	@GeneratedValue(generator = "genericGenerator")
	@GenericGenerator(name = "genericGenerator", strategy = "assigned")
	private String providerId;
	private String name;
	private short superprovider;
	private String direction;
	private String countryId = "086";
	private String stateId;
	private String cityId;
	private String postCode;
	private String street;
	private String telephone;
	private String providerimageid;
	private String certid1;
	private String certid1imageid;
	private String certid2;
	private String certid2imageid;
	private String certid3;
	private String certid3imageid;
	private String contactman;
	private String phone;
	private String description;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;

	@Transient
	private List<Provider_image> provider_image;
	@Transient
	private Location location;
	@Transient
	private City city;
	@Transient
	private List<Product> productList;

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getSuperprovider() {
		return superprovider;
	}

	public void setSuperprovider(short superprovider) {
		this.superprovider = superprovider;
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

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getProviderimageid() {
		return providerimageid;
	}

	public void setProviderimageid(String providerimageid) {
		this.providerimageid = providerimageid;
	}

	public String getCertid1() {
		return certid1;
	}

	public void setCertid1(String certid1) {
		this.certid1 = certid1;
	}

	public String getCertid1imageid() {
		return certid1imageid;
	}

	public void setCertid1imageid(String certid1imageid) {
		this.certid1imageid = certid1imageid;
	}

	public String getCertid2() {
		return certid2;
	}

	public void setCertid2(String certid2) {
		this.certid2 = certid2;
	}

	public String getCertid2imageid() {
		return certid2imageid;
	}

	public void setCertid2imageid(String certid2imageid) {
		this.certid2imageid = certid2imageid;
	}

	public String getCertid3() {
		return certid3;
	}

	public void setCertid3(String certid3) {
		this.certid3 = certid3;
	}

	public String getCertid3imageid() {
		return certid3imageid;
	}

	public void setCertid3imageid(String certid3imageid) {
		this.certid3imageid = certid3imageid;
	}

	public String getContactman() {
		return contactman;
	}

	public void setContactman(String contactman) {
		this.contactman = contactman;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<Provider_image> getProvider_image() {
		return provider_image;
	}

	public void setProvider_image(List<Provider_image> provider_image) {
		this.provider_image = provider_image;
	}

}