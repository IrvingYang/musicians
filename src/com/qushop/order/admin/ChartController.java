package com.qushop.order.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.PublicUtil;
import com.qushop.order.service.ChartService;


@Controller
@RequestMapping("/chart")
public class ChartController {

	@Resource
	ChartService chartService;
	
	@RequestMapping("getThisMonthFirstTypeChart.action")
	@ResponseBody
	public Object getThisMonthFirstTypeChart(HttpServletRequest request,String beginTime,String endTime,String frequency){
		
		String providerId = PublicUtil.getOper(request).getProviderId();
		List<Object> objects = chartService.getThisMonthFirstTypeChart(providerId,beginTime,endTime,frequency);
		return objects;
	}

	@RequestMapping("getSalesAreaChart.action")
	@ResponseBody
	public Object getSalesAreaChart(HttpServletRequest request,String beginTime,String endTime,String frequency){
		
		String providerId = PublicUtil.getOper(request).getProviderId();
		List<Object> objects = chartService.getSalesAreaChart(providerId,beginTime,endTime,frequency);
		return objects;
	}
	
	@RequestMapping("getProductSalesCountOfLineChart.action")
	@ResponseBody
	public Object getProductSalesCountOfLineChart(HttpServletRequest request,String beginTime,String endTime,String frequency){
		String providerId = PublicUtil.getOper(request).getProviderId();
		List<Object> objects = chartService.getProductSalesCountOfLineChart(providerId,beginTime,endTime,frequency);
		return objects;
	}
	
	@RequestMapping("getProductSalesMoneyOfLineChart.action")
	@ResponseBody
	public Object getProductSalesMoneyOfLineChart(HttpServletRequest request,String beginTime,String endTime,String frequency){
		
		String providerId = PublicUtil.getOper(request).getProviderId();
		List<Object> objects = chartService.getProductSalesMoneyOfLineChart(providerId,beginTime,endTime,frequency);
		return objects;
	}
	
}
