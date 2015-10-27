package com.qushop.prod.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qushop.prod.entity.Product_ext_recommProd;
import com.qushop.prod.service.Product_ext_recommProdService;


@Controller
@RequestMapping("/eshop/product_ext_recommProd")
public class Product_ext_recommProdController {

	@Resource
	Product_ext_recommProdService product_ext_recommProdService;
	
	@RequestMapping("getRecommProduct.action")
	@ResponseBody
	public Object getProduct_ext_recommProd(Integer maxCount,HttpServletRequest request,HttpServletResponse response){
		
		List<Product_ext_recommProd> recommProdsList = null;
		if(maxCount==null){
			recommProdsList = product_ext_recommProdService.getRecommProductByMethod(0);
		}else{
			recommProdsList = product_ext_recommProdService.getRecommProductByMethod(4,maxCount+"");
		}
		return recommProdsList;
	}
	
	@RequestMapping("getRecommProductDetailed.action")
	@ResponseBody
	public Object getRecommProductDetailed(String productId,HttpServletRequest request,HttpServletResponse response){
		List<Product_ext_recommProd> ext_recommProdsList = product_ext_recommProdService.getRecommProductByMethod(1, productId);
		if(ext_recommProdsList!=null && ext_recommProdsList.size()>0){
			return ext_recommProdsList.get(0);
		}
		return null;
	}
}
