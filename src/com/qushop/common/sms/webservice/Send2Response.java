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
 *         &lt;element name="Send2Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "send2Result" })
@XmlRootElement(name = "Send2Response")
public class Send2Response {

	@XmlElement(name = "Send2Result")
	protected String send2Result;

	/**
	 * Gets the value of the send2Result property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSend2Result() {
		return send2Result;
	}

	/**
	 * Sets the value of the send2Result property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSend2Result(String value) {
		this.send2Result = value;
	}

}
