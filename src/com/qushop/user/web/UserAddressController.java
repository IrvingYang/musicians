package com.qushop.user.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.Constants;
import com.qushop.user.entity.UserAddress;
import com.qushop.user.entity.User_Ext_Personal;
import com.qushop.user.service.UserAddressService;

@Controller
@RequestMapping("user/userAddress")
public class UserAddressController {

	@Resource
	UserAddressService userAddressService;

	@RequestMapping("getUserAddressByUserId.action")
	@ResponseBody
	public Object getUserAddressByUserId(HttpServletRequest request) {

		String userId = "";
		Object obj = request.getSession().getAttribute(Constants.LOGIN_ENTER_UER);
		if (obj == null) {
			obj = request.getSession().getAttribute(Constants.LOGIN_USER);
			if (obj == null) {
				return "needlogin";
			} else {
				User_Ext_Personal user_Ext_Personal = (User_Ext_Personal) obj;
				userId = user_Ext_Personal.getUserId();
			}
		} else {
			return "entUser";
		}

		List<UserAddress> userAddressesList = userAddressService.getUserAddressByUserId(userId);

		return userAddressesList;
	}

	@RequestMapping("getUserAddressByUserAddressId.action")
	@ResponseBody
	public Object getUserAddressByUserAddressId(String userAddressId, HttpServletRequest request) {
		return userAddressService.getUserAddressByMethod(2, userAddressId);
	}

	@RequestMapping("deleteUserAddress.action")
	@ResponseBody
	public Object deleteUserAddress(String userAddressId, String userId, HttpServletRequest request) {

		Object obj = request.getSession().getAttribute(Constants.LOGIN_ENTER_UER);
		if (obj == null) {
			obj = request.getSession().getAttribute(Constants.LOGIN_USER);
			if (obj == null) {
				return "needlogin";
			} else {
				User_Ext_Personal user_Ext_Personal = (User_Ext_Personal) obj;
				userId = user_Ext_Personal.getUserId();
			}
		} else {
			return "entUser";
		}

		boolean bool = userAddressService.deleteUserAddress(userId, userAddressId, request);
		if (bool) {
			return "success";
		}
		return "failed";
	}

	@RequestMapping("addUserAddress.action")
	@ResponseBody
	public Object addUserAddress(UserAddress userAddress, HttpServletRequest request) {

		Object obj = request.getSession().getAttribute(Constants.LOGIN_USER);
		if (obj == null) {
			obj = request.getSession().getAttribute(Constants.LOGIN_ENTER_UER);
			if (obj == null) {
				return "needLogin";
			} else {
				return "entUser";
			}
		}
		User_Ext_Personal personal = (User_Ext_Personal) obj;
		userAddress.setUserId(personal.getUserId());
		userAddress.setValidflag((short) 1);
		try {
			userAddressService.addUserAddress(userAddress, request);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping("updateUserAddress.do")
	@ResponseBody
	public void updateUserAddress(UserAddress userAddress, HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(Constants.LOGIN_USER);
		User_Ext_Personal personal = (User_Ext_Personal) obj;
		userAddress.setUserId(personal.getUserId());
		userAddress.setValidflag((short) 1);
		userAddressService.updateUserAddress(userAddress);
	}

	@RequestMapping("setDefaultUserAddress.do")
	@ResponseBody
	public void setDefaultUserAddress(String userAddressId, HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(Constants.LOGIN_USER);
		User_Ext_Personal personal = (User_Ext_Personal) obj;
		UserAddress defaultUserAddress = userAddressService.getDefaultUserAddress(personal.getUserId());
		defaultUserAddress.setDefaultflag((short)0);
		userAddressService.updateUserAddress(defaultUserAddress);
		List<UserAddress> userAddressByMethod = userAddressService.getUserAddressByMethod(1, personal.getUserId(),
				userAddressId);
		UserAddress userAddress = userAddressByMethod.get(0);
		userAddress.setDefaultflag((short) 1);
		userAddressService.updateUserAddress(userAddress);
	}

}
