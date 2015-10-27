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
 *         &lt;element name="CorpName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LinkMan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "corpID", "pwd", "corpName", "linkMan",
		"tel", "mobile", "email", "memo" })
@XmlRootElement(name = "UpdReg")
public class UpdReg {

	@XmlElement(name = "CorpID")
	protected String corpID;
	@XmlElement(name = "Pwd")
	protected String pwd;
	@XmlElement(name = "CorpName")
	protected String corpName;
	@XmlElement(name = "LinkMan")
	protected String linkMan;
	@XmlElement(name = "Tel")
	protected String tel;
	@XmlElement(name = "Mobile")
	protected String mobile;
	@XmlElement(name = "Email")
	protected String email;
	@XmlElement(name = "Memo")
	protected String memo;

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
	 * Gets the value of the corpName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCorpName() {
		return corpName;
	}

	/**
	 * Sets the value of the corpName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCorpName(String value) {
		this.corpName = value;
	}

	/**
	 * Gets the value of the linkMan property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLinkMan() {
		return linkMan;
	}

	/**
	 * Sets the value of the linkMan property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLinkMan(String value) {
		this.linkMan = value;
	}

	/**
	 * Gets the value of the tel property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * Sets the value of the tel property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTel(String value) {
		this.tel = value;
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
	 * Gets the value of the email property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the value of the email property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEmail(String value) {
		this.email = value;
	}

	/**
	 * Gets the value of the memo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * Sets the value of the memo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMemo(String value) {
		this.memo = value;
	}

}
