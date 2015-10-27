package com.qushop.dict.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.dict.entity.City;
import com.qushop.dict.entity.Location;
import com.qushop.dict.service.CityService;
import com.qushop.dict.service.DistrictService;
import com.qushop.dict.service.LocationService;
import com.qushop.prod.service.ProductService;
import com.qushop.provider.service.ProviderService;


@Controller
@RequestMapping("/manage/city")
public class MCityController {
	
	@Resource
	CityService cityService;
	
	@Resource
	DistrictService districtService;
	
	@Resource
	ProductService productService;
	
	@Resource
	ProviderService providerService;
	
	/**
	 * 
	 * @param type 1通过stateId查询城市名
	 * @param typeId
	 * @param request
	 * @return
	 */
	@RequestMapping("getCityBystateId.do")
	@ResponseBody
	public Object getCityByTypeId(String stateId,HttpServletRequest request){
		
		List<City> citysList = null;
		citysList = cityService.getCityByMethod(2,stateId);
		return citysList;
	}
	
	@RequestMapping("getAllCity.do")
	public String getAllCity(String action,HttpServletRequest request){
		
		List<City> citiesList = cityService.getCityByMethod(1);
		request.setAttribute("cityList", citiesList);
		if("dialog".equals(action)){
			return "admin/lookDialog/lookCity";
		}
		return "admin/vcityList";
	}
	
	@RequestMapping("toEditCity.do")
	public String toEditCity(String action,String[] cityId,HttpServletRequest request){
		
		if("add".equals(action)){
			request.setAttribute("action", "add");
		}
		else if("update".equals(action)){
			request.setAttribute("action", "update");
			List<City> citiesList = cityService.getCityByMethod(4, cityId[0]);
			request.setAttribute("city", citiesList.get(0));
		}

		return "admin/editvCity";
	}
	
	@RequestMapping("saveOrUpdate.do")
	@ResponseBody
	public Object saveOrUpdate(String action,City city,Double longitude,Double latitude,HttpServletRequest request){
		
		String stateId = request.getParameter("citys.stateId");
		city.setStateId(stateId);
		boolean bool = false;
		city.setValidflag((short)1);
		if("add".equals(action)){
			bool = cityService.addCity(city,longitude,latitude);
		}
		else if("update".equals(action)){
			bool = cityService.updateCity(city,longitude,latitude);
		}

		if(bool){
			return DwzUtil.opSuccess("操作成功", "city");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("deleteCity.do")
	@ResponseBody
	public Object deleteCity(String cityId,HttpServletRequest request){

		boolean bool = false;
		if(productService.getProductCountByCityId(cityId)>0){
			return DwzUtil.opFailed("该城市下存在产品绑定，不能删除", "city");
		}
		else if(providerService.getProviderByCityId(cityId)>0){
			return DwzUtil.opFailed("该城市存在合作社绑定，不能删除", "city");
		}
		else if(districtService.getDistrictByMethod(2, cityId).size()>0){
			return DwzUtil.opFailed("该城市下含有区县信息，不能删除", "city");
		}
		bool = cityService.deleteCity(cityId);

		if(bool){
			return DwzUtil.opSuccess("操作成功", "city");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
}
