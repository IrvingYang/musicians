package com.qushop.trip.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.PublicUtil;
import com.qushop.trip.entity.Activityapply;
import com.qushop.trip.service.ActivityapplyService;
import com.qushop.user.entity.User_Ext_Personal;


@Controller
@RequestMapping("/trip/activityapply")
public class ActivityApplyController {

	@Resource
	ActivityapplyService activityapplyService;
	

	@RequestMapping("applyActivity.action")
	@ResponseBody
	public Object applyActivity(String activityid,Integer adultcount,Integer childCount,HttpServletRequest request)
	{
		User_Ext_Personal ext_Personal = PublicUtil.getUserOfSession(request);
		
		if(ext_Personal==null){
			return "needLoginPerson";
		}
		String userid = ext_Personal.getUserId();
		
		if(activityapplyService.getActivityApplyByMethod(1, 0, null, userid,activityid).size()>0)
		{
			return "alreadyapply";
		}
		
		String name = request.getParameter("contactname");
		String telephone = request.getParameter("telephone");
		String remark = request.getParameter("remark");
		Activityapply activityapply = new Activityapply();
		activityapply.setActivityid(activityid);
		activityapply.setAdultcount(adultcount);
		activityapply.setChildcount(childCount);
		activityapply.setName(name);
		activityapply.setPhone(telephone);
		activityapply.setRemark(remark);
		activityapply.setUserid(ext_Personal.getUserId());
		
		String message = activityapplyService.addActivityApply(activityapply, null);
		
		return message;
	}
	
	@RequestMapping("getApplyPeopleNum.action")
	@ResponseBody
	public Object getApplyPeopleNum(String activityid,HttpServletRequest request)
	{
		return activityapplyService.getActivityApplyByMethod(2, 0, null, activityid);
	}
}
