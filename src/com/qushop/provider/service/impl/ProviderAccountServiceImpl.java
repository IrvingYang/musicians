package com.qushop.provider.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.PublicUtil;
import com.qushop.provider.entity.Provider;
import com.qushop.provider.entity.Provider_Account;
import com.qushop.provider.service.ProviderAccountService;
import com.qushop.provider.service.ProviderService;
import com.qushop.user.entity.Oper;


@Service
public class ProviderAccountServiceImpl implements ProviderAccountService {

	@Resource
	CommonDao commonDao;
	
	@Resource
	ProviderService providerService;
	
	@Override
	public List<Provider_Account> getProviderAccountByMethod(int type,Oper oper,
			String... params) {
		
		List<Provider_Account> accountsList = new ArrayList();
		switch (type) {
		case 0:

			if(oper.getPartnerflag()==0)
			{
				accountsList = commonDao.findByHql("from Provider_Account where  validflag=1");
			}
			else
			{
				accountsList = commonDao.findByHql("from Provider_Account where providerId=? and validflag=1",oper.getProviderId());
			}
			for (Provider_Account provider_Account : accountsList) {
				
				provider_Account.setProvider(providerService.getProviderByMethod(3,null, provider_Account.getProviderId()).get(0));
			}
			break;
		case 1:
			accountsList = commonDao.findByHql("from Provider_Account where providerId=? and accountNumber=? and validflag=1",params[0],params[1]);
			for (Provider_Account provider_Account : accountsList) {
				
				provider_Account.setProvider(providerService.getProviderByMethod(3,null, provider_Account.getProviderId()).get(0));
			}
			break;
		}
		return accountsList;
	}

	@Override
	public boolean updateProviderAccount(Provider_Account account,String providerId,String accountNumber,HttpServletRequest request) {
		try {
			String sql = "update tb_provider_account set branchName=?,accountName=?,accountNumber=?,"
					+ "lastUpdateTime=?,operid=? where providerId=? and accountNumber =? and validflag=1";
			boolean bool = commonDao.executeBySql(sql, account.getBranchName(),
					account.getAccountName(),account.getAccountNumber(),new Date(),PublicUtil.getOper(request).getOperId(),providerId,accountNumber);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteProviderAccount(String providerId,String accountNumber,HttpServletRequest request){

		String sql = "update tb_provider_account set validflag=0,lastUpdateTime=?,operid=? where providerId=? and accountNumber =? and validflag=1";
		boolean bool = commonDao.executeBySql(sql, new Date(),PublicUtil.getOper(request).getOperId(),providerId,accountNumber);
		return bool;
	}

	@Override
	public boolean addProviderAccount(Provider_Account account) {

		try {
			commonDao.insert(account);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}



