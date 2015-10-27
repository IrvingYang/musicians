package com.qushop.alipay.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.order.entity.Order_list;
import com.qushop.order.service.Order_listService;
@Controller
@RequestMapping("/alipay/dummy")
public class DummyAlipayController {
	
	@Resource
	Order_listService order_listService;
	
	@RequestMapping(value="pay.do",method=RequestMethod.POST)
	@ResponseBody
	public void pay(HttpServletRequest request,String orderId,Integer orderType,String trade_no) {
		List<Order_list> order_listDetail = order_listService. getOrder_listDetail(orderId, orderType==3?true:false);
		Order_list order_list = order_listDetail.get(0);
		order_list.setThirdpartorderno(trade_no);
		order_list.setStatus("06");
		order_list.setLastUpdateTime(new Date());
		order_listService.updateOrderList(order_list);
	}

}
