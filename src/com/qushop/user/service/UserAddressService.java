package com.qushop.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qushop.user.entity.UserAddress;

public interface UserAddressService {

	public void addUserAddress(UserAddress userAddress,HttpServletRequest request);
	
	public List<UserAddress> getUserAddressByUserId(String userId);
	
	public void updateUserAddress(UserAddress userAddress);
	
	
	/**
	 * 
	 * @param type 0查询所有收货地址 1，查询收货地址详细 2通过useraddressid查询收货地址 3查询详细包含已删除
	 * @param params
	 * @return
	 */
	public List<UserAddress> getUserAddressByMethod(int type,String...params);
	
	public boolean deleteUserAddress(String userId,String userAddressId,HttpServletRequest request);
	
	public Integer getUserAddressCountByDistrictId(String districtId);
	
	public boolean deleteUserAddressByUserIds(String userIds);
	
	public UserAddress getDefaultUserAddress(String userId);
	
}
