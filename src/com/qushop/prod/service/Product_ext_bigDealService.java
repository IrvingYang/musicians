package com.qushop.prod.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qushop.prod.entity.Product_ext_bigDeal;

public interface Product_ext_bigDealService {

	/**
	 * @author : Stephen Xiao
	 * @param type: 1：按照产品一级类别查询   2：按照产品一级类别查询同类大宗交易产品 3:按照bigdealid取单个大宗交易产品  4查看所有  5按照bigdealid取单个大宗交易产包含已删除 
	 * 6通过productId 查询大宗交易信息
	 * @return
	 */
	public List<Product_ext_bigDeal> getbigDealProductByMethod(int type, String ...params);

	/**
	 * 
	 * @param type 1查询全部，2查询详情 3查询已经删除的大宗交易
	 * @param params
	 * @return
	 */
//	public List<Product_ext_bigDeal> getProductBigDealByMethod(int type,String...params);
	
	public boolean addProductBigDeal(Product_ext_bigDeal bigDeal);
	
	public boolean updateProductBigDeal(Product_ext_bigDeal bigDeal);
	
	public boolean deleteProductBigDeal(String bigDealIds,HttpServletRequest request);
	

}
