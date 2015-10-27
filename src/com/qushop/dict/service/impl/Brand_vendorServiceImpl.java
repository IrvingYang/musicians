package com.qushop.dict.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.dict.entity.Brand_vendor;
import com.qushop.dict.service.Brand_vendorService;

@Service
public class Brand_vendorServiceImpl implements Brand_vendorService {

	@Resource
	CommonDao<Brand_vendor> Brand_vendorDao;
	

	@Override
	public List<Brand_vendor> getVendorByMethod(int type, String... params) {
		
		List<Brand_vendor> brand_vendorsList = null;
		
		switch (type) {
		case 1:
			brand_vendorsList = Brand_vendorDao.findByHql("from Brand_vendor where validflag=1");
			break;
		case 2:
			brand_vendorsList = Brand_vendorDao.findPagingByHql("from Brand_vendor where validflag=1",0,Integer.valueOf(params[0]).intValue());
			break;
		case 3:
			brand_vendorsList = Brand_vendorDao.findByHql("from Brand_vendor where brandid=? and validflag=1",params[0]);
			break;
		case 4:
			brand_vendorsList = Brand_vendorDao.findByHql("from Brand_vendor where brandid=?",params[0]);
		default:
			break;
		}
		
		return brand_vendorsList;
	}


	@Override
	public boolean addBrand(Brand_vendor brand_vendor) {
		
		
		List<Brand_vendor> vendors = Brand_vendorDao.findPagingByHql("from Brand_vendor order by brandid desc", 0, 1);
		if(vendors.size()<=0){
			brand_vendor.setBrandid("0001");
		}else{
			Brand_vendor c = vendors.get(0);
			String bId = c.getBrandid();
			int id = Integer.parseInt(bId)+1;
			String endId = "";
			for (int i = (id+"").length(); i < 4; i++) {
				endId+="0";
			}
			endId+=id;
			brand_vendor.setBrandid(endId);
		}
		try {
			Brand_vendorDao.insert(brand_vendor);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean deleteBrand(String brandid) {
		
		boolean bool = false;
		String sql = "update tb_brand_vendor set validflag=0 where brandid in ("+brandid+")";
		bool = Brand_vendorDao.executeBySql(sql);
		return bool;
	}


	@Override
	public boolean updateBrand(Brand_vendor brand_vendor) {
		try {
			Brand_vendorDao.update(brand_vendor);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
