package com.qushop.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.qushop.prod.entity.Product_ext_shop;

public class ShoppingCart_Backup {

	private Map<String, Map<String,Object>> map = null;
	
	public ShoppingCart_Backup(){
		super();
		map = new TreeMap<String, Map<String,Object>>();
	}
	
	public void addCart(Product_ext_shop product_ext_shop,Integer addCount){
		
		if(map.containsKey(product_ext_shop.getProductId())){
			Map<String,Object> shopMap = map.get(product_ext_shop.getProductId());
			String productCount = shopMap.get("productCount")+"";
			if(!"null".equals(productCount) && "".equals(productCount)){
				shopMap.put("productCount", addCount);
			}else{
				shopMap.put("productCount", Integer.parseInt(productCount)+addCount);
			}
//			shopMap.put("shopproduct", product_ext_shop);
			map.put(product_ext_shop.getProductId(), shopMap);
		}else{
			Map<String,Object> shopMap = new HashMap<String,Object>();
			shopMap.put("productCount", 1);
			shopMap.put("providerId", product_ext_shop.getProduct().getProviderId());
			shopMap.put("shopproduct", product_ext_shop);
			map.put(product_ext_shop.getProductId(), shopMap);
		}
	}
	
	public void updateCart(Product_ext_shop product_ext_shop,int count){
		
		if(map.containsKey(product_ext_shop.getProductId())){
			Map<String,Object> shopMap = map.get(product_ext_shop.getProductId());
			String productCount = shopMap.get("productCount")+"";
			shopMap.put("productCount", count);
			shopMap.put("shopproduct", product_ext_shop);
			map.put(product_ext_shop.getProductId(), shopMap);
		}
	}
	
	public void removeCart(String productId){
		
		map.remove(productId);
	}

	public Map<String, Map<String, Object>> getMap() {
		return map;
	}
	public void setMap(Map<String, Map<String, Object>> map) {
		this.map = map;
	}
}
