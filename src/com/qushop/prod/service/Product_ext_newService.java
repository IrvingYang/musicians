package com.qushop.prod.service;

import java.util.List;

import com.qushop.prod.entity.Product_ext_new;

public interface Product_ext_newService {


	/**
	 * 
	 * @param type0 查询列表1查询详情 2根据id查询新品曾经是否存在 3查询商品是否存在  4查询是否存在新到商品（多个）
	 * @param params
	 * @return
	 */
	public List<Product_ext_new> getNewProductByMethod(int type,String ...params);
	
	public boolean addNewProduct(Product_ext_new ext_new);
	
	public boolean updateProduct(Product_ext_new product_ext_new);

	public boolean deleteNewProduct(String productId,String operId);
	
}
