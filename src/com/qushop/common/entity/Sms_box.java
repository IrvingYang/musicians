package com.qushop.common.entity;

/***********************************************************************
 * Module:  tb_sms_checkcode.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_sms_checkcode
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
 * 用户短信验证码
 * 
 */
@Entity
@Table(name = "tb_sms_box")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Sms_box implements Serializable{
	
	@Id
	@GeneratedValue(generator = "genericGenerator")
	@GenericGenerator(name = "genericGenerator", strategy = "assigned")
	private String telephone;
	private String smstype;

	private String message;
	
	@Id
	@GeneratedValue(generator = "genericGenerator")
	@GenericGenerator(name = "genericGenerator", strategy = "assigned")
	private Date createtime;
	private Date sendtime;
	private short validflag;


	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSmstype() {
		return smstype;
	}

	public void setSmstype(String smstype) {
		this.smstype = smstype;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public short getValidflag() {
		return validflag;
	}

	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}

}