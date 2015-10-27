package com.qushop.order.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.qushop.prod.entity.Product;
import com.qushop.prod.entity.Product_ext_bigDeal;
import com.qushop.prod.entity.Product_ext_groupBuy;

/***********************************************************************
 * Module:  tb_order_detail.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_order_detail
 ***********************************************************************/

/**
 * 订单详情
 * 
 */
@Entity
@Table(name="tb_order_detail")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Order_detail  implements Serializable {

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String orderId;
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private short orderType;
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String productId;
	private double price;
	private short quantity;
	private double totalamt;
	private short validflag;
	/**
	 * 0: 没发货
	 * 1：发货
	 * 2：运送途中
	 * 3：用户签收
	 */
	private short deliverStatus;
	
	@Transient
	private Product product;
	@Transient
	private Product_ext_groupBuy groupBuy;
	@Transient
	private Product_ext_bigDeal bigDeal;
	
	public void setDeliverStatus(short deliverStatus) {
		this.deliverStatus = deliverStatus;
	}
	
	public short getDeliverStatus() {
		return deliverStatus;
	}
	
	public Product_ext_bigDeal getBigDeal() {
		return bigDeal;
	}

	public void setBigDeal(Product_ext_bigDeal bigDeal) {
		this.bigDeal = bigDeal;
	}

	public Product_ext_groupBuy getGroupBuy() {
		return groupBuy;
	}

	public void setGroupBuy(Product_ext_groupBuy groupBuy) {
		this.groupBuy = groupBuy;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public short getOrderType() {
		return orderType;
	}

	public void setOrderType(short orderType) {
		this.orderType = orderType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public short getQuantity() {
		return quantity;
	}

	public void setQuantity(short quantity) {
		this.quantity = quantity;
	}

	public double getTotalamt() {
		return totalamt;
	}

	public void setTotalamt(double totalamt) {
		this.totalamt = totalamt;
	}

	public short getValidflag() {
		return validflag;
	}

	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}

}