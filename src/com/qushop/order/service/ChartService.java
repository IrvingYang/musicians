package com.qushop.order.service;

import java.util.List;

public interface ChartService {

	public List<Object> getThisMonthFirstTypeChart(String providerId,String beginTime,String endTime,String frequency);
	
	public List<Object> getSalesAreaChart(String providerId,String beginTime,String endTime,String frequency);
	
	public List<Object> getProductSalesCountOfLineChart(String providerId,String beginTime,String endTime,String frequency);

	public List<Object> getProductSalesMoneyOfLineChart(String providerId,String beginTime,String endTime,String frequency);
	
	
}
