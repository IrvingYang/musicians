package com.qushop.musicains.service;

import java.util.List;

import com.qushop.musicains.entity.Repo;

public interface RepoDaoService {


	public void saveRepo(Repo repo);
	
	public void approveRepo(Repo repo,String operId);
	
	public void declineRepo(Repo repo,String operId);
	
	public void shippingRepo(Repo repo);
	
	public void finishRepo(Repo repo,String operId);

	public Repo getRepoByRepoId(String repoId);
	
	public void deleteRepoByRepoIds(String repoIds,String operId);
	
	public List<Repo> getRepoList(String providerId);
	
	public List<Repo> getRepoPagingListByUserId(String userId,int pageNo,int pageSize);
	
}
