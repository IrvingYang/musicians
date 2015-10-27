package com.qushop.dict.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.dict.entity.City;
import com.qushop.dict.entity.District;
import com.qushop.dict.service.CityService;
import com.qushop.dict.service.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService {
	
	@Resource
	CommonDao baseDao;
	
	@Resource
	CityService cityService;
	
	public List<District> getDistrictByMethod(int type,String...params){
		
		List<District> districtsList = null;
		switch (type) {
		case 1:
			districtsList = baseDao.findByHql("from District where validflag=1 ");
			break;
		case 2:
			districtsList = baseDao.findByHql("from District where cityId=? and validflag=1 ",params);
			break;
		case 3:
			districtsList = baseDao.findByHql("from District where districtId=? and validflag = 1", params[0]);
			break;
		case 4:
			districtsList = baseDao.findByHql("from District where districtId=?", params[0]);
			break;

		default:
			break;
		}
		for (District district : districtsList) {
			
			List<City> citiesList = cityService.getCityByMethod(4, district.getCityId());
			if(citiesList!=null && citiesList.size()>0){
				district.setCity(citiesList.get(0));
			}
		}
		return districtsList;
	}


	@Override
	public boolean deleteDistrict(String districtId) {

		boolean bool = false;
		String sql = "update tb_district set validflag=0 where districtId in ("+districtId+")";
		bool = baseDao.executeBySql(sql);
		return bool;
	}


	@Override
	public boolean updateDistrict(District district) {
		
		try {
			baseDao.update(district);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean addDistrict(District district) {
		
		List<District> districtsList = baseDao.findByHql("from District where cityId = ? and validflag = 1 order by districtId desc", district.getCityId());
		if(districtsList.size()<=0){	
			district.setCityId(district.getCityId()+"01");
		}else{
			District d = districtsList.get(0);
			String bId = d.getDistrictId();
			bId = bId.substring(4);
			int id = Integer.parseInt(bId)+1;
			String endId = "";
			for (int i = (id+"").length(); i < 2; i++) {
				endId+="0";
			}
			endId+=id;
			district.setDistrictId(district.getCityId()+endId);
		}
		try {
			baseDao.insert(district);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
