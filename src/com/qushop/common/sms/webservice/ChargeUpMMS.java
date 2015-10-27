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
 *         &lt;element name="CardNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CardPwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "corpID", "pwd", "cardNo", "cardPwd" })
@XmlRootElement(name = "ChargeUpMMS")
public class ChargeUpMMS {

	@XmlElement(name = "CorpID")
	protected String corpID;
	@XmlElement(name = "Pwd")
	protected String pwd;
	@XmlElement(name = "CardNo")
	protected String cardNo;
	@XmlElement(name = "CardPwd")
	protected String cardPwd;

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
	 * Gets the value of the cardNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * Sets the value of the cardNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCardNo(String value) {
		this.cardNo = value;
	}

	/**
	 * Gets the value of the cardPwd property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCardPwd() {
		return cardPwd;
	}

	/**
	 * Sets the value of the cardPwd property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCardPwd(String value) {
		this.cardPwd = value;
	}

}
