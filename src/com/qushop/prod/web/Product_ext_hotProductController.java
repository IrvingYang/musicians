package com.qushop.prod.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qushop.prod.service.Product_ext_hotProductService;

@Controller
@RequestMapping("/eshop/product_ext_hotProduct")
public class Product_ext_hotProductController {

	@Resource
	Product_ext_hotProductService hotProductService;
	
	@RequestMapping("getHotProduct.action")
	@ResponseBody
	public Object getHotProduct(Integer type,String typeId,Integer maxCount,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = hotProductService.getHotProductByType(type,typeId,maxCount);
		return map;
	}
}
