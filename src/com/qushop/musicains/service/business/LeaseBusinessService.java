package com.qushop.musicains.service.business;

public interface LeaseBusinessService {
	
	
	public double calculateTotalRentPrice(String productId,int count,int period,double yajin);
	
	public String assembleAddressFromUserAddress(String userId,String addressId);
	
	public void startCountingRentDates(String leaseId);
}
