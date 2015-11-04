package com.qushop.prod.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.prod.entity.ProductReview;
import com.qushop.prod.service.ProductReviewService;



@Controller
@RequestMapping("/manage/review")
public class MProductReviewController {

	@Resource
	ProductReviewService service;

	
	@RequestMapping("getAllProductReview.do")
	public String getAllProductReview(HttpServletRequest request){
		
		List<ProductReview> productReviewsList = service.getProductReviewByMethod(0);
		request.setAttribute("reviewsList", productReviewsList);
		return "admin/productReviewList";
	}
	
	@RequestMapping("deleteProductReview.do")
	@ResponseBody
	public Object deleteProductReview(String[] deleteKeyWord,HttpServletRequest request) throws ParseException{

		boolean bool = false;
		//String keyWords[] = deleteKeyWord.split(",");
		
		for (String key : deleteKeyWord) {
			bool = service.deleteProductReview(key,request);
		}
		if(bool){
			return DwzUtil.opSuccess("操作成功", "review");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
	
}




















