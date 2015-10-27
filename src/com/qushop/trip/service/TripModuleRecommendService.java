package com.qushop.trip.service;

import java.util.List;

import com.qushop.trip.entity.TripModuleRecommend;
import com.qushop.user.entity.Oper;


public interface TripModuleRecommendService {
	
	/**
	 * 
	 * @param type 0列表查询	1通过entitytype和entityid查询详细
	 * @param maxcount
	 * @param oper
	 * @param params
	 * @return
	 */
	public List<TripModuleRecommend> getTripModuleRecommendByMethod(int type,int maxcount,Oper oper,String ...params);
	
	public boolean addTripModuleRecommend(TripModuleRecommend moduleRecommend,Oper oper,String recommendImage);

	public boolean updateTripModuleRecommend(TripModuleRecommend moduleRecommend,String recommendImage, Oper oper);
	
	public boolean deleteTripModuleRecommend(String[] entitytypeAndentityid,Oper oper);
	
}
