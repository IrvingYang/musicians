package com.qushop.dict.service;

import java.util.List;

import com.qushop.dict.entity.Country;

public interface CountryService {
	
	public Country getCountriesById(Integer countryId);
	
	public List<Country> getAllCountries();
	
	public boolean add(Country country);
	
	public boolean delete(String cityId);
	
	public boolean update(Country city);

}
