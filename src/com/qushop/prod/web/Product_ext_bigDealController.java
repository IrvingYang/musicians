package com.qushop.prod.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.prod.service.Product_ext_bigDealService;
import com.qushop.prod.service.Product_ext_groupBuyService;

@Controller
@RequestMapping("/eshop/bigDealProduct")
public class Product_ext_bigDealController {

	@Resource
	Product_ext_bigDealService service;

	@RequestMapping("getbigDealProductByType.action")
	@ResponseBody
	public Object getbigDealProductByType(int type, String typeId, int maxcount,
			HttpServletRequest request,	HttpServletResponse response) {
		return service.getbigDealProductByMethod(type, typeId, maxcount+"");
	}

	
}
