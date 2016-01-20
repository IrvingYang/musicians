package com.qushop.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.qushop.musicains.entity.LeaseConfig;
import com.qushop.prod.entity.Product;
import com.qushop.prod.entity.Product_ext_shop;

public class ShoppingCart {

	private Map<String, Map<String, Object>> map = new TreeMap<String, Map<String, Object>>();
	
	
	
	public void addCart(Product product, int addCount, int cartType, Product_ext_shop product_ext_shop,
			String leaseCycle,LeaseConfig leaseConfig) {
		String key = product.getProductId() + "|" + cartType;
		if (map.containsKey(key)) {
			Map<String, Object> shopMap = map.get(key);
			if ((int) shopMap.get("cartType") == (cartType)) {

				String productCount = shopMap.get("productCount") + "";
				if (!"null".equals(productCount) && "".equals(productCount)) {
					shopMap.put("productCount", addCount);
				} else {
					shopMap.put("productCount", Integer.parseInt(productCount) + addCount);
				}
				// shopMap.put("shopproduct", product_ext_shop);
				map.put(key, shopMap);
			}
		} else
		{
			Map<String, Object> shopMap = new HashMap<String, Object>();
			shopMap.put("productCount", addCount);
			shopMap.put("providerId", product.getProviderId());
			shopMap.put("product", product);
			shopMap.put("cartType", cartType);
			// type位0代表商城商品
			if (cartType == 0) {
				Date nowdate = new Date();
				if (product_ext_shop.getPromoteflag() == 1 && nowdate.after(product_ext_shop.getPromoteStartTime())
						&& nowdate.before(product_ext_shop.getPromoteEndTime())) {
					shopMap.put("promoteflag", 1);
					shopMap.put("currentPrice", product_ext_shop.getPromotePrice());
				} else {
					shopMap.put("promoteflag", 0);
					shopMap.put("currentPrice", product_ext_shop.getOriginalPrice());
				}
				shopMap.put("promotePrice", product_ext_shop.getPromotePrice());
				shopMap.put("originalPrice", product_ext_shop.getOriginalPrice());
			}
			// type位1代表合作社商品
			else if (cartType == 1) {
				shopMap.put("shopPrice", product.getShopPrice());
			} // type 2 代表 租赁
			else if (cartType == 2) {
				shopMap.put("shopPrice", product.getShopPrice());
				shopMap.put("yajin", product.getShopPrice());
				shopMap.put("leaseCycle", leaseCycle);
				shopMap.put("leaseConfig",leaseConfig);
				shopMap.put("leaseType", 1);
			}

			map.put(key, shopMap);
		}

	}

	public void updateCart(Product product, int count,String cartType) {
		String key = product.getProductId() + "|" + cartType;
		if (map.containsKey(key)) {
			Map<String, Object> shopMap = map.get(key);
			// String productCount = shopMap.get("productCount")+"";
			shopMap.put("productCount", count);
			shopMap.put("product", product);
			map.put(key, shopMap);
		}
	}

	public void removeCart(String productId,String cartType) {
		String key = productId + "|" + cartType;
		map.remove(key);
	}

	public Map<String, Map<String, Object>> getMap() {
		return map;
	}

	public void setMap(Map<String, Map<String, Object>> map) {
		this.map = map;
	}
}
