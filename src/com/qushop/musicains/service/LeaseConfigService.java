package com.qushop.musicains.service;

import java.util.List;

import com.qushop.musicains.entity.LeaseConfig;
import com.qushop.prod.entity.Product_ext_shop;

public interface LeaseConfigService {

	public String saveLeaseConfig(LeaseConfig leaseConfig);
	
	public String updateLeaseConfig(LeaseConfig leaseConfig);
	
	public List<LeaseConfig> getLeaseConfigList(String productTypeId,String productId,short promoteFlag);
	
	public List<LeaseConfig> getAllLeaseConfigList();
	
	public List<LeaseConfig> getAllPromoteLeaseConfigList();
	
	public LeaseConfig getLeaseConfig(Product_ext_shop ext_shop, int count, int period);
	
	public LeaseConfig getLeaseConfig(String lcId);
	
	public void deleteLeaseConfig(String lcId);
	
	

}
