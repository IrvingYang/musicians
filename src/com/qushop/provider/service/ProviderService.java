package com.qushop.provider.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qushop.prod.entity.Product;
import com.qushop.provider.entity.Provider;
import com.qushop.user.entity.Oper;

public interface ProviderService {
	
	/**
	 * 
	 * @param type 0表示查询所有信息  1表示查询详情   2查询详细，包含商城  3查询详情,不包含子对象  4查询全部,不包含子对象  5查询单个合作社包含validflag=0
	 * @param params
	 * @return
	 */
	public List<Provider> getProviderByMethod(int type,Oper oper,String...params);
	
	public boolean addProvider(Provider provider,HttpServletRequest request);
	
	public boolean deleteProvider(String providerId,HttpServletRequest request);
	
	public boolean update(Provider provider,HttpServletRequest request);
	
	public List<Product> getProviderProductByMethod(int type,String...params);
	
	public Integer getProviderByCityId(String cityId);
	
}
