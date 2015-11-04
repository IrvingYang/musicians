package com.qushop.prod.entity;


/***********************************************************************
 * Module:  tb_productReview.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_productReview
 ***********************************************************************/

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
import org.hibernate.annotations.GenericGenerator;

import com.qushop.user.entity.User;
import com.qushop.user.entity.User_Ext_Personal;

/** 商品评价表
 * 
 */
@Entity
@Table(name="tb_productreview")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class ProductReview implements Serializable{
	private static final long serialVersionUID = 3709424260840447319L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="REVIEW_SEQ")
    @SequenceGenerator(name="REVIEW_SEQ",sequenceName="REVIEW_SEQ")
	private int reviewId;//租赁ID

	private String productId;
	private String userId;
	private Date commentsDate;
	private String productComments;
	private short rate;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;
	private String orderId;

	@Transient
	private Product product;
	@Transient
	private User user;
	
	public int getReviewId() {
		return reviewId;
	}
	
	
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderId() {
		return orderId;
	}
	


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCommentsDate() {
		return commentsDate;
	}

	public void setCommentsDate(Date commentsDate) {
		this.commentsDate = commentsDate;
	}

	public String getProductComments() {
		return productComments;
	}

	public void setProductComments(String productComments) {
		this.productComments = productComments;
	}

	public short getRate() {
		return rate;
	}

	public void setRate(short rate) {
		this.rate = rate;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


}