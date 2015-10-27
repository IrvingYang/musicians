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
 *         &lt;element name="Content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cell" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "", propOrder = { "corpID", "pwd", "mobile", "content", "cell",
		"sendTime" })
@XmlRootElement(name = "BatchSend")
public class BatchSend {

	@XmlElement(name = "CorpID")
	protected String corpID;
	@XmlElement(name = "Pwd")
	protected String pwd;
	@XmlElement(name = "Mobile")
	protected String mobile;
	@XmlElement(name = "Content")
	protected String content;
	@XmlElement(name = "Cell")
	protected String cell;
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
	 * Gets the value of the content property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the value of the content property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContent(String value) {
		this.content = value;
	}

	/**
	 * Gets the value of the cell property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCell() {
		return cell;
	}

	/**
	 * Sets the value of the cell property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCell(String value) {
		this.cell = value;
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
