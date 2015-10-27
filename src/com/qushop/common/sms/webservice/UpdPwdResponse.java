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
 *         &lt;element name="UpdPwdResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "updPwdResult" })
@XmlRootElement(name = "UpdPwdResponse")
public class UpdPwdResponse {

	@XmlElement(name = "UpdPwdResult")
	protected int updPwdResult;

	/**
	 * Gets the value of the updPwdResult property.
	 * 
	 */
	public int getUpdPwdResult() {
		return updPwdResult;
	}

	/**
	 * Sets the value of the updPwdResult property.
	 * 
	 */
	public void setUpdPwdResult(int value) {
		this.updPwdResult = value;
	}

}
