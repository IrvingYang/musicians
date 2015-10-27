package com.qushop.dict.service;

import java.util.List;

import com.qushop.dict.entity.District;

public interface DistrictService {
	
	/**
	 * 
	 * @param type 1 查询所有  2根据城市查询信息 3查询区县代码详细 4查询单个包含validflag=0
	 * @param params
	 * @return
	 */
	public List<District> getDistrictByMethod(int type,String...params);
	
	public boolean addDistrict(District district);
	
	public boolean deleteDistrict(String cityId);
	
	public boolean updateDistrict(District district);
	
}
