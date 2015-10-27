package com.qushop.dict.service;

import java.util.List;

import com.qushop.dict.entity.City;

public interface CityService {
	
	/**
	 * 
	 * @param type 1 查询所有  2根据省份查询信息 3查询市级代码详细 4查询单个包含validflag=0
	 * @param params
	 * @return
	 */
	public List<City> getCityByMethod(int type,String...params);
	
	public boolean addCity(City city,Double longitude,Double latitude);
	
	public boolean deleteCity(String cityId);
	
	public boolean updateCity(City city,Double longitude,Double latitude);
	
}
