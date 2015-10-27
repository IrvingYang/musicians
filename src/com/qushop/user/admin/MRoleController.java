package com.qushop.user.admin;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.provider.entity.Provider;
import com.qushop.provider.service.ProviderService;
import com.qushop.user.entity.Role;
import com.qushop.user.entity.UrlList;
import com.qushop.user.service.OperService;
import com.qushop.user.service.RoleService;


@Controller
@RequestMapping("/manage/role")
public class MRoleController {

	
	@Resource
	RoleService roleserbvice;
	
	@Resource
	ProviderService providerService;
	
	@Resource
	OperService  operService;

	@RequestMapping("getAllRoleInfo.do")
	public String getAllRoleInfo(String type, String typeid,String action, HttpServletRequest request,HttpServletResponse response){

			List<Role> roleList = roleserbvice.getRoleList(type, request, typeid);
			request.setAttribute("roleList", roleList);
			switch (Integer.valueOf(type)){
				case 1:
					if("dialog".equals(action)){
						return "lookDialog/lookUpRole";
					}
					return "roleList";
				case 2:
					List list = new ArrayList();
					UrlList urlList = null;
					roleserbvice.getUrlList2(list,urlList,PublicUtil.getOper(request));
					request.setAttribute("arrangeRoleUrlList", list);
					return "roleAuthorityList";
			}

		return "admin/index";
	}
	
	@RequestMapping("toAddRole.do")
	public String toAddRole(HttpServletRequest request){
		List<Provider> providersList = providerService.getProviderByMethod(0,PublicUtil.getOper(request));
		request.setAttribute("providersList", providersList);
		return "admin/addRole";
	}
	
	@RequestMapping("addRole.do")
	@ResponseBody
	public Object addRole(Role role,HttpServletRequest request){

		List<Role> roleList = roleserbvice.getRoleList("2", request, role.getRoleId()+"and"+role.getProviderId());
		if(roleList.size()>0){
			return DwzUtil.opFailed("此角色已经存在", "");
		}
		String reStr = roleserbvice.addRole(role,PublicUtil.getOper(request));
		
		if("success".equals(reStr)){
			return DwzUtil.opSuccess("操作成功", "RoleInfo");
		}
		else if("idoverflow".equals(reStr)){
			return DwzUtil.opFailed("角色数量已经上限", "");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
	
	@RequestMapping("deleteRole.do")
	@ResponseBody
	public Object deleteRole(String roleId,HttpServletRequest request){
		
		//多个参数逗号拆分 例：“ 12345645and123456,123456and654321 ”
		String roleIds[] = roleId.split(",");
		for (String string : roleIds) {
			//然后拆分角色id和合作社id
			String[] key = string.split("and");
			List list = operService.getOperByRoleId(key[0]);
			if(list!=null && list.size()>0){
				return DwzUtil.opFailed("选中角色包含操作员信息，不可删除", "");
			}
		}
		
		String reStr = roleserbvice.deleteRole(roleId);
		
		if("success".equals(reStr)){
			return DwzUtil.opSuccess("操作成功", "RoleInfo");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("updateRolePermissions.do")
	@ResponseBody
	public Object updateRolePermissions(String urlId,String roleId,String providerId,HttpServletRequest request) throws IntrospectionException{

		String reStr = roleserbvice.updateRolePermissions(urlId, roleId, providerId, request);

		if("success".equals(reStr)){
			return DwzUtil.opSuccess("操作成功", "RoleInfo");
		}
		
		return DwzUtil.opFailed("操作失败", "");
	}
}













