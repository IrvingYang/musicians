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
 *         &lt;element name="UnRegResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "unRegResult" })
@XmlRootElement(name = "UnRegResponse")
public class UnRegResponse {

	@XmlElement(name = "UnRegResult")
	protected int unRegResult;

	/**
	 * Gets the value of the unRegResult property.
	 * 
	 */
	public int getUnRegResult() {
		return unRegResult;
	}

	/**
	 * Sets the value of the unRegResult property.
	 * 
	 */
	public void setUnRegResult(int value) {
		this.unRegResult = value;
	}

}
