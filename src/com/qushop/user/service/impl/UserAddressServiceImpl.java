package com.qushop.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.dict.entity.District;
import com.qushop.dict.service.CityService;
import com.qushop.dict.service.DistrictService;
import com.qushop.user.entity.UserAddress;
import com.qushop.user.entity.User_Ext_Personal;
import com.qushop.user.service.UserAddressService;
import com.qushop.user.service.UserService;

@Service
public class UserAddressServiceImpl implements UserAddressService {

	@Resource
	CommonDao commonDao;
	
	@Resource
	DistrictService districtService;
	
	@Resource
	UserService userService;
	
	@Resource
	CityService cityService;
	
	@Override
	public void addUserAddress(UserAddress userAddress,HttpServletRequest request) {

		try {
			List addressesList = commonDao.findPagingBySql("select userAddressId from tb_useraddress where userId=? order by userAddressId desc", 0, 1,null,userAddress.getUserId());
			if(addressesList.size()<=0){
				userAddress.setUserAddressId("01");
			}else{
				Integer addrId = Integer.parseInt(addressesList.get(0)+"");
				addrId+=1;
				String endAddrId = "";
				for (int i = (addrId+"").length(); i < 2; i++) {
					endAddrId+="0";
				}
				endAddrId+=addrId;
				userAddress.setUserAddressId(endAddrId);
			}
			userAddress.setDeliveryschedule("01");
			commonDao.insert(userAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<UserAddress> getUserAddressByUserId(String userId) {
		List<UserAddress> addressList =  commonDao.findByHql("from UserAddress where userId=? and validflag=1", userId);
		for (UserAddress userAddress : addressList) {
			List<District> list = districtService.getDistrictByMethod(4, userAddress.getDistrictId());
			if(list!=null && list.size()>0){
				District district = list.get(0);
				userAddress.setDistrict(district);
			}
		}
		return addressList;
	}

	@Override
	public void updateUserAddress(UserAddress userAddress) {
			userAddress.setDeliveryschedule("01");
			commonDao.update(userAddress);
	}

	@Override
	public List<UserAddress> getUserAddressByMethod(int type, String... params) {
		
		List<UserAddress> addressesList = new ArrayList();
		
		switch (type) {
		case 0:
			addressesList = commonDao.findByHql("from UserAddress where validflag=1 order by userId");
			break;
		case 1:
			addressesList = commonDao.findByHql("from UserAddress where userAddressId=? and userId=? and validflag=1",params[1],params[0]);
			break;
		case 2:
			addressesList = commonDao.findByHql("from UserAddress where userAddressId=? and validflag=1",params[0]);
			break;
		case 3:
			addressesList = commonDao.findByHql("from UserAddress where userAddressId=? and userId=?",params[0],params[1]);
			break;
		}
		for (UserAddress userAddress : addressesList) {
			List<District> districtsList = districtService.getDistrictByMethod(4, userAddress.getDistrictId());
			List<Object> usersList = userService.getUserByMethod(3, userAddress.getUserId());
			if(districtsList!=null && districtsList.size()>0){
				userAddress.setDistrict(districtsList.get(0));
			}
			if(usersList!=null && usersList.size()>0){
				userAddress.setUser_Ext_Personal((User_Ext_Personal) usersList.get(0));
			}
		}
		return addressesList;
	}

	@Override
	public boolean deleteUserAddress(String userId, String userAddressId,HttpServletRequest request) {
		return commonDao.executeBySql("update tb_useraddress set validflag=0 where userId=? and userAddressId=?"
				,userId,userAddressId);
	}

	@Override
	public Integer getUserAddressCountByDistrictId(String districtId) {
		List districtCount = commonDao.findBySql("select count(*) from tb_useraddress where districtId in ("+districtId+") and validflag=1", null);
		return Integer.valueOf(districtCount.get(0)+"");
	}

	@Override
	public boolean deleteUserAddressByUserIds(String userIds) {
		return commonDao.executeBySql("update tb_useraddress set validflag=0 where userId in ("+userIds+")");
	}

	@Override
	public UserAddress getDefaultUserAddress(String userId) {

		List<UserAddress> addressesList = commonDao.findByHql("from UserAddress where userId=? and validflag=1 and defaultflag=1",userId);
		for (UserAddress userAddress : addressesList) {
			List<District> districtsList = districtService.getDistrictByMethod(4, userAddress.getDistrictId());
			List<Object> usersList = userService.getUserByMethod(3, userAddress.getUserId());
			if(districtsList!=null && districtsList.size()>0){
				userAddress.setDistrict(districtsList.get(0));
			}
			if(usersList!=null && usersList.size()>0){
				userAddress.setUser_Ext_Personal((User_Ext_Personal) usersList.get(0));
			}
		}
		return addressesList.get(0);
	}

}
