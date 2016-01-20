package com.qushop.order.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qushop.common.entity.CartUtil;
import com.qushop.common.util.PagePojo;
import com.qushop.common.util.ShopTemp;
import com.qushop.order.entity.Order_detail;
import com.qushop.order.entity.Order_list;
import com.qushop.order.pojo.OrderListResult;
import com.qushop.user.entity.Oper;

public interface Order_listService {
	public Order_list addOrder(List<ShopTemp> shopTemp,short orderType,String userId,String userAddressId,Integer payofflineflag,HttpServletRequest request);

	public void addShopOrder(CartUtil cartUtil,String userId,String userAddressId,Integer payofflineflag,HttpServletRequest request);

	public Order_list addLeaseOrder(List<ShopTemp> shopTemp, short orderType, String userId, String userAddressId,
			Integer payofflineflag,int invoicetype,String remark,String paymentway) ;

	public List<Order_list> getOrder_listByUserIdAndMethod(int type,String userId,Integer orderType,String...params);

	/**
	 * 
	 * @param type 1(查看所有商城订单 )2(查看所有团购订单) 3(查看大宗交易订单  )4(查看合作社订单) 5(根据团购id查询所有订单信息 )
	 * 			   6(查看可删除团购信息) 7(根据用户id查询未完成订单 )8(查看订单详细 )9(根据指定订单id和状态查询订单信息)10(查询退货信息)11(退款信息)
	 * 			   12(通过id查询可删除的订单) 13providerId未完成的订单
	 * @param params
	 * @return
	 */
	public List<Order_list> getOrder_listByMethod(Oper oper,int type,String providerId,String ...params);

	public List<Order_list> getOrder_listDetail(String orderId,boolean bigdeal);

	public boolean updateOrderList(Order_list order_list);

	public boolean deleteOrderList(String orderId,HttpServletRequest request);

	public String cancelOrder(String orderId);

	public String applyRefund(String orderId);

	public String orderPayment(String orderId,int orderType);

	public List<Order_list> getNotFinishedOrderList(String userId,PagePojo pagePojo);

	public List<Order_list> getFinishedOrderList(String userId,PagePojo pagePojo);

	public OrderListResult getAllOrderList(String userId,PagePojo pagePojo);
	
	public List<Order_detail> getAllShopOrderList(String userId);


	public List existsOrderListByProductId(String userId,String productId);

}
