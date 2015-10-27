package com.qushop.musicains.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_siteemail")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class SiteEmail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="emailId")
	@GenericGenerator(name="emailId",strategy="assigned")
	private String emailId;
	private String userId;
	private Short emailType;
	private String productName;
	private String description;
	private String imagePath;
	private String telephone;
	private String email;
	private Short validflag;
	
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Short getEmailType() {
		return emailType;
	}
	public void setEmailType(Short emailType) {
		this.emailType = emailType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Short getValidflag() {
		return validflag;
	}
	public void setValidflag(Short validflag) {
		this.validflag = validflag;
	}
}
