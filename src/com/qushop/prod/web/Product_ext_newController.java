package com.qushop.prod.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qushop.prod.entity.Product_ext_new;
import com.qushop.prod.service.Product_ext_newService;


@Controller
@RequestMapping("/eshop/product_ext_new")
public class Product_ext_newController {

	@Resource
	Product_ext_newService product_ext_newService;
	
	@RequestMapping("getProduct_ext_new.action")
	@ResponseBody
	public Object product_ext_newProduct(String maxcount,HttpServletRequest request,HttpServletResponse response){
		
		List<Product_ext_new> ext_newsList = product_ext_newService.getNewProductByMethod(0,maxcount);
		return ext_newsList;
	}
	
	@RequestMapping("getNewProductDetailed.action")
	@ResponseBody
	public Object getNewProductDetailed(String productId, HttpServletRequest request,HttpServletResponse response){
		
		List<Product_ext_new> product_ext_newsList = product_ext_newService.getNewProductByMethod(1,productId);
		if(product_ext_newsList!=null && product_ext_newsList.size()>0){
			return product_ext_newsList.get(0);
		}
		return null;
	}
}
