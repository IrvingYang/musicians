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
 *         &lt;element name="BatchSend2Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "batchSend2Result" })
@XmlRootElement(name = "BatchSend2Response")
public class BatchSend2Response {

	@XmlElement(name = "BatchSend2Result")
	protected String batchSend2Result;

	/**
	 * Gets the value of the batchSend2Result property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBatchSend2Result() {
		return batchSend2Result;
	}

	/**
	 * Sets the value of the batchSend2Result property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBatchSend2Result(String value) {
		this.batchSend2Result = value;
	}

}
