package com.qushop.dict.service;

import java.util.List;

import com.qushop.dict.entity.Express_vendor;

public interface ExpressService {

	public boolean addExpress(Express_vendor express);
	
	public boolean deleteExpress(String vendorid);
	
	public boolean updateExpress(Express_vendor express);
	
	/**
	 * 
	 * @param type 0查询所有快递公司  1查询快递公司详情 2查询单个包含validflag=0
	 * @param params
	 * @return
	 */
	public List<Express_vendor> getExpressByMethod(int type,String ...params);
	
}
