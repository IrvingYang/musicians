package com.qushop.prod.service;

import java.util.List;

import com.qushop.prod.entity.ProductImg;


public interface ProductImgService {
	
	/**
	 * 
	 * @param type 1通过productId 和类型查询图片 2查询长方形大图  3通过productId查询所有图片 4商品详情页图片 5查询商城使用图片 6查询产品所有长方形图片
	 * @param params
	 * @return
	 */
	public List<ProductImg> getProductImgByMethod(int type,String...params);

	/**
	 * 
	 * @param type 0删除正方形图片（1234）1删除长方形图片（56） 2删除所有图片 3通过productid删除多个
	 * @param params
	 * @return
	 */
	public boolean deleteProductImgPhysically(int type,String ...params);
	
	public boolean addProductImg(ProductImg productImg);
	
}
