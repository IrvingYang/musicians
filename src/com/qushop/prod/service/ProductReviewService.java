package com.qushop.prod.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qushop.prod.entity.ProductReview;
import com.qushop.user.entity.Oper;

public interface ProductReviewService {
	
	/**
	 * 
	 * @param type 0 表示插叙所有 1查询商品评价
	 * @param params
	 * @return
	 */
	public List<ProductReview> getProductReviewByMethod(int type,String ...params);
	
	public boolean deleteProductReview(String reviewId,HttpServletRequest request);

	public boolean deleteProductReviewByProduct(String productId,Oper oper);
	
	public List<ProductReview> getProductReviewPaging(String productId,Integer pageNo,Integer pageSize);
	
	public boolean addProductReview(String productId,Integer rate,String productComments,String orderId,String userId);
	
	public List<ProductReview> getProductReviewByOrderId(String orderId);
	
}
