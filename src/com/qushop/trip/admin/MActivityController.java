package com.qushop.trip.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.common.util.UtilDate;
import com.qushop.trip.entity.Activity;
import com.qushop.trip.service.ActivityService;
import com.qushop.trip.service.ActivityapplyService;
import com.qushop.user.entity.Oper;



@Controller
@RequestMapping("/manage/activity")
public class MActivityController {

	@Resource
	ActivityService activityService;
	
	@Resource
	ActivityapplyService activityapplyService;
	
	/**
	 * 列表
	 * @param action
	 * @param request
	 * @return
	 */
	@RequestMapping("getActivityList.do")
	public String getActivityList(String action,HttpServletRequest request)
	{
		List<Activity> activitiesList = activityService.getActivityByMethod(0, 0, PublicUtil.getOper(request));
		request.setAttribute("activitiesList", activitiesList);
		if("page".equals(action))
		{
			return "admin/activityList";
		}
		else
		{
			return "admin/lookDialog/lookUpActivity";
		}
	}
	
	/**
	 * 编辑（添加修改）页
	 * @param opFlag
	 * @param activityId
	 * @param request
	 * @return
	 */
	@RequestMapping("toEditActivityPage.do")
	public String toEditActivityPage(Integer opFlag,String[] activityid,HttpServletRequest request)
	{
		if(opFlag==1)
		{
			request.setAttribute("action", "add");
		}
		else if(opFlag==2)
		{
			List<Activity> activitiesList = activityService.getActivityByMethod(1, 0, null, activityid[0]);
			request.setAttribute("activity", activitiesList.get(0));
			request.setAttribute("action", "update");
		}
		return "admin/editActivity";
	}
	
	/**
	 * 删除多个
	 * @param activityId
	 * @param request
	 * @return
	 */
	@RequestMapping("deleteActivity.do")
	@ResponseBody
	public Object deleteActivity(String[] activityid,HttpServletRequest request)
	{
		boolean bool = true;
		for (String acid : activityid) {
			
			List<Activity> activitiesList = activityService.getActivityByMethod(1, 0, PublicUtil.getOper(request), acid);
			if(activitiesList.size()>0){
				if(activitiesList.get(0).getStatus()!=0 && activitiesList.get(0).getStatus()!=4)
				{
					List<Long> countList =  activityapplyService.getActivityApplyByMethod(2, 0, PublicUtil.getOper(request), acid);
					
					if(!activitiesList.get(0).getProviderId().equals(PublicUtil.getOper(request).getProviderId()))
					{
						return DwzUtil.opFailed("操作失败:选中活动中包含非自己合作社活动", "");
					}
					
					if(countList.get(0).intValue()>0)
					{
						return DwzUtil.opFailed("操作失败:选中活动中已有用户报名", "");
					}
				}
			}
		}
		bool = activityService.deleteActivity(activityid, PublicUtil.getOper(request));
		
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "activity");
		}
		return DwzUtil.opFailed("操作失败", "");
	}

	/**
	 * 修改活动
	 * @param activity
	 * @param imagepath
	 * @param request
	 * @return
	 */
	@RequestMapping("updateActivity.do")
	@ResponseBody
	public Object updateActivity(Activity activity,String imagepath,HttpServletRequest request)
	{
		String cbeginapplytime = request.getParameter("cbeginapplytime");
		String cendapplytime = request.getParameter("cendapplytime");
		String cbegintime = request.getParameter("cbegintime");
		activity.setBeginapplytime(UtilDate.parseDateForDatepatternString(cbeginapplytime));
		activity.setEndapplytime(UtilDate.parseDateForDatepatternString(cendapplytime));
		activity.setBegintime(UtilDate.parseDateForDatepatternString(cbegintime));
		
		boolean bool = activityService.updateActivity(activity, PublicUtil.getOper(request),imagepath);
		
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "activity");
		}
		return DwzUtil.opFailed("操作失败", "");
	}

	/**
	 * 添加活动
	 * @param activity
	 * @param imagepath
	 * @param request
	 * @return
	 */
	@RequestMapping("addActivity.do")
	@ResponseBody
	public Object addActivity(Activity activity,String imagepath,HttpServletRequest request)
	{
		String cbeginapplytime = request.getParameter("cbeginapplytime");
		String cendapplytime = request.getParameter("cendapplytime");
		String cbegintime = request.getParameter("cbegintime");
		activity.setBeginapplytime(UtilDate.parseDateForDatepatternString(cbeginapplytime));
		activity.setEndapplytime(UtilDate.parseDateForDatepatternString(cendapplytime));
		activity.setBegintime(UtilDate.parseDateForDatepatternString(cbegintime));
		
		boolean bool = activityService.addActivity(activity,  PublicUtil.getOper(request), imagepath);
		
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "activity");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	/**
	 * 活动业务流程
	 * @param action
	 * @param activityid
	 * @param request
	 * @return
	 */
	@RequestMapping("activityBusiness.do")
	@ResponseBody
	public Object activityBusiness(String action,String activityid,HttpServletRequest request)
	{
		
		boolean bool = true;
		Oper oper = PublicUtil.getOper(request);
		List<Activity> activitiesList = activityService.getActivityByMethod(1, 0, oper, activityid);
		
		if(activitiesList.size()>0)
		{
			Activity activity = activitiesList.get(0);
			if(!activity.getProviderId().equals(oper.getProviderId()))
			{
				return DwzUtil.opFailed("操作失败:您不是此合作社管理员", "");
			}
			if("signupStart".equals(action))
			{
				if(activity.getStatus()!=0)
				{
					return DwzUtil.opFailed("操作失败:此活动未在报名时段", "");
				}
				activity.setStatus((short)1);
			}
			else if("signupEnd".equals(action))
			{
				if(activity.getStatus()!=1)
				{
					return DwzUtil.opFailed("操作失败:此活动并未开始报名", "");
				}
				activity.setStatus((short)2);
			}
			else if("begin".equals(action))
			{
				if(activity.getStatus()!=2)
				{
					return DwzUtil.opFailed("操作失败:此活动并未结束报名", "");
				}
				activity.setStatus((short)3);
			}
			else if("end".equals(action))
			{
				if(activity.getStatus()!=3)
				{
					return DwzUtil.opFailed("操作失败:此活动并未开始", "");
				}
				activity.setStatus((short)4);
				//活动报名信息状态线下操作
			}
			else
			{
				return DwzUtil.opFailed("操作失败", "");
			}
			bool = activityService.updateActivity(activity, oper, null);
		}
		else
		{
			bool = true;
		}
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "activity");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
}
