package com.qushop.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.Constants;
import com.qushop.provider.service.ProviderService;
import com.qushop.user.entity.Oper;
import com.qushop.user.entity.Role;
import com.qushop.user.service.OperService;
import com.qushop.user.service.RoleService;


@Service
public class OperServiceImpl implements OperService {

	@Resource
	CommonDao commonDao;
	
	@Resource
	RoleService roleService;
	
	@Resource
	ProviderService providerService;
	
	@Override
	public Oper login(Oper oper, HttpServletRequest request) {
		
		List<Oper> opersList = commonDao.findByHql("from Oper where operName=? and password=? and validflag = 1", oper.getOperName(),oper.getPassword());
		Oper op = null;
		if(opersList!=null && opersList.size()>0){
			op = opersList.get(0);
			op.setLoginStatus((short)1);
			op.setLastLoginTime(new Date());
			commonDao.update(op);
			request.getSession().setAttribute(Constants.OPER_USER, op);
			Role role = roleService.getRoleList("2", request,op.getRoleId()+"and"+op.getProviderId()).get(0);
			op.setRole(role);
			request.getSession().setAttribute(Constants.OPER_USER, op);
		}
		return op;
	}

	@Override
	public void logout(Oper oper) {
		List<Oper> opersList = commonDao.findByHql("from Oper where operName=? and password=? and validflag = 1", oper.getOperName(),oper.getPassword());
		if(opersList!=null && opersList.size()>0){
			Oper op = opersList.get(0);
			op = opersList.get(0);
			op.setLoginStatus((short)0);
			commonDao.update(op);
		}
	}


	@Override
	public List getOperByMethod(int type,Oper oper, String... params) {

		List<Oper> opersList = new ArrayList<Oper>();
		switch (type) {
		case 0:
			if(oper.getPartnerflag()==0){
				opersList = commonDao.findByHql("from Oper where validflag=1");
			}
			else {
				opersList = commonDao.findByHql("from Oper where providerId=? and validflag=1",params[0]);
			}
			break;
		case 1:
			opersList = commonDao.findByHql("from Oper where operId=? and validflag=1",oper.getOperId());
			break;

		default:
			break;
		}
		
		for (Oper op : opersList) {
			op.setProvider(providerService.getProviderByMethod(3, oper, op.getProviderId()).get(0));
		}
		
		return opersList;
	}

	@Override
	public String addOper(Oper oper) {
		try {
			oper.setOperId(oper.getProviderId()+oper.getRoleId()+(new Random().nextInt(8999)+1000));
			oper.setPassword(DigestUtils.md5Hex(oper.getPassword()+Constants.MAIN_KEY));
			oper.setPartnerflag(oper.getProviderId().equals("0001")?(short)0:(short)1);
			commonDao.insert(oper);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public String deleteOper(String operId) {
		
		try {
			commonDao.executeBySql("update tb_oper set validflag=0 where operId in ("+operId+")");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public boolean updateLoginPassword(Oper oper) {
		return commonDao.executeBySql("update tb_oper set password=? where operId =?",oper.getPassword(),oper.getOperId());
	}

	@Override
	public boolean updateDetailInfo(String email, String sex, String telephone,
			String operId) {
		return commonDao.executeBySql("update tb_oper set email=?,sex=?,telephone=? where operId =?",email,sex,telephone,operId);
	}

	@Override
	public List getOperByRoleId(String roleId) {
		List list = commonDao.findByHql("from Oper where roleId=?",roleId);
		return list;
	}

	@Override
	public String deleteOperByProviderId(String providerIds) {
		try {
			commonDao.executeBySql("update tb_oper set validflag=0 where providerId in ("+providerIds+")");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public boolean operExists(String operName) {
		List<Oper> list = commonDao.findByHql("from Oper where operName=?", operName);
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}
}
