package com.qushop.dict.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.dict.entity.City;
import com.qushop.dict.entity.Location;
import com.qushop.dict.entity.State;
import com.qushop.dict.service.CityService;
import com.qushop.dict.service.LocationService;
import com.qushop.dict.service.StateService;

@Service
public class CityServiceImpl implements CityService {
	
	@Resource
	CommonDao baseDao;
	
	@Resource
	StateService stateService;
	
	@Resource
	LocationService locationService;
	
	public List<City> getCityByMethod(int type,String...params){
		
		List<City> citiesList = null;
		switch (type) {
		case 1:
			citiesList = baseDao.findByHql("from City where validflag=1 ");
			break;
		case 2:
			citiesList = baseDao.findByHql("from City where stateId=? and validflag=1 ",params);
			break;
		case 3:
			citiesList = baseDao.findByHql("from City where cityId=? and validflag = 1", params[0]);
			break;
		case 4:
			citiesList = baseDao.findByHql("from City where cityId=?", params[0]);
			break;

		default:
			break;
		}
		for (City city : citiesList) {
			List<State> statesList = stateService.getStateByMethod(4, city.getStateId());
			List<Location> locationList = locationService.getLocationByMethod(1, "02", city.getCityId());
			if(statesList!=null && statesList.size()>0){
				city.setState(statesList.get(0));
			}
			if(locationList!=null && locationList.size()>0){
				city.setLocation(locationList.get(0));
			}
		}
		return citiesList;
	}


	@Override
	public boolean deleteCity(String cityId) {
		
		boolean bool = false;
		String sql = "delete from tb_city where cityId in ("+cityId+")";
		bool = baseDao.executeBySql(sql);
		return bool;
	}


	@Override
	public boolean updateCity(City city,Double longitude,Double latitude) {
		
		try {
			baseDao.update(city);
			locationService.updateLocation(city.getCityId(),"02",longitude,latitude);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean addCity(City city,Double longitude,Double latitude) {
		
		List<City> cityList = baseDao.findByHql("from City where stateId = ? and validflag = 1 order by cityId desc", city.getStateId());
		if(cityList.size()<=0){	
			city.setCityId(city.getStateId()+"01");
		}else{
			City c = cityList.get(0);
			String bId = c.getCityId();
			bId = bId.substring(2);
			int id = Integer.parseInt(bId)+1;
			String endId = "";
			for (int i = (id+"").length(); i < 2; i++) {
				endId+="0";
			}
			endId+=id;
			city.setCityId(city.getStateId()+endId);
		}
		try {
			baseDao.insert(city);
			Location location = new Location();
			location.setEntityid(city.getCityId());
			location.setEntitytype("02");
			location.setLatitude(latitude);
			location.setLongitude(longitude);
			location.setLocationName("");
			locationService.addLocation(location);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
