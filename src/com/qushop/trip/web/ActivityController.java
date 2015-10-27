package com.qushop.trip.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.trip.entity.Activity;
import com.qushop.trip.service.ActivityService;


@Controller
@RequestMapping("/trip/activity")
public class ActivityController  {

	
	@Resource
	ActivityService activityService;
	
	
	@RequestMapping("getActivityList.action")
	@ResponseBody
	public Object getActivityList(HttpServletRequest request)
	{
		List<Activity> activitiesList = activityService.getActivityByMethod(0, 0, null);
		return activitiesList;
	}
	
	
	@RequestMapping("getActvityDetail.action")
	@ResponseBody
	public Object getActvityDetail(String activityid,HttpServletRequest request)
	{
		List<Activity> activitiesList = activityService.getActivityByMethod(1, 0, null, activityid);
		return activitiesList.get(0);
	}
	
	@RequestMapping("activityList.shtml")
	public String activityList(HttpServletRequest request,String pageNostr,
			String pageSizestr){
		
		return "web/activity";
	}
	
}
