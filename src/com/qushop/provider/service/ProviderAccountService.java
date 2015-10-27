package com.qushop.provider.service;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.qushop.provider.entity.Provider;
import com.qushop.provider.entity.Provider_Account;
import com.qushop.user.entity.Oper;

public interface ProviderAccountService {

	/**
	 * 
	 * @param type 0，查询所有 1查询详细
	 * @param params
	 * @return
	 */
	public List<Provider_Account> getProviderAccountByMethod(int type,Oper oper,
			String... params);

	public boolean updateProviderAccount(Provider_Account account,String providerId,String accountNumber,HttpServletRequest request);
	
	public boolean deleteProviderAccount(String providerId,String accountNumber,HttpServletRequest request);
	
	public boolean addProviderAccount(Provider_Account account);
	
}
