package com.qushop.dict.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.dict.entity.Country;
import com.qushop.dict.entity.State;
import com.qushop.dict.service.CityService;
import com.qushop.dict.service.CountryService;
import com.qushop.dict.service.StateService;


@Controller
@RequestMapping("/manage/country")
public class MContryController {

	@Resource
	CountryService countryService;
	
	@RequestMapping("getCountryList.do")
	public String getStateConnectCityList(String action,HttpServletRequest request){
		
		List<Country> statesList = countryService.getAllCountries();
		request.setAttribute("countryList", statesList);
		if("dialog".equals(action)){
		return "admin/lookDialog/lookUpCountry";
		}
		return "admin/vcountryList";
	}
	
	@RequestMapping("toEdit.do")
	public String toEditState(String action,Integer countryId,HttpServletRequest request){
		
		if("add".equals(action)){
			request.setAttribute("action", "add");
		}
		else if("update".equals(action)){
			request.setAttribute("action", "update");
			Country country = countryService.getCountriesById(countryId);
			request.setAttribute("country",country);
		}

		return "admin/editCountry";
	}
	
	@RequestMapping("saveOrUpdate.do")
	@ResponseBody
	public Object saveOrUpdate(String action,Country country,HttpServletRequest request){
		
		boolean bool = false;
		country.setValidflag((short)1);
		if("add".equals(action)){
			bool = countryService.add(country);
		}
		else if("update".equals(action)){
			bool = countryService.update(country);
		}

		if(bool){
			return DwzUtil.opSuccess("操作成功", "country");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("delete.do")
	@ResponseBody
	public Object deleteState(String countryId,HttpServletRequest request){
		boolean bool = false;
		bool = countryService.delete(countryId);
		if(bool){
			return DwzUtil.opSuccess("操作成功", "country");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
	
}
