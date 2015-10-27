package com.qushop.prod.service;

import java.util.List;

import com.qushop.prod.entity.Product_ext_recommProd;

public interface Product_ext_recommProdService {

	/**
	 * 
	 * @param type 0 表示查询列表 1表示查询详情 2表示查询推荐产品曾经是否存在 3查看产品现在是否存在 4查询指定个数推荐产品 5查询是否存在推荐商品（多个）
	 * @param params
	 * @return
	 */
	public List getRecommProductByMethod(int type,String...params);
	
	public boolean addRecommendProduct(Product_ext_recommProd recommProd);
	
	public boolean updateRecommendProduct(Product_ext_recommProd recommProd);

	public boolean deleteRecommendProduct(String productId,String operId);
	
}
