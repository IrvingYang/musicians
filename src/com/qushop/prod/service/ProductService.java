package com.qushop.prod.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qushop.prod.entity.Product;
import com.qushop.user.entity.Oper;

public interface ProductService {
	
	
	/**
	 * 
	 * @param type 0 表示前台查询  1表示后台操作 2查询详情 3通过providerId查询产品  4通过productId查询详细不包含子对象
	 * @param oper 后台操作员
	 * @return
	 */
	public List<Product> getProductListByMethod(Integer type,Oper oper,String ...params);
	
	/**
	 * 
	 * @param type 0 新增 1修改
	 * @param images
	 * @param product
	 * @param request
	 * @return
	 */
	public boolean addorupdate(int type,String [] images,Product product,HttpServletRequest request);

	public boolean deleteProduct(String productId,Oper oper);
	
	public boolean existsProductInProductType(String productTypeId);
	
	/**
	 * 0 查询允许商城的商品 1查询允许团购的商品 2查询允许大宗交易的商品
	 * @return
	 */
	public List<Product> getAllowProducts(int type,String ...params);
	
	public boolean updateStockNumber(String productId,int number);
	
	public Integer getProductCountByBrandVendor(String brandId);
	
	public Integer getProductCountByCityId(String cityId);
	
}
