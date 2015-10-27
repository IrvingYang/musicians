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
 *         &lt;element name="BatchSendResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "batchSendResult" })
@XmlRootElement(name = "BatchSendResponse")
public class BatchSendResponse {

	@XmlElement(name = "BatchSendResult")
	protected int batchSendResult;

	/**
	 * Gets the value of the batchSendResult property.
	 * 
	 */
	public int getBatchSendResult() {
		return batchSendResult;
	}

	/**
	 * Sets the value of the batchSendResult property.
	 * 
	 */
	public void setBatchSendResult(int value) {
		this.batchSendResult = value;
	}

}
