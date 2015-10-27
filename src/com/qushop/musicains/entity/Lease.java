package com.qushop.musicains.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.qushop.order.entity.Order_list;
import com.qushop.prod.entity.Product_ext_shop;
import com.qushop.user.entity.User;

/**
 * 租赁
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_lease")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Lease implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2221866897279787459L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="LEASE_SEQ")
    @SequenceGenerator(name="LEASE_SEQ",sequenceName="LEASE_SEQ")
	private int leaseId;//租赁ID
	private String userId;//用户ID
	private String productId;//产品ID
//	private String productTypeId;//产品ID
//	private String providerId;
//	private String addressId;
	private int leaseType;//租赁类型 1：租赁 2：续租
//	private double totalamt;//总价格
	//private double deposit;//定金
//	private int count;
	/*01：已下单
	02：已付款
	03：已发货
	04：已收货
	05：已完成*/
	private String status;
//	private double rent;//租金
	private Date createTime;//创建时间
	private int leaseCycle;//租赁周期
	private String continueId;//续租 租赁ID
	private Date shouldEndTime;//应该结束时间
	private Date realEndTime;//真实结束时间
	private Date lastUpdateTime;//
	private int validflag;//
//	private String address;//用户地址
	
	private String orderId;
	
	@Transient
	private Product_ext_shop ext_shop;
	
	@Transient
	private User user;
	
	@Transient
	private Order_list leaseOrderList;

	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
	public String getOrderId() {
		return orderId;
	}
	
	public void setLeaseOrderList(Order_list leaseOrderList) {
		this.leaseOrderList = leaseOrderList;
	}
	
	public Order_list getLeaseOrderList() {
		return leaseOrderList;
	}
	
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	
//	public void setProductTypeId(String productTypeId) {
//		this.productTypeId = productTypeId;
//	}
//	
//	public String getProductTypeId() {
//		return productTypeId;
//	}
//	
//	
//	public void setCount(int count) {
//		this.count = count;
//	}
//	
//	public int getCount() {
//		return count;
//	}
//	
//	public void setAddressId(String addressId) {
//		this.addressId = addressId;
//	}
//	
//	public String getAddressId() {
//		return addressId;
//	}
//	
//	public void setAddress(String address) {
//		this.address = address;
//	}
//	
//	public String getAddress() {
//		return address;
//	}
	
	public void setExt_shop(Product_ext_shop ext_shop) {
		this.ext_shop = ext_shop;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Product_ext_shop getExt_shop() {
		return ext_shop;
	}
	
	public User getUser() {
		return user;
	}

//	public String getProviderId() {
//		return providerId;
//	}
//
//	public void setProviderId(String providerId) {
//		this.providerId = providerId;
//	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLeaseId() {
		return leaseId;
	}

	public void setLeaseId(int leaseId) {
		this.leaseId = leaseId;
	}

//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getLeaseType() {
		return leaseType;
	}

	public void setLeaseType(int leaseType) {
		this.leaseType = leaseType;
	}

//	public Double getTotalamt() {
//		return totalamt;
//	}
//
//	public void setTotalamt(Double totalamt) {
//		this.totalamt = totalamt;
//	}

//	public Double getDeposit() {
//		return deposit;
//	}
//
//	public void setDeposit(Double deposit) {
//		this.deposit = deposit;
//	}

//	public Double getRent() {
//		return rent;
//	}
//
//	public void setRent(double rent) {
//		this.rent = rent;
//	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getLeaseCycle() {
		return leaseCycle;
	}

	public void setLeaseCycle(int leaseCycle) {
		this.leaseCycle = leaseCycle;
	}

	public String getContinueId() {
		return continueId;
	}

	public void setContinueId(String continueId) {
		this.continueId = continueId;
	}

	public Date getShouldEndTime() {
		return shouldEndTime;
	}

	public void setShouldEndTime(Date shouldEndTime) {
		this.shouldEndTime = shouldEndTime;
	}

	public Date getRealEndTime() {
		return realEndTime;
	}

	public void setRealEndTime(Date realEndTime) {
		this.realEndTime = realEndTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getValidflag() {
		return validflag;
	}

	public void setValidflag(int validflag) {
		this.validflag = validflag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
