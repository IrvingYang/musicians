package com.qushop.prod.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qushop.prod.entity.ProductType;
import com.qushop.prod.service.ProductTypeService;


@Controller
@RequestMapping("/eshop/product_type")
public class Product_typeController {
	@Resource
	ProductTypeService productTypeService;
	
	
	@RequestMapping("getAllProductType.action")
	@ResponseBody
	public Object getAllProductType(HttpServletRequest request,HttpServletResponse response){
		
		List productypeNodeList = productTypeService.getProductType();
		return productypeNodeList;

//		List levelProductTypeList = new ArrayList();
//		productTypeService.getProductType2(levelProductTypeList, null);
//		System.out.println(JSONObject.toJSON(levelProductTypeList));
//		return levelProductTypeList;
		
	}
	@RequestMapping("getTopProductType.action")
	@ResponseBody
	public Object getTopProductType(HttpServletRequest request,HttpServletResponse response){
		
		List productypeNodeList = productTypeService.getProductTypeByMethod(5);
		
		return productypeNodeList;
		
	}	
	@RequestMapping("getProductTypedetailed.action")
	@ResponseBody
	public Object getProductTypedetailedById(String productTypeId,HttpServletRequest request){
		
		ProductType productType = productTypeService.getProductTypeByMethod(0, productTypeId).get(0);
		return productType;
	}
	
	@RequestMapping("getLevelProductType.action")
	@ResponseBody
	public Object getLevelProductType(String productTypeId,HttpServletRequest request){
		
		List<ProductType> productTypesList = productTypeService.getProductTypeByMethod(1, productTypeId);
		return productTypesList;
	}
	
	@RequestMapping("getNavigation.action")
	@ResponseBody
	public Object getNavigation(String productTypeId,HttpServletRequest request,HttpServletResponse response){
		List<ProductType> productTypesList =  productTypeService.getProductTypeByMethod(6,productTypeId);
		return productTypesList;
	}
	/***===================================================================================================*/
	
	@RequestMapping("getProductType.action")
	public String getProductType(){
		
		return "";
	}
}
