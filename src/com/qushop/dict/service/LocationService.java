package com.qushop.dict.service;

import java.util.List;

import com.qushop.dict.entity.Location;

public interface LocationService {

	public boolean addLocation(Location location);
	
	public boolean updateLocation(Location location);

	public boolean updateLocation(String entityId,String entityType,Double longitude,Double latitude);
	
	/**
	 * 
	 * @param type 1.根据entitytype 和entityid，查询
	 * @param params
	 * @return
	 */
	public List<Location> getLocationByMethod(int type,String...params);
	
	public boolean deleteLocationPhysically(String entityids,String entityType);
	
}
