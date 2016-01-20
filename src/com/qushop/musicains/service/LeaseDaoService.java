package com.qushop.musicains.service;

import java.util.List;

import com.qushop.musicains.entity.Lease;

public interface LeaseDaoService {

	public void saveLease(Lease lease);

	public String updateLease(Lease lease);
	
	public List<Lease> getLeaseList();
	
	public void deleteLeaseByLeaseId(String leaseId);
	
	public void deleteLeaseByOrderId(String leaseId);
	
	public List<Lease> getLeaseListByUserId(String userId);
	
	public List<Lease> getLeasePagingListByUserId(String userId,Integer pageNo,Integer pageSize);
	
	public Lease getLeaseByLeaseId(String leaseId);
	
}
