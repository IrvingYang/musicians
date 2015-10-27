package com.qushop.dict.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/***********************************************************************
 * Module:  tb_express_vendor.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_express_vendor
 ***********************************************************************/

/**
 * 快递公司字典表
 * 
 */
@Entity
@Table(name="tb_express_vendor")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Express_vendor implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String vendorid;
	private String vendorname;
	private short validflag;

	public String getVendorid() {
		return vendorid;
	}

	public void setVendorid(String vendorid) {
		this.vendorid = vendorid;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public short getValidflag() {
		return validflag;
	}

	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}
}