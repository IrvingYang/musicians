package com.qushop.order.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.Constants;
import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.order.entity.Order_list;
import com.qushop.order.service.Order_listService;
import com.qushop.user.entity.Oper;


@Controller
@RequestMapping("/manage/order")
public class MOrderController {

	@Resource
	Order_listService order_listService;
	
	@RequestMapping(value="getOrderList.do")
	public String getOrderList(String action,Integer typeId,HttpServletRequest request){

		Oper oper = (Oper) request.getSession().getAttribute(Constants.OPER_USER);
		List<Order_list> ordersList = order_listService.getOrder_listByMethod(oper,typeId, oper.getProviderId());
		request.setAttribute("orders", ordersList);
		if("dialog".equals(action)){
			return "admin/lookDialog/lookUpOrder";
		}
		return "admin/productGroupbuyersList";
	}
	
	@RequestMapping("getOrderDetail.do")
	public String getOrderDetail(String[] orderId,HttpServletRequest request){
		
		List<Order_list> orderList = order_listService.getOrder_listByUserIdAndMethod(3, null, 0, orderId[0]);
		request.setAttribute("orderList", orderList);
		request.setAttribute("orderType", orderList.get(0).getOrderType());
		return "admin/orderProduct";
	}
	
	@RequestMapping("getOrderUserByGroupId.do")
	@ResponseBody
	public Object getOrderUserByGroupId(String groupbuyid,HttpServletRequest request){
		Oper oper = (Oper) request.getSession().getAttribute(Constants.OPER_USER);
		List<Order_list> ordersList = order_listService.getOrder_listByMethod(oper,5,null, groupbuyid);
		return ordersList;
	}
	
	
	@RequestMapping("getAllOrder_List.do")
	public String getAllOrder_List(Integer typeId,String status,String beginTime,String endTime,HttpServletRequest request){
		
		Oper oper = (Oper) request.getSession().getAttribute(Constants.OPER_USER);
		
		List<Order_list> ordersList = order_listService.getOrder_listByMethod(oper,typeId, oper.getProviderId(),status,beginTime,endTime);
		request.setAttribute("status", status);
		request.setAttribute("typeId", typeId);
		request.setAttribute("ordersList", ordersList);
		request.setAttribute("beginTime", beginTime);
		request.setAttribute("endTime", endTime);
		String redirectPage = "";
		switch (typeId) {
		case 1:
			redirectPage = "admin/productOrderList";
			break;
		case 2:
			redirectPage = "admin/groupOrderList";
			break;
		case 3:
			redirectPage = "admin/bigDealOrderList";
			break;
		case 10:
			redirectPage = "admin/returnProductOrderList";
			break;
		case 11:
			redirectPage = "admin/returnMoneyOrderList";
			break;
		}
		
		return redirectPage;
	}
	
