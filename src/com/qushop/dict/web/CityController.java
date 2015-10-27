package com.qushop.dict.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.dict.entity.City;
import com.qushop.dict.service.CityService;
import com.qushop.dict.service.LocationService;

@Controller
@RequestMapping("/dict/city")
public class CityController {
	
	@Resource
	CityService cityService;
	
	@Resource
	LocationService locationService;
	
	@RequestMapping("getCityByCityId.action")
	@ResponseBody
	public Object getCityByCityId(String cityId,HttpServletRequest request){
		List<City> citiesList = cityService.getCityByMethod(3, cityId);
		if(citiesList!=null && citiesList.size()>0){
			return citiesList.get(0);
		}
		return null;
	}
	
	@RequestMapping("getCityByStateId.action")
	@ResponseBody
	public Object getCityByStateId(String stateId,HttpServletRequest request){
		
		List<City> cityList = cityService.getCityByMethod(2, stateId);
		return cityList;
		
	}

	@RequestMapping("getCityLocationByEntityIdAndEntityType.action")
	@ResponseBody
	public List getCityLocationByEntityIdAndEntityType(String entityId,String entityType){
		
		return locationService.getLocationByMethod(1, entityType,entityId);
	}

}
