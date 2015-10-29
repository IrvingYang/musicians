package com.qushop.common.entity;


import com.qushop.musicains.entity.Lease;
import com.qushop.prod.entity.Product_ext_shop;

public class CartItem {

	private Product_ext_shop ext_shop;
	private int count;
	private double price;
	private Lease lease;
	private double subtotal;
	
	public CartItem(Product_ext_shop ext_shop, int count, double price,double subtotal) {
		this.ext_shop = ext_shop;
		this.count = count;
		this.price = price;
		this.subtotal=subtotal;
	}
	
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	public double getSubtotal() {
		return subtotal;
	}
	
	public void setLease(Lease lease) {
		this.lease = lease;
	}
	
	public Lease getLease() {
		return lease;
	}
	
	public double getPrice() {
		return price;
	}
	public Product_ext_shop getExt_shop() {
		return ext_shop;
	}
	public void setExt_shop(Product_ext_shop ext_shop) {
		this.ext_shop = ext_shop;
	}
	public int getCount() {
		return count;
	}
	
}
