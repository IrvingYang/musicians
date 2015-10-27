package com.qushop.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qushop.user.entity.Oper;

public interface OperService {

	
	public Oper login(Oper oper,HttpServletRequest request);
	
	public void logout(Oper oper);
	
	/**
	 * 
	 * @param type 0查询所有 1查询详细
	 * @param params
	 * @return
	 */
	public List getOperByMethod(int type,Oper oper, String... params);
	
	public String addOper(Oper oper);
	
	public String deleteOper(String operId);

	public String deleteOperByProviderId(String providerIds);
	
	public boolean updateLoginPassword(Oper oper);
	
	public boolean updateDetailInfo(String email,String sex,String telephone,String operId);
	
	public List getOperByRoleId(String roleId);
	
	public boolean operExists(String operName);
	
}
