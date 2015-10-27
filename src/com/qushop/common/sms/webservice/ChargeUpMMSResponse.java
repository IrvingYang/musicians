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
 *         &lt;element name="ChargeUpMMSResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "chargeUpMMSResult" })
@XmlRootElement(name = "ChargeUpMMSResponse")
public class ChargeUpMMSResponse {

	@XmlElement(name = "ChargeUpMMSResult")
	protected String chargeUpMMSResult;

	/**
	 * Gets the value of the chargeUpMMSResult property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getChargeUpMMSResult() {
		return chargeUpMMSResult;
	}

	/**
	 * Sets the value of the chargeUpMMSResult property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setChargeUpMMSResult(String value) {
		this.chargeUpMMSResult = value;
	}

}
