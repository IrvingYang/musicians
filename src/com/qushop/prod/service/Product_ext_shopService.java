package com.qushop.prod.service;

import java.util.List;
import java.util.Map;

import com.qushop.common.util.PagePojo;
import com.qushop.prod.entity.Product_ext_shop;

public interface Product_ext_shopService {

	public Map getShopProductByProductTypeId(String level,
			String productTypeId, String ordercondition, int pageno,int pagesize,String listtype);

	public boolean existsExtShopProductInProductId(String productId);
	
	public boolean deleteProductShop(String productIds,String operId);
	
	public boolean updateProductShop(Product_ext_shop shop);
	
	public boolean addProductShop(Product_ext_shop shop);
	
	public boolean deletePromoteProduct(String productIds,String operId);
	
	
	/**
	 * 
	 * @param type  0查询所有  1查询关联产品     2查询能添加关联的产品        3查询能修改促销状态的产品  4查询所有促销产品  
	 * 				5查询产品详细 6查询商城商品详细 7查询能添加新品或者推荐的商城商品  8查询产品详细不包含子对象
	 * @param params
	 * @return
	 */
	public List<Product_ext_shop> getShopProductByMethod(int type,String ...params);
	
	public Map searchShopProductByProductShop(String searchKey,String ordercondition,int pageno,int pagesize);
	
	public List<Product_ext_shop> getProductShopPageByCondition(PagePojo pagePojo,String price,String order,String searchKey,String grade,String brands,String typestr);
	
}
