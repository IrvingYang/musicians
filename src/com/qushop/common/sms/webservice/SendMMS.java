package com.qushop.common.sms.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CorpID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Base64Content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExtCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SendTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "corpID", "pwd", "mobile", "base64Content",
		"title", "extCode", "sendTime" })
@XmlRootElement(name = "SendMMS")
public class SendMMS {

	@XmlElement(name = "CorpID")
	protected String corpID;
	@XmlElement(name = "Pwd")
	protected String pwd;
	@XmlElement(name = "Mobile")
	protected String mobile;
	@XmlElement(name = "Base64Content")
	protected String base64Content;
	@XmlElement(name = "Title")
	protected String title;
	@XmlElement(name = "ExtCode")
	protected String extCode;
	@XmlElement(name = "SendTime")
	protected String sendTime;

	/**
	 * Gets the value of the corpID property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCorpID() {
		return corpID;
	}

	/**
	 * Sets the value of the corpID property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCorpID(String value) {
		this.corpID = value;
	}

	/**
	 * Gets the value of the pwd property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * Sets the value of the pwd property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPwd(String value) {
		this.pwd = value;
	}

	/**
	 * Gets the value of the mobile property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the value of the mobile property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMobile(String value) {
		this.mobile = value;
	}

	/**
	 * Gets the value of the base64Content property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBase64Content() {
		return base64Content;
	}

	/**
	 * Sets the value of the base64Content property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBase64Content(String value) {
		this.base64Content = value;
	}

	/**
	 * Gets the value of the title property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the value of the title property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTitle(String value) {
		this.title = value;
	}

	/**
	 * Gets the value of the extCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getExtCode() {
		return extCode;
	}

	/**
	 * Sets the value of the extCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setExtCode(String value) {
		this.extCode = value;
	}

	/**
	 * Gets the value of the sendTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSendTime() {
		return sendTime;
	}

	/**
	 * Sets the value of the sendTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSendTime(String value) {
		this.sendTime = value;
	}

}
