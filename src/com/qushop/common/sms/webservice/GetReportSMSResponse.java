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
 *         &lt;element name="GetReportSMSResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getReportSMSResult" })
@XmlRootElement(name = "GetReportSMSResponse")
public class GetReportSMSResponse {

	@XmlElement(name = "GetReportSMSResult")
	protected String getReportSMSResult;

	/**
	 * Gets the value of the getReportSMSResult property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGetReportSMSResult() {
		return getReportSMSResult;
	}

	/**
	 * Sets the value of the getReportSMSResult property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGetReportSMSResult(String value) {
		this.getReportSMSResult = value;
	}

}
