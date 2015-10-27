package com.qushop.dict.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.dict.entity.District;
import com.qushop.dict.service.DistrictService;

@Controller
@RequestMapping("/dict/district")
public class DistrictController {
	
	@Resource
	DistrictService districtService;
	
	@RequestMapping("getDistrictByCityId.action")
	@ResponseBody
	public Object getCityByStateId(String cityId,HttpServletRequest request){
		
		List<District> districtsList = districtService.getDistrictByMethod(2, cityId);
		
		return districtsList;
		
	}
}
