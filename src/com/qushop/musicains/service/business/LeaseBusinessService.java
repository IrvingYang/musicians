package com.qushop.musicains.service.business;

import com.qushop.musicains.entity.LeaseConfig;
import com.qushop.prod.entity.Product;

public interface LeaseBusinessService {
	
	
	public double calculateTotalRentPrice(Product product,int count, LeaseConfig leaseconfig);
	
	public String assembleAddressFromUserAddress(String userId,String addressId);
	
	public void startCountingRentDates(String leaseId);
}
