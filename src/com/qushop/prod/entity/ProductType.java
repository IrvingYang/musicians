package com.qushop.prod.entity;


/***********************************************************************
 * Module:  tb_productType.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_productType
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

import com.qushop.musicains.entity.Discountconfig;
import com.qushop.musicains.entity.HostingConfig;
import com.qushop.musicains.entity.LeaseConfig;

/** 产品类别表
 * 
 */
@Entity
@Table(name="tb_producttype")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class ProductType implements Serializable{
	
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String productTypeId;
	private String LogImagePath;
	private String typeName;
	private String parentId;
	private String description;
	private String properties;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;
	
	@Transient
	ProductType parentProductType;
	@Transient
	List<Discountconfig> discountconfigs;
	@Transient
	List<LeaseConfig> leaseConfigs;
	@Transient
	List<HostingConfig> hostingConfigs;
	@Transient
	List<ProductType> childTypeList;
	
	
	
	public List<Discountconfig> getDiscountconfigs() {
		return discountconfigs;
	}

	public void setDiscountconfigs(List<Discountconfig> discountconfigs) {
		this.discountconfigs = discountconfigs;
	}

	public List<LeaseConfig> getLeaseConfigs() {
		return leaseConfigs;
	}

	public void setLeaseConfigs(List<LeaseConfig> leaseConfigs) {
		this.leaseConfigs = leaseConfigs;
	}

	public List<HostingConfig> getHostingConfigs() {
		return hostingConfigs;
	}

	public void setHostingConfigs(List<HostingConfig> hostingConfigs) {
		this.hostingConfigs = hostingConfigs;
	}

	public String getLogImagePath() {
		return LogImagePath;
	}

	public List<ProductType> getChildTypeList() {
		return childTypeList;
	}
	public void setChildTypeList(List<ProductType> childTypeList) {
		this.childTypeList = childTypeList;
	}
	public void setLogImagePath(String logImagePath) {
		LogImagePath = logImagePath;
	}

	public ProductType getParentProductType() {
		return parentProductType;
	}

	public void setParentProductType(ProductType parentProductType) {
		this.parentProductType = parentProductType;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

   
 
}