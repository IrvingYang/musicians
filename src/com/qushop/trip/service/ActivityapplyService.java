package com.qushop.trip.service;

import java.util.List;

import com.qushop.trip.entity.Activityapply;
import com.qushop.user.entity.Oper;


public interface ActivityapplyService {

	/**
	 * 
	 * @param type 0通过活动id查询报名信息；1通过userid和activityid查询报名详细；2查询指定活动是否报名；3查询报名人数
	 * @param maxCount
	 * @param oper
	 * @param params
	 * @return
	 */
	public List getActivityApplyByMethod(int type,int maxCount,Oper oper,String ...params);
	
	/**
	 * 新增活动报名
	 * @param activityapply
	 * @param oper
	 * @return
	 */
	public String addActivityApply(Activityapply activityapply,Oper oper);
	
	/**
	 * 修改活动报名
	 * @param activityapply
	 * @param oper
	 * @return
	 */
	public String updateActivityApply(Activityapply activityapply,Oper oper);
	
	/**
	 * “删除”活动报名
	 * @param userid
	 * @param activityId
	 * @param oper
	 * @return
	 */
	public String deleteActivityApply(String userid,String activityId,Oper oper);
	
	
	/**
	 * 
	 * @param userid
	 * @param activityId
	 * @param status
	 * @param oper
	 * @return
	 */
	public String updateStatus(String[] useridAndActivityid,Integer status,Oper oper);
	
	
}
