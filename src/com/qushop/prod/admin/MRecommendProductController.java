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
import com.qushop.common.util.PublicUtil;
import com.qushop.prod.entity.Product_ext_recommProd;
import com.qushop.prod.service.Product_ext_recommProdService;




@RequestMapping("/manage/recommendProduct")
@Controller
public class MRecommendProductController {
	
	@Resource
	Product_ext_recommProdService recommProdService;
	
	@RequestMapping("addRecomendProduct.do")
	@ResponseBody
	public Object addRecomendProduct(Product_ext_recommProd recommProd, HttpServletRequest request) throws ParseException{
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String recommBeginTime = request.getParameter("beginTime");
		String recommEndTime = request.getParameter("endTime");
		String productId = request.getParameter("shop.productId");
		String productName = request.getParameter("shop.productName");
		
		if(Integer.parseInt(recommProdService.getRecommProductByMethod(3, productId).get(0)+"")>0){
			return DwzUtil.opFailed("推荐产品已经存在，请勿重复添加", "");
		}
		Date begintime = null;
		Date endtime = null;
		if(recommBeginTime!=null && !recommBeginTime.equals("")){
			begintime = format.parse(recommBeginTime);
		}
		if(recommEndTime!=null && !recommEndTime.equals("")){
			endtime = format.parse(recommEndTime);
		}
		boolean bool = false;
		List<Product_ext_recommProd> rList = recommProdService.getRecommProductByMethod(2, productId);
		if(rList!=null && rList.size()>0){
			Product_ext_recommProd recommProd2 = rList.get(0);
			recommProd2.setRecommBeginTime(begintime);
			recommProd2.setProductId(productId);
			recommProd2.setRecommEndTime(endtime);
			recommProd2.setOperid(PublicUtil.getOper(request).getOperId());
			recommProd2.setValidflag((short)1);
			recommProd2.setLastUpdateTime(new Date());
			recommProd2.setProductName(productName);
			bool = recommProdService.updateRecommendProduct(recommProd2);
		}else{
			recommProd.setProductId(productId);
			recommProd.setRecommBeginTime(begintime);
			recommProd.setProductId(productId);
			recommProd.setRecommEndTime(endtime);
			recommProd.setOperid(PublicUtil.getOper(request).getOperId());
			recommProd.setValidflag((short)1);
			recommProd.setProductName(productName);
			recommProd.setLastUpdateTime(new Date());
			bool = recommProdService.addRecommendProduct(recommProd);
		}
		if(bool){
			return DwzUtil.opSuccess("操作成功", "recommendProduct");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("updateRecomendProduct.do")
	@ResponseBody
	public Object updateRecomendProduct(Product_ext_recommProd recommProd, HttpServletRequest request) throws ParseException{
		

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String recommBeginTime = request.getParameter("beginTime");
		String recommEndTime = request.getParameter("endTime");
		String productId = request.getParameter("shop.productId");
		String productName = request.getParameter("shop.productName");
		Date begintime = null;
		Date endtime = null;
		if(recommBeginTime!=null && !recommEndTime.equals("")){
			begintime = format.parse(recommBeginTime);
		}
		if(recommEndTime!=null && !recommEndTime.equals("")){
			endtime = format.parse(recommEndTime);
		}
		recommProd.setProductName(productName);
		recommProd.setProductId(productId);
		recommProd.setRecommBeginTime(begintime);
		recommProd.setProductId(productId);
		recommProd.setRecommEndTime(endtime);
		recommProd.setOperid(PublicUtil.getOper(request).getOperId());
		recommProd.setValidflag((short)1);
		recommProd.setLastUpdateTime(new Date());
		boolean bool = recommProdService.updateRecommendProduct(recommProd);
		if(bool){
			return DwzUtil.opSuccess("操作成功", "recommendProduct");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("recommendProductList.do")
	public String recommendProductList(HttpServletRequest request){
		List<Product_ext_recommProd> recommProdsList = recommProdService.getRecommProductByMethod(0);
		request.setAttribute("recommProdsList", recommProdsList);
		return "/admin/recommendProductList";
	}
	
	@RequestMapping("toAddRecomendProduct.do")
	public String toAddRecomendProduct(HttpServletRequest request){
		request.setAttribute("action", "add");
		return "/admin/editRecommendProduct";
	}
	@RequestMapping("toEditRecommendProduct.do")
	public String toEditRecommendProduct(String productId,HttpServletRequest request){
		productId = productId.split(",")[0];
		List<Product_ext_recommProd> recommProdsList = recommProdService.getRecommProductByMethod(1, productId);
		if(recommProdsList!=null && recommProdsList.size()>0){
			request.setAttribute("recommend", recommProdsList.get(0));
		}
		request.setAttribute("action", "update");
		return "/admin/editRecommendProduct";
	}
	@RequestMapping("deleteRecommendProduct.do")
	@ResponseBody
	public Object deleteRecommendProduct(String productId,HttpServletRequest request){
		
		boolean bool= recommProdService.deleteRecommendProduct(productId,PublicUtil.getOper(request).getOperId());
		if(bool){
			return DwzUtil.opSuccess("操作成功", "recommendProduct");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
}
