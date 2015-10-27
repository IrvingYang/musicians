package com.qushop.dict.entity;

/***********************************************************************
 * Module:  tb_order_list.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_order_list
 ***********************************************************************/

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 订单列表
 * 
 */
@Entity
@Table(name="tb_payment_method")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Payment_Method implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String paymentway;
//	@Id
//	@GeneratedValue(generator="genericGenerator")
//	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String instname;
	private String recvbranchname;
	private String recvbranchnumber;
	private String swiftname;
	private String swiftnumber;
	private String recvaccountname;
	private String recvaccountnumber;
	private String merchantId;
	private String imgpath;
	private String communicationkey;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;
	
	
	public String getCommunicationkey() {
		return communicationkey;
	}
	public void setCommunicationkey(String communicationkey) {
		this.communicationkey = communicationkey;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public String getPaymentway() {
		return paymentway;
	}
	public void setPaymentway(String paymentway) {
		this.paymentway = paymentway;
	}
	public String getInstname() {
		return instname;
	}
	public void setInstname(String instname) {
		this.instname = instname;
	}
	public String getRecvbranchname() {
		return recvbranchname;
	}
	public void setRecvbranchname(String recvbranchname) {
		this.recvbranchname = recvbranchname;
	}
	public String getRecvbranchnumber() {
		return recvbranchnumber;
	}
	public void setRecvbranchnumber(String recvbranchnumber) {
		this.recvbranchnumber = recvbranchnumber;
	}
	public String getSwiftname() {
		return swiftname;
	}
	public void setSwiftname(String swiftname) {
		this.swiftname = swiftname;
	}
	public String getSwiftnumber() {
		return swiftnumber;
	}
	public void setSwiftnumber(String swiftnumber) {
		this.swiftnumber = swiftnumber;
	}
	public String getRecvaccountname() {
		return recvaccountname;
	}
	public void setRecvaccountname(String recvaccountname) {
		this.recvaccountname = recvaccountname;
	}
	public String getRecvaccountnumber() {
		return recvaccountnumber;
	}
	public void setRecvaccountnumber(String recvaccountnumber) {
		this.recvaccountnumber = recvaccountnumber;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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

}