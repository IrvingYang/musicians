package com.qushop.dict.service;

import java.util.List;

import com.qushop.dict.entity.Brand_vendor;

public interface Brand_vendorService {
	
	/**
	 * 
	 * 
	 * @param type 0通过brandid查询    1查询所有   2查询指定个数品牌   3查询品牌详细 4查询单个包含validflag=0
	 * @param params
	 * @return
	 */
	public List<Brand_vendor> getVendorByMethod(int type,String...params);
	

	public boolean addBrand(Brand_vendor brand_vendor);
	
	public boolean deleteBrand(String brandid);
	
	public boolean updateBrand(Brand_vendor brand_vendor);
	
}
