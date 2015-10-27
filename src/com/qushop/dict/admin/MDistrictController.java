package com.qushop.dict.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.dict.entity.District;
import com.qushop.dict.service.CityService;
import com.qushop.dict.service.DistrictService;
import com.qushop.user.service.UserAddressService;


@Controller
@RequestMapping("/manage/district")
public class MDistrictController {
	
	@Resource
	DistrictService districtService;
	
	@Resource
	UserAddressService userAddressService;
	
	/**
	 * 
	 * @param typeId
	 * @param request
	 * @return
	 */
	@RequestMapping("getDistrictBycityId.do")
	@ResponseBody
	public Object getDistrictBycityId(String cityId,HttpServletRequest request){
		
		List<District> districtsList = null;
		districtsList = districtService.getDistrictByMethod(2, cityId);
		return districtsList;
	}
	
	@RequestMapping("lookDistrict.do")
	public Object lookDistrict(String cityId,HttpServletRequest request){
		
		List<District> districtsList = null;
		districtsList = districtService.getDistrictByMethod(2, cityId);
		request.setAttribute("districtsList", districtsList);
		return "admin/lookDialog/lookDistrict";
	}
	
	@RequestMapping("getAllDistrict.do")
	public String getAllDistrict(String action,HttpServletRequest request){
		
		List<District> districtsList = districtService.getDistrictByMethod(1);
		request.setAttribute("districtsList", districtsList);
		if("dialog".equals(action)){
			return "admin/lookDialog/lookDistrict";
		}
		return "admin/vdistrictList";
	}
	
	@RequestMapping("toEditDistrict.do")
	public String toEditDistrict(String action,String[] districtId,HttpServletRequest request){
		
		if("add".equals(action)){
			request.setAttribute("action", "add");
		}
		else if("update".equals(action)){
			request.setAttribute("action", "update");
			List<District> citiesList = districtService.getDistrictByMethod(3, districtId[0]);
			request.setAttribute("district", citiesList.get(0));
		}

		return "admin/editvDistrict";
	}
	
	@RequestMapping("saveOrUpdate.do")
	@ResponseBody
	public Object saveOrUpdate(String action,District district,HttpServletRequest request){
		
		String cityId = request.getParameter("district.cityId");
		district.setCityId(cityId);
		boolean bool = false;
		district.setValidflag((short)1);
		if("add".equals(action)){
			bool = districtService.addDistrict(district);
		}
		else if("update".equals(action)){
			bool = districtService.updateDistrict(district);
		}

		if(bool){
			return DwzUtil.opSuccess("操作成功", "district");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("deleteDistrict.do")
	@ResponseBody
	public Object deleteDistrict(String districtId,HttpServletRequest request){

//		查询时可以查看validflag=0的地区，此代码不需要
//		if(userAddressService.getUserAddressCountByDistrictId(districtId)>0){
//			return DwzUtil.opFailed("选中地区含绑定用户收货地址，不可删除", "");
//		}
		boolean bool = false;
		
		bool = districtService.deleteDistrict(districtId);

		if(bool){
			return DwzUtil.opSuccess("操作成功", "district");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
}
