package com.qushop.common.util;

import com.qushop.prod.entity.Product;

public class ShopTemp {

	private Product product;
	private int count;
	private double price;
	private double totalamt;
	private String providerId;
	
	
	public double getTotalamt() {
		return totalamt;
	}
	public void setTotalamt(double totalamt) {
		this.totalamt = totalamt;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
