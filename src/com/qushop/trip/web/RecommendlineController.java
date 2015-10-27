package com.qushop.trip.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.trip.entity.Recommendline;
import com.qushop.trip.service.RecommendlineService;



@Controller
@RequestMapping("/trip/recommendline")
public class RecommendlineController {

	@Resource
	RecommendlineService recommendlineService;
	
	

	@RequestMapping("getRecommendlineList.action")
	@ResponseBody
	public Object getActivityList(HttpServletRequest request)
	{
		List<Recommendline> recommendlinesList = recommendlineService.getRecommendlineByMethod(0, 0, null);
		return recommendlinesList;
	}
	
	
	@RequestMapping("getRecommendlineDetail.action")
	@ResponseBody
	public Object getActvityDetail(String recommendlineid,HttpServletRequest request)
	{
		List<Recommendline> recommendlinesList = recommendlineService.getRecommendlineByMethod(1, 0, null, recommendlineid);
		return recommendlinesList;
	}
	
}
