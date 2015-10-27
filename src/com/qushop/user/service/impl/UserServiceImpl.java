package com.qushop.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.Constants;
import com.qushop.common.util.PublicUtil;
import com.qushop.user.entity.Enterprise_image;
import com.qushop.user.entity.User;
import com.qushop.user.entity.User_Ext_Enterprise;
import com.qushop.user.entity.User_Ext_Personal;
import com.qushop.user.service.Enterprise_imageService;
import com.qushop.user.service.UserAddressService;
import com.qushop.user.service.UserService;
import com.qushop.user.service.WishlistService;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	CommonDao baseDao;

	@Resource
	Enterprise_imageService enterprise_imageService;
	
	@Resource
	WishlistService wishlistService;
	
	@Resource
	UserAddressService userAddressService;
	
	@Override
	public int exists(String userName) {
		
		int count = Integer.parseInt(baseDao.findByHql("select count(*) from User").get(0)+"");
		return count;
	}

	@Override
	public boolean login(User user,HttpServletRequest request) {

		Map<String,String> params = new HashMap<String,String>();
		List<User> usersList = baseDao.findByHql("from User where userName=? and password=? and status =1 and validflag = 1", user.getUserName(),user.getPassword());
		if(usersList!=null && usersList.size()>0){
			
			User loginUser =  usersList.get(0);
			if(loginUser.getUserType()==(short)1){
				
				User_Ext_Personal user_Ext_Personal = (User_Ext_Personal) baseDao.findByHql("from User_Ext_Personal where userId=?  and validflag = 1", loginUser.getUserId()).get(0);
				user_Ext_Personal.setUser(loginUser);
				request.getSession().setAttribute(Constants.LOGIN_USER, user_Ext_Personal);
				
				return true;
			}else if(loginUser.getUserType()==(short)2){
				
				User_Ext_Enterprise user_Ext_Enterprise = (User_Ext_Enterprise) baseDao.findByHql("from User_Ext_Enterprise where userId=? and validflag = 1", loginUser.getUserId()).get(0);
				user_Ext_Enterprise.setUser(loginUser);
				request.getSession().setAttribute(Constants.LOGIN_ENTER_UER, user_Ext_Enterprise);
				
				return true;
				
			}
		}

		return false;
	}

	@Override
	public void register(User user,HttpServletRequest request) {
		
		String telephone = request.getParameter("telephone");
		if(user.getUserType()==(short)1){
			user.setStatus((short)1);
			baseDao.insert(user);
			User_Ext_Personal user_Ext_Personal = new User_Ext_Personal();
			user_Ext_Personal.setTelephone(telephone);
			user_Ext_Personal.setLastUpdateTime(new Date());
			user_Ext_Personal.setSubscribe_status((short)0);
			user_Ext_Personal.setValidflag((short)1);
			user_Ext_Personal.setUserId(user.getUserId());
			baseDao.insert(user_Ext_Personal);
			
		}else if(user.getUserType()==(short)2){
			user.setStatus((short)0);
			baseDao.insert(user);
			User_Ext_Enterprise user_Ext_Enterprise = new User_Ext_Enterprise();
			String contactman = request.getParameter("contactman");
			String certid1 = request.getParameter("certno1");
			String certid1image = request.getParameter("certid1image");
			String certid2 = request.getParameter("certno2");
			String certid2image = request.getParameter("certid2image");
			String certid3 = request.getParameter("certno3");
			String certid3image = request.getParameter("certid3image");
			String enterprisename = request.getParameter("enterprisename");
			
			Enterprise_image enterprise_image1 = new Enterprise_image();
			Enterprise_image enterprise_image2 = new Enterprise_image();
			Enterprise_image enterprise_image3 = new Enterprise_image();
			enterprise_image1.setImageid(user.getUserId()+"01");
			enterprise_image1.setUserId(user.getUserId());
			enterprise_image1.setImagepath(certid1image);
			enterprise_image1.setUploadtime(new Date());
			baseDao.insert(enterprise_image1);
			enterprise_image2.setImageid(user.getUserId()+"02");
			enterprise_image2.setUserId(user.getUserId());
			enterprise_image2.setImagepath(certid2image);
			enterprise_image2.setUploadtime(new Date());
			enterprise_image3.setUserId(user.getUserId());
			baseDao.insert(enterprise_image2);
			enterprise_image3.setImageid(user.getUserId()+"03");
			enterprise_image3.setImagepath(certid3image);
			enterprise_image3.setUploadtime(new Date());
			baseDao.insert(enterprise_image3);
			user_Ext_Enterprise.setCertid1(certid1);
			user_Ext_Enterprise.setCertid2(certid2);
			user_Ext_Enterprise.setCertid3(certid3);
			user_Ext_Enterprise.setCertid1imageid(enterprise_image1.getImageid());
			user_Ext_Enterprise.setCertid2imageid(enterprise_image2.getImageid());
			user_Ext_Enterprise.setCertid3imageid(enterprise_image3.getImageid());
			user_Ext_Enterprise.setLastUpdateTime(new Date());
			user_Ext_Enterprise.setContactman(contactman);
			user_Ext_Enterprise.setTelephone(telephone);
			user_Ext_Enterprise.setUserId(user.getUserId());
			user_Ext_Enterprise.setEnterprisename(enterprisename);
			user_Ext_Enterprise.setValidflag((short)1);
			baseDao.insert(user_Ext_Enterprise);
			
		}
	}

	@Override
	public List<Object> getUserByMethod(int type, String... params) {

		List<Object> userList = new ArrayList();
		switch (type) {
		case 1:
			userList = baseDao.findByHql("from User_Ext_Personal where validflag = 1 ");
			for (int i = 0; i < userList.size(); i++) {
				User_Ext_Personal user_Ext_Personal = (User_Ext_Personal) userList.get(i);
				List<User> userlist = baseDao.findByHql("from User where userId=? and userType=1 and validflag=1", user_Ext_Personal.getUserId());
				User user = null;
				if(userlist!=null && userlist.size()>0){
					user = userlist.get(0);
				}
				user_Ext_Personal.setUser(user);
			}
			break;
		case 2:
			userList = baseDao.findByHql("from User_Ext_Enterprise where validflag = 1");
			for (int i = 0; i < userList.size(); i++) {
				User_Ext_Enterprise user_Ext_Enterprise = (User_Ext_Enterprise) userList.get(i);
				List<User> userlist = baseDao.findByHql("from User where userId=? and userType=2 and validflag=1 ", user_Ext_Enterprise.getUserId());
				User user = null;
				if(userlist!=null && userlist.size()>0){
					user = userlist.get(0);
				}
				user_Ext_Enterprise.setUser(user);
			}
			break;
		case 3:
			userList = baseDao.findByHql("from User_Ext_Personal where userId=? and validflag = 1 ",params[0]);
			for (int i = 0; i < userList.size(); i++) {
				User_Ext_Personal user_Ext_Personal = (User_Ext_Personal) userList.get(i);
				List<User> userlist = baseDao.findByHql("from User where userId=? and userType=1 and validflag=1", user_Ext_Personal.getUserId());
				User user = null;
				if(userlist!=null && userlist.size()>0){
					user = userlist.get(0);
				}
				user_Ext_Personal.setUser(user);
			}
			break;
		case 4:
			userList = baseDao.findByHql("from User_Ext_Enterprise where userId=? and validflag = 1",params[0]);
			for (int i = 0; i < userList.size(); i++) {
				User_Ext_Enterprise user_Ext_Enterprise = (User_Ext_Enterprise) userList.get(i);
				List<User> userlist = baseDao.findByHql("from User where userId=? and userType=2 and validflag=1", user_Ext_Enterprise.getUserId());
				User user = null;
				if(userlist!=null && userlist.size()>0){
					user = userlist.get(0);
				}
				user_Ext_Enterprise.setUser(user);
			}
			break;
		case 5:
			List<User> usersList = baseDao.findByHql("from User where userType=2 and (status=0 or status=4) and validflag=1");
			for (User user : usersList) {
				List<User_Ext_Enterprise> entList = baseDao.findByHql("from User_Ext_Enterprise where userId=? and validflag=1", user.getUserId());
				if(entList!=null && entList.size()>0){
					User_Ext_Enterprise enterprise = entList.get(0);
					enterprise.setUser(user);
					userList.add(enterprise);
				}
			}
			break;
		case 6:
			return baseDao.findByHql("from User where userId=? and validflag=1",params[0]);
		case 7:
			userList = baseDao.findByHql("from User_Ext_Personal where userId=? and validflag = 1 ",params[0]);
			break;
		case 8:
			userList = baseDao.findByHql("from User_Ext_Enterprise where userId=? and validflag = 1",params[0]);
			break;
		case 9:
			break;
		case 10:
			userList = baseDao.findBySql("select * from tb_user user,tb_user_ext_personal person where person.userId = user.userId and telephone=? and person.validflag = 1", User.class, params[0]);
			if(userList.size()<=0){
				userList = baseDao.findBySql("select * from tb_user user,tb_user_ext_enterprise enterprise where enterprise.userId = user.userId and  telephone=? and enterprise.validflag = 1",User.class, params[0]);
				if(userList.size()<=0){
					return null;
				}
			}
			break;
		case 11:
			return baseDao.findByHql("from User where userId=?",params[0]);
		case 12:
			userList = baseDao.findByHql("from User_Ext_Personal where userId=?",params[0]);
			break;
		case 13:
			userList = baseDao.findByHql("from User_Ext_Enterprise where userId=?",params[0]);
			break;
		default:
			break;
		}
		return userList;
	}

	@Override
	public boolean updateUser(int type, User_Ext_Personal personal,
			User_Ext_Enterprise enterprise,User user) {
		
		try {
			switch (type) {
			case 1:
				baseDao.update(user);
				baseDao.update(personal);
				break;
			case 2:
				baseDao.update(user);
				baseDao.update(enterprise);
				break;
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public boolean deleteUser(int type, String userId,HttpServletRequest request) {

		try {
			switch (type) {
			case 1:
				baseDao.executeBySql("update tb_user_ext_personal set validflag=0,lastUpdateTime=?,operid=? where userId in ("+userId+")",new Date(),PublicUtil.getOper(request).getOperId());
				wishlistService.deleteWishListByUserIds(userId);
				userAddressService.deleteUserAddressByUserIds(userId);
				break;
			case 2:
				baseDao.executeBySql("update tb_user_ext_enterprise set validflag=0,lastUpdateTime=?,operid=? where userId in ("+userId+")",new Date(),PublicUtil.getOper(request).getOperId());
				break;
			default:
				break;
			}
			baseDao.executeBySql("update tb_user set validflag=0,lastUpdateTime=?,operid=? where userId in ("+userId+")",new Date(),PublicUtil.getOper(request).getOperId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public User getBaseUser(String userId) {
		
		List<User> usersList = baseDao.findByHql("from User where userId = ? and validflag = 1", userId);
		if(usersList!=null && usersList.size()>0){
			return usersList.get(0);
		}
		return null;
	}

	@Override
	public boolean updateUser(User user) {
		
		try {
			baseDao.executeBySql("update tb_user set password=? where username = ?",user.getPassword(),user.getUserName());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
