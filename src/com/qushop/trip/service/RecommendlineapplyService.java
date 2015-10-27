package com.qushop.trip.service;

import java.util.List;

import com.qushop.trip.entity.Recommendlineapply;
import com.qushop.user.entity.Oper;

public interface RecommendlineapplyService {

	
	/**
	 * @param type 0通过线路id查询报名信息；1通过userid和recommendlineapply查询报名详细；2查询指定线路是否报名；3查询报名人数
	 * @param maxCount
	 * @param oper
	 * @param params
	 * @return
	 */
	public List getRecommendlineApplyByMethod(int type,int maxCount,Oper oper,String ...params);
	
	/**
	 * 新增线路报名信息
	 * @param recommendlineapply
	 * @param oper
	 * @return
	 */
	public boolean addRecommendlineApply(Recommendlineapply recommendlineapply,Oper oper);
	
	/**
	 * 删除线路报名
	 * @param userid
	 * @param recommendlineid
	 * @param oper
	 * @return
	 */
	public boolean deleteRecommendlineapply(String userid,String recommendlineid,Oper oper);
	
	/**
	 * 修改线路报名状态
	 * @param useridAndRecommendlineid
	 * @param status
	 * @param oper
	 * @return
	 */
	public boolean updateStatus(String[] useridAndRecommendlineid,Integer status,Oper oper);
	
}
