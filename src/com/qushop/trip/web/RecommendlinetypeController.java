package com.qushop.trip.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.trip.entity.Recommendlinetype;
import com.qushop.trip.service.RecommendlinetypeService;


@Controller
@RequestMapping("/trip/recommendlinetype")
public class RecommendlinetypeController {

	
	@Resource
	RecommendlinetypeService recommendlinetypeService;
	
	
	@RequestMapping("getRecommendlineType.action")
	@ResponseBody
	public Object getRecommendlineType(HttpServletRequest request)
	{
		List<Recommendlinetype> recommendlinetypesList = recommendlinetypeService.getRecommendlineTypeByMethod(0, null);
		return recommendlinetypesList;
	}
	
}
