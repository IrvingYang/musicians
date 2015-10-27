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
 *         &lt;element name="LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LoginPwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorpID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SmsBalance" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MmsBalance" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "loginName", "loginPwd", "corpID",
		"smsBalance", "mmsBalance" })
@XmlRootElement(name = "AgentChangeAccount")
public class AgentChangeAccount {

	@XmlElement(name = "LoginName")
	protected String loginName;
	@XmlElement(name = "LoginPwd")
	protected String loginPwd;
	@XmlElement(name = "CorpID")
	protected String corpID;
	@XmlElement(name = "SmsBalance")
	protected int smsBalance;
	@XmlElement(name = "MmsBalance")
	protected int mmsBalance;

	/**
	 * Gets the value of the loginName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * Sets the value of the loginName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLoginName(String value) {
		this.loginName = value;
	}

	/**
	 * Gets the value of the loginPwd property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLoginPwd() {
		return loginPwd;
	}

	/**
	 * Sets the value of the loginPwd property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLoginPwd(String value) {
		this.loginPwd = value;
	}

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
	 * Gets the value of the smsBalance property.
	 * 
	 */
	public int getSmsBalance() {
		return smsBalance;
	}

	/**
	 * Sets the value of the smsBalance property.
	 * 
	 */
	public void setSmsBalance(int value) {
		this.smsBalance = value;
	}

	/**
	 * Gets the value of the mmsBalance property.
	 * 
	 */
	public int getMmsBalance() {
		return mmsBalance;
	}

	/**
	 * Sets the value of the mmsBalance property.
	 * 
	 */
	public void setMmsBalance(int value) {
		this.mmsBalance = value;
	}

}
