package com.qushop.trip.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qushop.trip.entity.TripModuleRecommend;
import com.qushop.trip.service.TripModuleRecommendService;


@Controller
@RequestMapping("/trip/moduleRecommend")
public class TripModuleRecommendController {

	@Resource
	TripModuleRecommendService moduleRecommendService;
	
	
	@RequestMapping("getTripModuleRecommendWithEntityType.action")
	@ResponseBody
	public Object getTripModuleRecommendWithEntityType(Integer maxCount,HttpServletResponse response,HttpServletRequest request){
		
		List<TripModuleRecommend> moduleRecommendsList = moduleRecommendService.getTripModuleRecommendByMethod(0, maxCount, null);
		return moduleRecommendsList;
	}
}
