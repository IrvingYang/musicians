package com.qushop.user.entity;

/***********************************************************************
 * Module:  tb_wishlist.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_wishlist
 ***********************************************************************/

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
import com.qushop.prod.entity.Product_ext_shop;

/**
 * 用户收藏
 * 
 */
@Entity
@Table(name="tb_wishlist")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Wishlist  implements Serializable{

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String userId;
	
	@Id
	@GeneratedValue(generator="genericGenerator1")
	@GenericGenerator(name="genericGenerator1",strategy="assigned")
	private String productId;
	
	private Integer partnerflag;
	
	private short validflag;
	
	@Transient
	private Product product;

	@Transient
	private Product_ext_shop product_ext_shop;


	public Product_ext_shop getProduct_ext_shop() {
		return product_ext_shop;
	}

	public void setProduct_ext_shop(Product_ext_shop product_ext_shop) {
		this.product_ext_shop = product_ext_shop;
	}

	public Integer getPartnerflag() {
		return partnerflag;
	}

	public void setPartnerflag(Integer partnerflag) {
		this.partnerflag = partnerflag;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public short getValidflag() {
		return validflag;
	}

	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}



}