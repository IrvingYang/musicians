package com.qushop.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qushop.user.entity.User;
import com.qushop.user.entity.User_Ext_Enterprise;
import com.qushop.user.entity.User_Ext_Personal;

public interface UserService {

	public int exists(String userName);
	
	public boolean login(User user,HttpServletRequest request);
	
	public void register(User user,HttpServletRequest request);
	
	/**
	 * 
	 * @param type 1查询普通用户列表  2查询企业用户列表  3查询普通用户详情 4查询企业用户详情  5查询没用通过审核的企业用户 6通过userid查询用户基础信息不包含子对象  
	 * 7查询普通用户详细不包user对象  	8查询企业用户详细不包user对象	 9通过username和phone查询基本信息  10通过手机查询信息 11查询用户基本信息包含validflag=0
	 * @param params
	 * @return
	 */
	public List<Object> getUserByMethod(int type,String...params);
	
	public boolean updateUser(User user);
	
	public boolean updateUser(int type,User_Ext_Personal personal,User_Ext_Enterprise enterprise,User user);
	
	public boolean deleteUser(int type, String userId,HttpServletRequest request);
	
	public User getBaseUser(String userId);
	
}
