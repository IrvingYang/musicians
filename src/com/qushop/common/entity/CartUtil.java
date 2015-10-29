package com.qushop.common.entity;

import java.util.HashMap;
import java.util.Map;

import com.qushop.prod.entity.Product_ext_shop;

public class CartUtil {

	private Map<String, CartItem> map = new HashMap<String, CartItem>();

	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}

	/**
	 * 添加购物车
	 * 
	 * @param productId
	 * @param ext_shop
	 * @param count
	 */
	public void addCart(String productId, Product_ext_shop ext_shop, int count) {

		int itemCount = count;
		double itemSubtotal;
		double itemPrice;

		if (map.containsKey(productId)) {
			CartItem cart = map.get(productId);
			if (ext_shop.getPromoteflag() == 1) {
				itemPrice = ext_shop.getPromotePrice();
				itemSubtotal = cart.getSubtotal() + ext_shop.getPromotePrice() * count;
			} else {
				itemPrice = ext_shop.getOriginalPrice();
				itemSubtotal = cart.getSubtotal() + ext_shop.getOriginalPrice() * count;
			}
		} else {
			if (ext_shop.getPromoteflag() == 1) {
				itemPrice = ext_shop.getPromotePrice();
				itemSubtotal = ext_shop.getPromotePrice() * count;
			} else {
				itemPrice = ext_shop.getOriginalPrice();
				itemSubtotal = ext_shop.getOriginalPrice() * count;
			}
		}
		
		CartItem cart = new CartItem(ext_shop, itemCount, itemPrice, itemSubtotal);
		map.put(productId, cart);
	}

	/**
	 * 
	 * @param productId
	 * @param ext_shop
	 * @param count
	 */
	public void updateCart(String productId, Product_ext_shop ext_shop, Integer count) {

		CartItem cart = null;
		cart = map.get(productId);
		cart.setCount(count);
		if (ext_shop.getPromoteflag() == 1) {
			cart.setSubtotal(ext_shop.getPromotePrice() * count);
		} else {
			cart.setSubtotal(ext_shop.getOriginalPrice() * count);
		}
		map.put(productId, cart);
	}

	/**
	 * 删除
	 * 
	 * @param productId
	 */
	public void deleteCart(String productId) {
		map.remove(productId);
	}
}
