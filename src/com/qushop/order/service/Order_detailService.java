package com.qushop.order.service;

import java.util.List;

import com.qushop.order.entity.Order_detail;
import com.qushop.user.entity.Oper;

public interface Order_detailService {

	/**
	 * 
	 * @param type 0通过订单类别和productid查询订单详细信息	1通过orderId查询详细包含validflag=0   2通过orderId查询详细只包含validflag=1
	 * @param oper
	 * @param params
	 * @return
	 */
	public List<Order_detail> getOrderdetailByMethod(int type,Oper oper,String...params);
	
	public Order_detail getOrderdetail(String productId, String orderId,int orderType) ;
	
	public boolean deleteOrderDetail(String orderIds);
	
}
