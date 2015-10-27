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
 * 
 * 租赁金额配置
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_pleaseConfig")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class PLeaseConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8692717856488389184L;
	@Id
	@GeneratedValue(generator="lcId")
	@GenericGenerator(name="lcId",strategy="assigned")
	private String lcId;//配置ID
	private Short day;//天数
	private Double money;//金额
	private String productId;//类型ID
	private Date lastUpdateTime;
	private Short validflag;
	

	public String getLcId() {
		return lcId;
	}

	public void setLcId(String lcId) {
		this.lcId = lcId;
	}

	public Short getDay() {
		return day;
	}

	public void setDay(Short day) {
		this.day = day;
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
