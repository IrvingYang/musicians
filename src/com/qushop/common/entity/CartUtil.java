package com.qushop.common.entity;

import java.util.HashMap;
import java.util.Map;

import com.qushop.prod.entity.Product_ext_shop;

public class CartUtil {
	
	private Map<String,Cart> map = new HashMap<String, Cart>();
	
	public Map<String, Cart> getMap() {
		return map;
	}

	public void setMap(Map<String, Cart> map) {
		this.map = map;
	}

	/**
	 * 添加购物车
	 * @param productId
	 * @param ext_shop
	 * @param count
	 */
	public void addCart(String productId,Product_ext_shop ext_shop,Integer count){
		
		Cart cart = null;
		if(map.containsKey(productId)){
			cart = map.get(productId);
			if(cart==null)
			{
				cart = new Cart();
			}
			cart.setExt_shop(ext_shop);
			cart.setCount(count);
			if(ext_shop.getPromoteflag()==1)
			{
				cart.setPrice(ext_shop.getPromotePrice());
				cart.setSubtotal(cart.getSubtotal()+ext_shop.getPromotePrice()*count);
			}
			else
			{
				cart.setPrice(ext_shop.getOriginalPrice());
				cart.setSubtotal(cart.getSubtotal()+ext_shop.getOriginalPrice()*count);
			}
			map.put(productId, cart);
		}
		else{
			cart = new Cart();
			cart.setCount(1);
			cart.setExt_shop(ext_shop);
			if(ext_shop.getPromoteflag()==1)
			{
				cart.setPrice(ext_shop.getPromotePrice());
				cart.setSubtotal(ext_shop.getPromotePrice()*count);
			}
			else
			{
				cart.setPrice(ext_shop.getOriginalPrice());
				cart.setSubtotal(ext_shop.getOriginalPrice()*count);
			}
			map.put(productId, cart);
		}
	}
	
	/**
	 * xiugai 
	 * @param productId
	 * @param ext_shop
	 * @param count
	 */
	public void updateCart(String productId,Product_ext_shop ext_shop,Integer count){
		
		Cart cart = null;
		cart = map.get(productId);
		cart.setCount(count);
		if(ext_shop.getPromoteflag()==1)
		{
			cart.setSubtotal(ext_shop.getPromotePrice()*count);
		}
		else
		{
			cart.setSubtotal(ext_shop.getOriginalPrice()*count);
		}
		map.put(productId, cart);
	}
	/**
	 * 删除
	 * @param productId
	 */
	public void deleteCart(String productId){
		map.remove(productId);
	}
}
