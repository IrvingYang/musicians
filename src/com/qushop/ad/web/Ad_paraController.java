package com.qushop.ad.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.ad.service.Ad_paraService;


@Controller
@RequestMapping("/ad")
public class Ad_paraController {

	@Resource
	Ad_paraService paraService;
	
	@RequestMapping("getAdByOp_area.action")
	@ResponseBody
	public Object getAdByOp_area(Integer type,String typeId,Integer maxCount,HttpServletRequest request,HttpServletResponse response){
		List ad_paraList = paraService.getAd_ParaByArea(type, typeId, maxCount);
		return ad_paraList;
	}
}
