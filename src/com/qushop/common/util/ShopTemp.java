package com.qushop.common.util;

import com.qushop.musicains.entity.LeaseConfig;
import com.qushop.prod.entity.Product;

public class ShopTemp {

	private Product product;
	private int count;
	private double price;
	private double totalamt;
	private String providerId;
	private int leaseCycle;
	private int leaseType;
	private double yajin; 
	private LeaseConfig leaseConfig;
	
	
	public void setLeaseConfig(LeaseConfig leaseConfig) {
		this.leaseConfig = leaseConfig;
	}
	
	public LeaseConfig getLeaseConfig() {
		return leaseConfig;
	}
	
	public void setYajin(double yajin) {
		this.yajin = yajin;
	}
	
	public double getYajin() {
		return yajin;
	}
	
	public void setLeaseCycle(int leaseCycle) {
		this.leaseCycle = leaseCycle;
	}
	
	public int getLeaseCycle() {
		return leaseCycle;
	}
	
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
	
	public void setLeaseType(int leaseType) {
		this.leaseType = leaseType;
	}

	public int getLeaseType() {
		return leaseType;
	}

}
