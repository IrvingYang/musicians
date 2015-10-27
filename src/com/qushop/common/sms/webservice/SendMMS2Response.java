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
 *         &lt;element name="SendMMS2Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "sendMMS2Result" })
@XmlRootElement(name = "SendMMS2Response")
public class SendMMS2Response {

	@XmlElement(name = "SendMMS2Result")
	protected String sendMMS2Result;

	/**
	 * Gets the value of the sendMMS2Result property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSendMMS2Result() {
		return sendMMS2Result;
	}

	/**
	 * Sets the value of the sendMMS2Result property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSendMMS2Result(String value) {
		this.sendMMS2Result = value;
	}

}
