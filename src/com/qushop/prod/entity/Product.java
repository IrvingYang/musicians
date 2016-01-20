package com.qushop.prod.entity;

/***********************************************************************
 * Module:  tb_product.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_product
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

import com.qushop.dict.entity.Brand_vendor;
import com.qushop.dict.entity.City;
import com.qushop.dict.entity.Country;
import com.qushop.provider.entity.Provider;

/**
 * 商品基础信息表
 * 
 */
@Entity
@Table(name="tb_product")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Product  implements Serializable{
	
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String productId;
	private String productName;
	private String productTypeId;
	private String description;
	private String unit;
	private int stockNumber;
	private Date onTime;
	private Date offTime;
	private double shopPrice;
	private short salesbyself;
	private String brandid;
	private String providerId;
	private short productGrade;
	private short attentionGrade;
	private String salesrange;
	private String productCityId;
	private String attribute;
	private String remarkHtml;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;
	private String productCountryId;
	
	//新增评分
	private Integer grade;
	//新增销售量
	private Integer salesvolume;
	//新增评分数量
	private Integer reviewcount;
	//商品折现率
	private Short discount;

	@Transient
	private Provider provider;
	@Transient
	private Brand_vendor brand_vendor;
	@Transient
	private ProductType product_type;
	@Transient
	private List<ProductImg> productimglist;
	@Transient
	private City city;
	@Transient
	private Country country;
	@Transient
	private ProductTrack producttrack;
	
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public Country getCountry() {
		return country;
	}
	
	public void setProductCountryId(String productCountryId) {
		this.productCountryId = productCountryId;
	}
	
	public String getProductCountryId() {
		return productCountryId;
	}
	
	public Short getDiscount() {
		return discount;
	}

	public void setDiscount(Short discount) {
		this.discount = discount;
	}

	public Integer getReviewcount() {
		return reviewcount;
	}

	public void setReviewcount(Integer reviewcount) {
		this.reviewcount = reviewcount;
	}

	public Integer getSalesvolume() {
		return salesvolume;
	}

	public void setSalesvolume(Integer salesvolume) {
		this.salesvolume = salesvolume;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getRemarkHtml() {
		return remarkHtml;
	}

	public void setRemarkHtml(String remarkHtml) {
		this.remarkHtml = remarkHtml;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
	}

	public Date getOnTime() {
		return onTime;
	}

	public void setOnTime(Date onTime) {
		this.onTime = onTime;
	}

	public Date getOffTime() {
		return offTime;
	}

	public void setOffTime(Date offTime) {
		this.offTime = offTime;
	}

	public double getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(double shopPrice) {
		this.shopPrice = shopPrice;
	}

	public short getSalesbyself() {
		return salesbyself;
	}

	public void setSalesbyself(short salesbyself) {
		this.salesbyself = salesbyself;
	}

	public String getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public short getProductGrade() {
		return productGrade;
	}

	public void setProductGrade(short productGrade) {
		this.productGrade = productGrade;
	}

	public short getAttentionGrade() {
		return attentionGrade;
	}

	public void setAttentionGrade(short attentionGrade) {
		this.attentionGrade = attentionGrade;
	}

	public String getSalesrange() {
		return salesrange;
	}

	public void setSalesrange(String salesrange) {
		this.salesrange = salesrange;
	}

	public String getProductCityId() {
		return productCityId;
	}

	public void setProductCityId(String productCityId) {
		this.productCityId = productCityId;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
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

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Brand_vendor getBrand_vendor() {
		return brand_vendor;
	}

	public void setBrand_vendor(Brand_vendor brand_vendor) {
		this.brand_vendor = brand_vendor;
	}

	public ProductType getProduct_type() {
		return product_type;
	}

	public void setProduct_type(ProductType product_type) {
		this.product_type = product_type;
	}

	public List<ProductImg> getProductimglist() {
		return productimglist;
	}

	public void setProductimglist(List<ProductImg> productimglist) {
		this.productimglist = productimglist;
	}

	public ProductTrack getProducttrack() {
		return producttrack;
	}

	public void setProducttrack(ProductTrack producttrack) {
		this.producttrack = producttrack;
	}

}