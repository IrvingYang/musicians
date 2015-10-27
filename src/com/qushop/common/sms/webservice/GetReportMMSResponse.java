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
 *         &lt;element name="GetReportMMSResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getReportMMSResult" })
@XmlRootElement(name = "GetReportMMSResponse")
public class GetReportMMSResponse {

	@XmlElement(name = "GetReportMMSResult")
	protected String getReportMMSResult;

	/**
	 * Gets the value of the getReportMMSResult property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGetReportMMSResult() {
		return getReportMMSResult;
	}

	/**
	 * Sets the value of the getReportMMSResult property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGetReportMMSResult(String value) {
		this.getReportMMSResult = value;
	}

}