	@RequestMapping("deleteOrder.do")
	@ResponseBody
	public Object deleteOrder(String orderId,Integer typeId,HttpServletRequest request){

		String orderIds[] = orderId.split(",");
		Oper oper = (Oper) request.getSession().getAttribute(Constants.OPER_USER);
		for (String orId : orderIds) 
		{
			List<Order_list> order_lists = order_listService.getOrder_listByMethod(oper, 12, oper.getProviderId(), orId);
			if(order_lists!=null && order_lists.size()>0){
				return DwzUtil.opFailed("选中数据含有没完成订单，操作拒绝", "");
			}
		}
		boolean bool = false;
		for (String orId : orderIds) {
			List<Order_list> detaillist = order_listService.getOrder_listByMethod(oper,8, oper.getProviderId(),orId);
			if(detaillist!=null && detaillist.size()>0){
				Order_list orderList = detaillist.get(0);
				if(!orderList.getProviderid().equals(oper.getProviderId())){
					return DwzUtil.opFailed("选中数据含有不是当前操作员合作社订单，不可操作", "");
				}
			}
		}
		for (String orId : orderIds) {
			bool = order_listService.deleteOrderList(orId, request);
		}
		String refreshPage = "";
		switch (typeId) {
		case 1:
			refreshPage = "productOrder";
			break;
		case 2:
			refreshPage="groupbuyOrder";
			break;
		case 3:
			refreshPage="bigdealOrder";
			break;
		case 10:
			refreshPage = "reProductOrder";
			break;
		case 11:
			refreshPage = "reMoneyrder";
			break;
		}
		if(bool){
			return DwzUtil.opSuccess("操作成功", refreshPage);
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("todelivery.do")
	public Object todelivery(String[] orderId,HttpServletRequest request){

		Oper oper = (Oper) request.getSession().getAttribute(Constants.OPER_USER);
		List<Order_list> order_listsList = order_listService.getOrder_listByMethod(oper,8, oper.getOperId(), orderId[0]);
		if(order_listsList!=null && order_listsList.size()>0){
			request.setAttribute("orderList", order_listsList.get(0));
		}
		return "admin/orderbusiness/delivery";
	}
	@RequestMapping(value="orderBusiness.do")
	@ResponseBody
	public Object delivery(Integer typeId,String[] orderId,String action,HttpServletRequest request){

		Oper oper = (Oper) request.getSession().getAttribute(Constants.OPER_USER);
		String expressname = request.getParameter("orderexpress.expressid");
		String waybill = request.getParameter("waybill");
		
		String refreshPage = "";
		List<Order_list> lists = null;
		boolean bool = false;
		//查询订单详细
		List<Order_list> detaillist = order_listService.getOrder_listByMethod(oper,8, oper.getProviderId(),orderId[0]);

		if(detaillist!=null && detaillist.size()>0){
			Order_list orderList = detaillist.get(0);
			if(!orderList.getProviderid().equals(oper.getProviderId())){
				return DwzUtil.opFailed("您不是该订单合作社操作员，不可操作", "");
			}
		}
		
		if("delivery".equals(action)){
			//判断是否等待配送状态
			lists = order_listService.getOrder_listByMethod(oper,9, oper.getProviderId(), orderId[0],"06");
			if(lists==null || lists.size()<=0){
				return DwzUtil.opFailed("此订单不是等待配送状态，不可发货", "");
			}else{
				Order_list orderList = null;
				if(detaillist!=null && detaillist.size()>0){
					orderList = detaillist.get(0);
					orderList.setExpressnumber(waybill);
					orderList.setExpressvendor(expressname);
					orderList.setStatus("07");
					orderList.setUpdateTime(new Date());
					orderList.setLastUpdateTime(new Date());
					orderList.setOperid(PublicUtil.getOper(request).getOperId());
					bool = order_listService.updateOrderList(orderList);
				}	
			}
		}
		else if("cancelOrder".equals(action)){
			lists = order_listService.getOrder_listByMethod(oper,9, oper.getProviderId(), orderId[0],"01");
			if(lists==null || lists.size()<=0){
				return DwzUtil.opFailed("此订单不是下单状态，不可撤销", "");
			}else{
				Order_list orderList = null;
				if(detaillist!=null && detaillist.size()>0){
					orderList = detaillist.get(0);
					orderList.setExpressnumber(waybill);
					orderList.setExpressvendor(expressname);
					orderList.setStatus("02");
					orderList.setUpdateTime(new Date());
					orderList.setLastUpdateTime(new Date());
					orderList.setOperid(PublicUtil.getOper(request).getOperId());
					bool = order_listService.updateOrderList(orderList);
				}	
			}
		}
		else if("endOrder".equals(action)){
			//判断是否处于配送中
			lists = order_listService.getOrder_listByMethod(oper,9, oper.getProviderId(), orderId[0],"07");
			if(lists==null || lists.size()<=0){
				return DwzUtil.opFailed("此订单不是配送状态，不可完成","");
			}else{
				Order_list orderList = null;
				if(detaillist!=null && detaillist.size()>0){
					orderList = detaillist.get(0);
					if(orderList.getPayofflineflag()==0)
					{
						return DwzUtil.opFailed("此订单不是货到付款订单，不可完成","");
					}
					else
					{
						orderList.setStatus("08");
						orderList.setUpdateTime(new Date());
						orderList.setLastUpdateTime(new Date());
						orderList.setOperid(PublicUtil.getOper(request).getOperId());
						bool = order_listService.updateOrderList(orderList);
					}
				}
			}
		}
		/**
		 * 
		 * 这里需要集成支付宝接口
		 * 
		 * 
		 */
		else if("returnMoney".equals(action)){
			//判断是否处于同意退款状态组
			lists = order_listService.getOrder_listByMethod(oper,9, oper.getProviderId(), orderId[0],"12");
			if(lists==null || lists.size()<=0){
				return DwzUtil.opFailed("此订单不是等待退款状态，不可完成","");
			}else{
				Order_list orderList = null;
				if(detaillist!=null && detaillist.size()>0){
					orderList = detaillist.get(0);
					orderList.setStatus("13");
					orderList.setUpdateTime(new Date());
					orderList.setLastUpdateTime(new Date());
					orderList.setOperid(PublicUtil.getOper(request).getOperId());
					bool = order_listService.updateOrderList(orderList);
				}
			}
			refreshPage="reMoneyrder";
		}
		switch (typeId) {
		//商城
		case 1:
			refreshPage="productOrder";
			
			if("confirmpay".equals(action)){
				//判断是否付款状态
				lists = order_listService.getOrder_listByMethod(oper,9, oper.getProviderId(), orderId[0],"03");
				if(lists==null || lists.size()<=0){
					return DwzUtil.opFailed("此订单不是付款状态，不可确认","");
				}else{
					Order_list orderList = null;
					if(detaillist!=null && detaillist.size()>0){
						orderList = detaillist.get(0);
						orderList.setStatus("06");
						orderList.setUpdateTime(new Date());
						orderList.setLastUpdateTime(new Date());
						orderList.setOperid(PublicUtil.getOper(request).getOperId());
						bool = order_listService.updateOrderList(orderList);
					}
				}
			}
			
			break;
		//团购
		case 2:
			refreshPage="groupbyOrder";
			if("confirmpay".equals(action)){
				//判断是否处于成功开团状态
				lists = order_listService.getOrder_listByMethod(oper,9, oper.getProviderId(), orderId[0],"05");
				if(lists==null || lists.size()<=0){
					return DwzUtil.opFailed("此订单不是成功开团状态，不可确认","");
				}else{
					Order_list orderList = null;
					if(detaillist!=null && detaillist.size()>0){
						orderList = detaillist.get(0);
						orderList.setStatus("06");
						orderList.setUpdateTime(new Date());
						orderList.setLastUpdateTime(new Date());
						orderList.setOperid(PublicUtil.getOper(request).getOperId());
						bool = order_listService.updateOrderList(orderList);
					}
				}
			}
			break;
		//大宗交易
		case 3:
			refreshPage="bigdealOrder";
			break;
			
		case 10:
			
			if("acceptReturnProduct".equals(action)){
				//判断是否处于退货处理状态
				lists = order_listService.getOrder_listByMethod(oper,9, oper.getProviderId(), orderId[0],"09");
				if(lists==null || lists.size()<=0){
					return DwzUtil.opFailed("此订单不是退货处理状态，不可同意","");
				}else{
					Order_list orderList = null;
					if(detaillist!=null && detaillist.size()>0){
						orderList = detaillist.get(0);
						orderList.setStatus("10");
						orderList.setUpdateTime(new Date());
						orderList.setLastUpdateTime(new Date());
						orderList.setOperid(PublicUtil.getOper(request).getOperId());
						bool = order_listService.updateOrderList(orderList);
					}
				}
				
			}else if("notAcceptReturnProduct".equals(action)){

				//判断是否退货处理状态
				lists = order_listService.getOrder_listByMethod(oper,9, oper.getProviderId(), orderId[0],"09");
				if(lists==null || lists.size()<=0){
					return DwzUtil.opFailed("此订单不是退货处理状态，不可操作","");
				}else{
					Order_list orderList = null;
					if(detaillist!=null && detaillist.size()>0){
						orderList = detaillist.get(0);
						orderList.setStatus("11");
						orderList.setUpdateTime(new Date());
						orderList.setLastUpdateTime(new Date());
						orderList.setOperid(PublicUtil.getOper(request).getOperId());
						bool = order_listService.updateOrderList(orderList);
					}
				}
				
			}
			else if("receivedReProduct".equals(action)){

				//判断是否同意退货状态
				lists = order_listService.getOrder_listByMethod(oper,9, oper.getProviderId(), orderId[0],"10");
				if(lists==null || lists.size()<=0){
					return DwzUtil.opFailed("此订单不是同意退货状态，不可完成操作","");
				}else{
					Order_list orderList = null;
					if(detaillist!=null && detaillist.size()>0){
						orderList = detaillist.get(0);
						orderList.setStatus("12");
						orderList.setUpdateTime(new Date());
						orderList.setLastUpdateTime(new Date());
						orderList.setOperid(PublicUtil.getOper(request).getOperId());
						bool = order_listService.updateOrderList(orderList);
					}
				}
			}
			break;
		}
		
		if(bool){
			return DwzUtil.opSuccess("操作成功", refreshPage);
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
}
