package com.qushop.user.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.order.service.Order_listService;
import com.qushop.user.entity.Enterprise_image;
import com.qushop.user.entity.User;
import com.qushop.user.entity.User_Ext_Enterprise;
import com.qushop.user.entity.User_Ext_Personal;
import com.qushop.user.service.Enterprise_imageService;
import com.qushop.user.service.UserService;


@Controller
@RequestMapping("/manage/user")
public class MUserController {

	@Resource
	UserService userService;
	
	@Resource
	Enterprise_imageService enterprise_imageService;
	
	@Resource
	Order_listService orderlistService;
	
	@Resource
	CommonDao commonDao;
	
	
	@RequestMapping("getUserListByUserType.do")
	public String getUserListByUserType(Integer userType,HttpServletRequest request){
		
		List<Object> usersList = null;
		String redirectPage = "";
		switch (userType) {
		case 1:
			usersList = userService.getUserByMethod(1);
			redirectPage="admin/userList";
			break;
		case 2:
			usersList = userService.getUserByMethod(2);
			redirectPage="admin/entUserList";
			break;

		default:
			break;
		}
		request.setAttribute("usersList", usersList);
		return redirectPage;
	}
	
	@RequestMapping("toEditUser.do")
	public String toEditUser(Integer userType,String[] userId,HttpServletRequest request){

		String redirectPage = "";
		List<Object> usersList = null;
		switch (userType) {
		//普通用户详细
		case 1:
			usersList = userService.getUserByMethod(3,userId[0]);
			redirectPage="editUser";
			break;
		//企业用户详细
		case 2:
			List<Enterprise_image> imagesList = enterprise_imageService.getImageByMethod(1, userId[0]);
			for (int i = 0; i < imagesList.size(); i++) {
				request.setAttribute("imageURl"+(i+1), imagesList.get(i).getImagepath());
			}
			usersList = userService.getUserByMethod(4,userId[0]);
			redirectPage="editEntUser";
			break;
		//企业用户详情查询2用做审核
		case 3:
			List<Enterprise_image> validImage = enterprise_imageService.getImageByMethod(1, userId[0]);
			for (int i = 0; i < validImage.size(); i++) {
				request.setAttribute("imageURl"+(i+1), validImage.get(i).getImagepath());
			}
			usersList = userService.getUserByMethod(4,userId[0]);
			redirectPage="validationEntUser";
		default:
			break;
		}
		request.setAttribute("user", usersList.get(0));
		return redirectPage;
		
	}
	@RequestMapping("editUser.do")
	@ResponseBody
	public Object editUser(Integer userType,User_Ext_Personal personal,User_Ext_Enterprise enterprise,String email,HttpServletRequest request){

		boolean bool = false;
		try {
			User baseUser = userService.getBaseUser(personal.getUserId());
			baseUser.setEmail(email);
			baseUser.setOperid(PublicUtil.getOper(request).getOperId());
			baseUser.setLastUpdateTime(new Date());
			switch (userType) {
			case 1:
				personal.setOperid(PublicUtil.getOper(request).getOperId());
				personal.setLastUpdateTime(new Date());
				bool = userService.updateUser(1, personal, null, baseUser);
				if(bool){
					return DwzUtil.opSuccess("操作成功", "user");
				}
				return DwzUtil.opFailed("操作失败", "");
			case 2:
				enterprise_imageService.deleteRellayImageByUserId(personal.getUserId());
				String imagePath1 = request.getParameter("image1");
				String imagePath2 = request.getParameter("image2");
				String imagePath3 = request.getParameter("image3");
				
				Enterprise_image enterprise_image1 = new Enterprise_image();
				enterprise_image1.setImageid(personal.getUserId()+"01");
				enterprise_image1.setImagepath(imagePath1);
				enterprise_image1.setUploadtime(new Date());
				enterprise_image1.setUserId(personal.getUserId());
				
				Enterprise_image enterprise_image2 = new Enterprise_image();
				enterprise_image2.setImageid(personal.getUserId()+"02");
				enterprise_image2.setImagepath(imagePath2);
				enterprise_image2.setUploadtime(new Date());
				enterprise_image2.setUserId(personal.getUserId());
				
				Enterprise_image enterprise_image3 = new Enterprise_image();
				enterprise_image3.setImageid(personal.getUserId()+"03");
				enterprise_image3.setImagepath(imagePath3);
				enterprise_image3.setUploadtime(new Date());
				enterprise_image3.setUserId(personal.getUserId());
				
				enterprise_imageService.addEnterprise_image(enterprise_image1);
				enterprise_imageService.addEnterprise_image(enterprise_image2);
				enterprise_imageService.addEnterprise_image(enterprise_image3);
				
				enterprise.setCertid1imageid(enterprise_image1.getImageid());
				enterprise.setCertid2imageid(enterprise_image2.getImageid());
				enterprise.setCertid3imageid(enterprise_image3.getImageid());
				enterprise.setOperid(PublicUtil.getOper(request).getOperId());
				enterprise.setLastUpdateTime(new Date());
				bool = userService.updateUser(2, null, enterprise, baseUser);if(bool){
					return DwzUtil.opSuccess("操作成功", "entuser");
				}
				return DwzUtil.opFailed("操作失败", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return false;
		
	}
	
	@RequestMapping("deleteUser.do")
	@ResponseBody
	public Object deleteUser(Integer userType,String userId,HttpServletRequest request){
		
		List list = orderlistService.getOrder_listByMethod(PublicUtil.getOper(request),7, null, userId);
		if(list!=null && list.size()>0){
			return DwzUtil.opFailed("用户含有未完成的订单，操作拒绝", "");
		}
		boolean bool = false;
		switch (userType) {
		case 1:
			bool = userService.deleteUser(1, userId, request);
			if(bool){
				return DwzUtil.opSuccess("操作成功", "user");
			}
			return DwzUtil.opFailed("操作失败", "");
		case 2:
			bool = userService.deleteUser(2, userId, request);
			if(bool){
				return DwzUtil.opSuccess("操作成功", "entuser");
			}
			return DwzUtil.opFailed("操作失败", "");
		}
		return bool;
		
	}
	
	@RequestMapping("getNotThroughValidateEntUser.do")
	public String getNotThroughValidateEntUser(HttpServletRequest request){
		
		List enUser = userService.getUserByMethod(5);
		request.setAttribute("usersList", enUser);
		return "admin/entValidUserList";
	}
	

	@RequestMapping("validateEntUser.do")
	@ResponseBody
	public Object validateEntUser(String[] userId,Integer type,HttpServletRequest request){
		
		boolean bool = false;
		List<Object> usersList = userService.getUserByMethod(4,userId[0]);
		User user = ((User_Ext_Enterprise)usersList.get(0)).getUser();
		user.setLastUpdateTime(new Date());
		user.setOperid(PublicUtil.getOper(request).getOperId());
		User_Ext_Enterprise enterprise = (User_Ext_Enterprise) usersList.get(0);
		enterprise.setLastUpdateTime(new Date());
		enterprise.setOperid(PublicUtil.getOper(request).getOperId());
		switch (type) {
		//没通过验证
		case 0:
			user.setStatus((short)4);
			break;
		//通过验证
		case 1:
			user.setStatus((short)1);
			break;
		}
		try {
			commonDao.update(enterprise);
			bool = userService.updateUser(user);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		if(bool){
			return DwzUtil.opSuccess("操作成功", "entuser");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
	
}

















