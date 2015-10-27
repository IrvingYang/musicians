package com.qushop.musicains.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 托管配置
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_phostingconfig")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class PHostingConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4255455000091880645L;
	@Id
	@GeneratedValue(generator="hcId")
	@GenericGenerator(name="hcId",strategy="assigned")
	private String hcId;//托管配置ID
	private Short month;//月份
	private Double money;//金额
	private String productId;//产品类型ID
	private Date lastUpdateTime;
	private Short validflag;

	public String getHcId() {
		return hcId;
	}

	public void setHcId(String hcId) {
		this.hcId = hcId;
	}

	public Short getMonth() {
		return month;
	}

	public void setMonth(Short month) {
		this.month = month;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Short getValidflag() {
		return validflag;
	}

	public void setValidflag(Short validflag) {
		this.validflag = validflag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
