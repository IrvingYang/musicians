package com.qushop.order.entity;

/***********************************************************************
 * Module:  tb_order_list.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_order_list
 ***********************************************************************/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.qushop.dict.entity.Express_vendor;
import com.qushop.dict.entity.Payment_Method;
import com.qushop.prod.entity.ProductReview;
import com.qushop.user.entity.User;
import com.qushop.user.entity.UserAddress;

/**
 * 订单列表
 * 
 */
@Entity
@Table(name="tb_order_list")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Order_list implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String orderId;
	private short orderType;
	private String userId;
	private String userAddressId;
	private String status;
	private double totalamt;
	private Date createTime;
	private Date updateTime;
	private String providerid;
	private String paymentway;
	private String thirdpartorderno;
	private String accountno;
	private short requireinvoice;
	private Short invoicetype;
	private String invoicetitle;
	private String invoicecontent;
	private Short invoiceprinted;
	private String expressvendor;
	private String expressnumber;
	private String operid;
	private Date lastUpdateTime;
	private String remark;
	private short payofflineflag;
	private short validflag;

	@Transient
	private UserAddress userAddress;
	
	@Transient
	private List<Order_detail> order_detail;
	
	@Transient
	private User user;
	
	@Transient
	private Express_vendor express;
	
	@Transient
	private Payment_Method payment_Method;
	
	
	@Transient
	private List<ProductReview> productReviews =new ArrayList<ProductReview>();
	
	public void setProductReviews(List<ProductReview> productReviews) {
		this.productReviews=productReviews;
	}
	
	public List<ProductReview> getProductReviews() {
		return productReviews;
	}
	
	
	public String getThirdpartorderno() {
		return thirdpartorderno;
	}

	public void setThirdpartorderno(String thirdpartorderno) {
		this.thirdpartorderno = thirdpartorderno;
	}

	public Payment_Method getPayment_Method() {
		return payment_Method;
	}

	public void setPayment_Method(Payment_Method payment_Method) {
		this.payment_Method = payment_Method;
	}

	public Express_vendor getExpress() {
		return express;
	}

	public void setExpress(Express_vendor express) {
		this.express = express;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(String userAddressId) {
		this.userAddressId = userAddressId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalamt() {
		return totalamt;
	}

	public void setTotalamt(double totalamt) {
		this.totalamt = totalamt;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getProviderid() {
		return providerid;
	}

	public void setProviderid(String providerid) {
		this.providerid = providerid;
	}

	public String getPaymentway() {
		return paymentway;
	}

	public void setPaymentway(String paymentway) {
		this.paymentway = paymentway;
	}

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public short getRequireinvoice() {
		return requireinvoice;
	}

	public void setRequireinvoice(short requireinvoice) {
		this.requireinvoice = requireinvoice;
	}

	public Short getInvoicetype() {
		return invoicetype;
	}

	public void setInvoicetype(Short invoicetype) {
		this.invoicetype = invoicetype;
	}

	public String getInvoicetitle() {
		return invoicetitle;
	}

	public void setInvoicetitle(String invoicetitle) {
		this.invoicetitle = invoicetitle;
	}

	public String getInvoicecontent() {
		return invoicecontent;
	}

	public void setInvoicecontent(String invoicecontent) {
		this.invoicecontent = invoicecontent;
	}

	public Short getInvoiceprinted() {
		return invoiceprinted;
	}

	public void setInvoiceprinted(Short invoiceprinted) {
		this.invoiceprinted = invoiceprinted;
	}

	public String getExpressvendor() {
		return expressvendor;
	}

	public void setExpressvendor(String expressvendor) {
		this.expressvendor = expressvendor;
	}

	public String getExpressnumber() {
		return expressnumber;
	}

	public void setExpressnumber(String expressnumber) {
		this.expressnumber = expressnumber;
	}

	public String getOperid() {
		return operid;
	}

	public void setOperid(String operid) {
		this.operid = operid;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public short getValidflag() {
		return validflag;
	}

	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}

	public List<Order_detail> getOrder_detail() {
		return order_detail;
	}

	public void setOrder_detail(List<Order_detail> order_detail) {
		this.order_detail = order_detail;
	}

	public short getPayofflineflag() {
		return payofflineflag;
	}

	public void setPayofflineflag(short payofflineflag) {
		this.payofflineflag = payofflineflag;
	}

}