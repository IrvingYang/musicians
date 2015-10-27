package com.qushop.dict.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.dict.service.Brand_vendorService;

@Controller
@RequestMapping("dict/brand_vendor")
public class Brand_vendorController {

	@Resource
	Brand_vendorService brand_vendorService;
	
	@RequestMapping("getBrand_vendorByType.action")
	@ResponseBody
	public Object getBrand_vendorByType(Integer type,Integer typeId,Integer maxCount,HttpServletRequest request,HttpServletResponse response){
		Map map = new HashMap();
		List list = brand_vendorService.getVendorByMethod(type, maxCount+"");
		map.put("brand_vendorsList", list);
		return map;
	}
}
