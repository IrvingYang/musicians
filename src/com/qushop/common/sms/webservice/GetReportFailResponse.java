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
 *         &lt;element name="GetReportFailResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getReportFailResult" })
@XmlRootElement(name = "GetReportFailResponse")
public class GetReportFailResponse {

	@XmlElement(name = "GetReportFailResult")
	protected String getReportFailResult;

	/**
	 * Gets the value of the getReportFailResult property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGetReportFailResult() {
		return getReportFailResult;
	}

	/**
	 * Sets the value of the getReportFailResult property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGetReportFailResult(String value) {
		this.getReportFailResult = value;
	}

}
