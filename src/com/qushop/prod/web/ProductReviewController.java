package com.qushop.prod.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.Constants;
import com.qushop.prod.entity.ProductReview;
import com.qushop.prod.pojo.PagingListPojo;
import com.qushop.prod.service.ProductReviewService;
import com.qushop.user.entity.User_Ext_Personal;



@Controller
@RequestMapping("/eshop/productreview")
public class ProductReviewController {

	@Resource
	ProductReviewService service;

	
	@RequestMapping("getProductReviewById.action")
	@ResponseBody
	public Object getProductDetailedById(String productId, int offset, int limit,HttpServletRequest request,HttpServletResponse response){
		List<ProductReview> productreviewlist = service.getProductReviewByMethod(1,productId, offset/limit+"",limit+"");
		List<ProductReview> productreviewlistForAllSize = service.getProductReviewByMethod(1,productId);

		
		int size = productreviewlistForAllSize==null?0:productreviewlistForAllSize.size();
		PagingListPojo pagingListPojo=new PagingListPojo(productreviewlist, size);
		return pagingListPojo;
	}
	
	@RequestMapping("addProductReview.action")
	@ResponseBody
	public Object addProductReview(String productId,Integer rate,String productComments,String orderId,HttpServletRequest request){
		
		Object obj = request.getSession().getAttribute(Constants.LOGIN_USER);
		if(obj==null){
			return "needLogin";
		}else{
			User_Ext_Personal user = (User_Ext_Personal) obj;
			return service.addProductReview(productId,rate,productComments,orderId,user.getUserId());
		}
		
	}
}




















