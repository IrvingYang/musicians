package com.qushop.trip.service;

import java.util.List;

import com.qushop.trip.entity.Activity;
import com.qushop.user.entity.Oper;

public interface ActivityService {

	/**
	 * 
	 * @param type 0查询活动列表		1查询活动详细		2查询详细包含删除
	 * @param maxCount  最大返回条数
	 * @param oper 操作员
	 * @param params 可变参数
	 * @return
	 */
	public List<Activity> getActivityByMethod(int type,int maxCount,Oper oper,String...params);

	/**
	 * 修改活动信息
	 * @param activity
	 * @param oper
	 * @return
	 */
	public boolean updateActivity(Activity activity,Oper oper,String imagepath);
	
	/**
	 * 添加活动信息
	 * @param activity
	 * @param oper
	 * @return
	 */
	public boolean addActivity(Activity activity,Oper oper,String imagepath);
	
	/**
	 * “删除”活动信息
	 * @param activityid
	 * @param oper
	 * @return 成功 success；错误信息
	 */
	public boolean deleteActivity(String[] activityid,Oper oper);
	
}
