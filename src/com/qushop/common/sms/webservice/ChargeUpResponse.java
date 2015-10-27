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
 *         &lt;element name="ChargeUpResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "chargeUpResult" })
@XmlRootElement(name = "ChargeUpResponse")
public class ChargeUpResponse {

	@XmlElement(name = "ChargeUpResult")
	protected int chargeUpResult;

	/**
	 * Gets the value of the chargeUpResult property.
	 * 
	 */
	public int getChargeUpResult() {
		return chargeUpResult;
	}

	/**
	 * Sets the value of the chargeUpResult property.
	 * 
	 */
	public void setChargeUpResult(int value) {
		this.chargeUpResult = value;
	}

}
