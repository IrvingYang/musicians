package com.qushop.user.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.user.entity.UserAddress;
import com.qushop.user.service.UserAddressService;


@Controller
@RequestMapping("/manage/useraddress")
public class MUserAddressController {

	@Resource
	UserAddressService addressService;
	
	
	@RequestMapping("userAddressList.do")
	public String addressList(HttpServletRequest request){
		
		List<UserAddress> userAddressList = addressService.getUserAddressByMethod(0);
		request.setAttribute("userAddressList", userAddressList);
		return "admin/userAddressList";
	}
	
	@RequestMapping("toEditUserAddress.do")
	public String toEditUserAddress(String[] keywords,HttpServletRequest request){
		String[] words = keywords[0].split("and");
		List<UserAddress> userAddressList = addressService.getUserAddressByMethod(1,words[0],words[1]);
		if(userAddressList!=null && userAddressList.size()>0){
			request.setAttribute("userAddress", userAddressList.get(0));
		}
		return "admin/editUserAddress";
	}
	
	/**
	 * 
	 * @param keywords  "userid" and "addressid"
	 * @param request
	 * @return
	 */
	@RequestMapping("deleteUserAddress.do")
	@ResponseBody
	public Object deleteUserAddress(String[] keywords,HttpServletRequest request){
		
		boolean bool = false;
		for (String keyword : keywords) {
			//查分userid和收货地址id
			String[] keywordArray = keyword.split("and");
			bool = addressService.deleteUserAddress(keywordArray[0], keywordArray[1], request);
		}
		if(bool){
			return DwzUtil.opSuccess("操作成功", "useraddress");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("updateUserAddress.do")
	@ResponseBody
	public Object updateUserAddress(UserAddress userAddress,String userId,String userAddressId,HttpServletRequest request){
		
		String stateId = request.getParameter("useraddress.stateId");
		String cityId = request.getParameter("useraddress.cityId");
		String districtId = request.getParameter("useraddress.districtId");
		userAddress.setCityId(cityId);
		userAddress.setStateId(stateId);
		userAddress.setDistrictId(districtId);
		try {
			addressService.updateUserAddress(userAddress);
			return DwzUtil.opSuccess("操作成功", "useraddress");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
	
}




















