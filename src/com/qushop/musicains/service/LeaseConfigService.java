package com.qushop.musicains.service;

import java.util.List;

import com.qushop.musicains.entity.LeaseConfig;

public interface LeaseConfigService {

	public String saveLeaseConfig(LeaseConfig leaseConfig);
	public String updateLeaseConfig(LeaseConfig leaseConfig);
	public List<LeaseConfig> getLeaseConfigList(String productTypeId);
	
	public List<LeaseConfig> getAllLeaseConfigList();
	
	public LeaseConfig getLeaseConfig(String productTypeId, int count,int period);
	
	public LeaseConfig getLeaseConfig(String lcId);

}
