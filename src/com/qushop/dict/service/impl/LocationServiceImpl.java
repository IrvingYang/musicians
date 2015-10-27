package com.qushop.dict.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.dict.entity.Location;
import com.qushop.dict.service.LocationService;


@Service
public class LocationServiceImpl implements LocationService {
	
	@Resource
	CommonDao commonDao;

	@Override
	public boolean addLocation(Location location) {
		
		try {
			commonDao.insert(location);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateLocation(Location location) {
		
		try {
			commonDao.update(location);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Location> getLocationByMethod(int type, String... params) {

		List<Location> locations = new ArrayList<Location>();
		switch (type) {
		case 1:
			locations = commonDao.findByHql("from Location where entitytype=? and entityid =?", params[0],params[1]);
			break;
		}
		return locations;
	}

	@Override
	public boolean deleteLocationPhysically(String entityids, String entityType) {
		return commonDao.executeBySql("delete from tb_location where entitytype=? and entityid in ("+entityids+")",entityType);
	}

	@Override
	public boolean updateLocation(String entityId, String entityType,
			Double longitude, Double latitude) {
		if(commonDao.findByHql("from Location where entityId=? and entityType=?", entityId,entityType).size()>0){
			return commonDao.executeBySql("update tb_location set longitude=?,latitude=? where entityid=? and entitytype=?",longitude,latitude,entityId,entityType);
		}
		else{
			Location location = new Location();
			location.setEntityid(entityId);
			location.setEntitytype("02");
			location.setLatitude(latitude);
			location.setLongitude(longitude);
			location.setLocationName("");
			commonDao.insert(location);
			return true;
		}
	}
	
}
