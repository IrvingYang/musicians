package com.qushop.prod.service;

import java.util.List;

import com.qushop.prod.entity.Product_ext_relation;

public interface Product_ext_relationService {

	public boolean addRelation(Product_ext_relation product_ext_relation);
	
	/**
	 * 
	 * @param type 0 查询所有 1查询是否存在关联
	 * @param params
	 * @return
	 */
	public List<Product_ext_relation> getRelationProductByMethod(int type,String ...params);

	public boolean deleteRelationProduct(String[] productId,String operId);
	
	public boolean deleteRelationByOneProduct(String productIds,String operId);
	
	public boolean updateProduct_ext_relation(Product_ext_relation product_ext_relation);
	
}
