package com.qushop.prod.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qushop.common.util.PagePojo;
import com.qushop.prod.entity.Product_ext_groupBuy;

public interface Product_ext_groupBuyService {

	/**
	 * @author: Stephen Xiao
	 * @param type: 1：按照产品一级类别查询   2：按照产品一级类别查询同类团购产品 3:按照groupbuyid取单个团购产品  4:查询所有 5:查询的团购信息包含已删除
	 * 6通过productId查询团购信息
	 * @see com.qushop.prod.service.Product_ext_groupBuyService#getgroupBuyProductByType(int, java.lang.String, int)
	 */
	public List<Product_ext_groupBuy>  getgroupBuyProductByMethod(int type, String ...params);
	
	public boolean updateProduct_ext_group(Product_ext_groupBuy groupBuy);
	
	public boolean addProduct_ext_group(Product_ext_groupBuy groupBuy);
	
	public boolean deleteProduct_ext_group(String groupbuyIds,HttpServletRequest request);
	
	public boolean openGroupBuy(String groupbuyId,Integer statusId,HttpServletRequest request);

	public boolean updateStockNumber(String groupbuyId,int number);
	
	public List<Product_ext_groupBuy> getPageGroupBuyList(PagePojo pagePojo);

}
