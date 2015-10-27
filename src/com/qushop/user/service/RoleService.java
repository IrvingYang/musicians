package com.qushop.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qushop.user.entity.Oper;
import com.qushop.user.entity.Role;
import com.qushop.user.entity.UrlList;

public interface RoleService {

	/**
	 * 
	 * @param type 1查询全部Role, 2查询指定Roleid,  3查询无效的角色信息
	 * @param params
	 * @return
	 */
	public List<Role> getRoleList(String type,HttpServletRequest request,String...params);

	public List getUrlList(List list, UrlList currurllist);

	public List getUrlList2(List list,UrlList currentUrlList,Oper oper);
	
	public String addRole(Role role, Oper oper);
	
	public String deleteRole(String roleId);

	public String deleteRoleByProviderId(String providerIds);
	
	public String updateRolePermissions(String urlId,String roleId,String providerId,HttpServletRequest request);
	
}
