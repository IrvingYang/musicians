package com.qushop.dict.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.dict.entity.Country;
import com.qushop.dict.service.CountryService;


@Service
public class CountryServiceImpl implements CountryService {
	
	@Resource
	CommonDao baseDao;
	
	
	@Override
	public Country getCountriesById(Integer countryId) {
		 List<Country> findByHql = baseDao.findByHql("from Country where validflag = 1 and countryId = ?",countryId);
		return findByHql!=null?findByHql.get(0):null;
	}

	@Override
	public boolean add(Country country) {	
		baseDao.insert(country);
		return true;
	}

	@Override
	public boolean delete(String countryId) {
		boolean bool = false;
		String sql = "update tb_country set validflag=1 where countryId="+countryId;
		bool = baseDao.executeBySql(sql);
		return bool;
	}

	@Override
	public boolean update(Country city) {
		baseDao.update(city);
		return true;
	}

	@Override
	public List<Country> getAllCountries() {
		 List<Country> findByHql = baseDao.findByHql("from Country where validflag=1 ");
		return findByHql;
	}

}
