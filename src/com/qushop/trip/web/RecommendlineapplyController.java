package com.qushop.trip.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.PublicUtil;
import com.qushop.trip.entity.Recommendlineapply;
import com.qushop.trip.service.RecommendlineapplyService;
import com.qushop.user.entity.User_Ext_Personal;

@Controller
@RequestMapping("/trip/recommendlineapply")
public class RecommendlineapplyController {

	@Resource
	RecommendlineapplyService recommendlineapplyService;
	

	@RequestMapping("applyActivity.action")
	@ResponseBody
	public Object applyRecommendlineapply(String recommendlineid,Integer adultcount,Integer childCount,HttpServletRequest request)
	{
		User_Ext_Personal ext_Personal = PublicUtil.getUserOfSession(request);
		
		if(ext_Personal==null){
			return "needLoginPerson";
		}
		String userid = ext_Personal.getUserId();
		
		if(recommendlineapplyService.getRecommendlineApplyByMethod(1, 0, null, userid,recommendlineid).size()>0)
		{
			return "alreadyapply";
		}
		
		String name = request.getParameter("contactname");
		String telephone = request.getParameter("telephone");
		String remark = request.getParameter("remark");
		Recommendlineapply recommendlineapply = new Recommendlineapply();
		recommendlineapply.setRecommendlineid(recommendlineid);
		recommendlineapply.setAdultcount(adultcount);
		recommendlineapply.setChildcount(childCount);
		recommendlineapply.setName(name);
		recommendlineapply.setPhone(telephone);
		recommendlineapply.setRemark(remark);
		recommendlineapply.setUserid(ext_Personal.getUserId());
		
		boolean bool = recommendlineapplyService.addRecommendlineApply(recommendlineapply, null);
		
		return bool;
	}
	
	@RequestMapping("getApplyPeopleNum.action")
	@ResponseBody
	public Object getApplyPeopleNum(String recommendlineid,HttpServletRequest request)
	{
		return recommendlineapplyService.getRecommendlineApplyByMethod(2, 0, null, recommendlineid);
	}

	@RequestMapping("applyCount.action")
	@ResponseBody
	public Object applyCount(String recommendlineid){
		
		List<Object[]> list = recommendlineapplyService.getRecommendlineApplyByMethod(3, 0, null, recommendlineid);
		
		return list;
	}
	
	
}
