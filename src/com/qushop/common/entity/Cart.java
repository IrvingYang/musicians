package com.qushop.common.entity;

import com.qushop.prod.entity.Product_ext_shop;

public class Cart {

	private Product_ext_shop ext_shop;
	private Integer count;
	private Double price;
	private Double subtotal;
	
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Product_ext_shop getExt_shop() {
		return ext_shop;
	}
	public void setExt_shop(Product_ext_shop ext_shop) {
		this.ext_shop = ext_shop;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
}
