package com.qushop.order.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.qushop.common.entity.CartUtil;
import com.qushop.common.util.Constants;
import com.qushop.common.util.PagePojo;
import com.qushop.common.util.PublicUtil;
import com.qushop.common.util.ShopTemp;
import com.qushop.common.util.ShoppingCart;
import com.qushop.dict.entity.Brand_vendor;
import com.qushop.musicains.service.business.LeaseBusinessService;
import com.qushop.order.entity.Order_detail;
import com.qushop.order.entity.Order_list;
import com.qushop.order.service.Order_listService;
import com.qushop.prod.entity.Product;
import com.qushop.prod.entity.ProductType;
import com.qushop.prod.entity.Product_ext_shop;
import com.qushop.user.entity.User;
import com.qushop.user.entity.User_Ext_Enterprise;
import com.qushop.user.entity.User_Ext_Personal;
import com.qushop.user.service.UserAddressService;


@Controller
@RequestMapping("/order/orderList/")
public class Order_listController {

	@Resource
	Order_listService order_listService;
	
	@Resource
	UserAddressService userAddressService;
	
	@Resource
	LeaseBusinessService leaseBusinessService;
	
	@RequestMapping(value="addOrder_list.action",method=RequestMethod.POST)
	public Object addOrder_list(String userAddressId,Short orderType,Integer payofflineflag, HttpServletRequest request){

		
		if(userAddressId!=null && !"".equals(userAddressId)){
			Object obj = request.getSession().getAttribute(Constants.LOGIN_USER);
			if(!PublicUtil.userLoginState(request)){
				if(!PublicUtil.entUserLoginState(request)){
					return "needLogin";
				}
			}
		}

		User user = null;
		if(orderType==1 || orderType==2 || orderType==4 || orderType==5||orderType==100)
		{
			Object u = request.getSession().getAttribute(Constants.LOGIN_USER);
			if(u!=null){
				user = ((User_Ext_Personal) u).getUser();
			}
			else{
				return "redirect:index.html";
			}
		}
		if(orderType==3)
		{
			Object u = request.getSession().getAttribute(Constants.LOGIN_ENTER_UER);
			if(u!=null){
				user = ((User_Ext_Enterprise) u).getUser();
			}
			else{
				return "redirect:index.html";
			}
		}
		switch (orderType) {
		case 1:
			List<ShopTemp> shopsList = new ArrayList<ShopTemp>();
			ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute(Constants.SHOPPING_CART);
			//得到购物车哈希表
			Map map = shoppingCart.getMap();
			for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
				String key = iter.next()+"";
				Map<String,Object> productMap = (Map<String, Object>) map.get(key);
				Product product = (Product) productMap.get("product");

				//得到当前产品类型
				int cartType = Integer.parseInt(productMap.get("cartType").toString());
				//当前产品个数
				int productCount = Integer.parseInt(productMap.get("productCount").toString());
				
				ShopTemp shopTemp = new ShopTemp();
				shopTemp.setCount(productCount);
				if(cartType==0)
				{
					//当前产品促销标志
					int promoteflag = Integer.parseInt(productMap.get("promoteflag").toString());
					if(promoteflag==1){
						//当前产品促销价格
						double promotePrice = Double.parseDouble(productMap.get("promotePrice").toString());
						shopTemp.setPrice(promotePrice);
						shopTemp.setTotalamt(promotePrice * productCount);
					}else{
						//当前产品价格
						double originalPrice = Double.parseDouble(productMap.get("originalPrice").toString());
						shopTemp.setPrice(originalPrice);
						shopTemp.setTotalamt(originalPrice * productCount);
					}
				}
				else if(cartType==1)
				{
					//当前产品合作社价格
					double shopPrice = Double.parseDouble(productMap.get("shopPrice").toString());
					shopTemp.setPrice(shopPrice);
					shopTemp.setTotalamt(shopPrice * productCount);
				}
				shopTemp.setProduct(product);
				shopTemp.setProviderId(product.getProviderId());
				shopsList.add(shopTemp);
			}
			Collections.sort(shopsList, new Comparator<ShopTemp>() {
				@Override
				public int compare(ShopTemp s0, ShopTemp s1) {
					return s0.getProviderId().compareTo(s1.getProviderId());
				}
			});
			
			order_listService.addOrder(shopsList, orderType, user.getUserId(),userAddressId,payofflineflag,request);
			request.getSession().removeAttribute(Constants.SHOPPING_CART);
			break;
		case 2:
			order_listService.addOrder(null, orderType, user.getUserId(),userAddressId,payofflineflag,request);
			break;
		case 3:
			order_listService.addOrder(null, orderType, user.getUserId(),userAddressId,payofflineflag,request);
			break;
		case 4:
			order_listService.addOrder(null, orderType, user.getUserId(),userAddressId,payofflineflag,request);
			break;
		case 5:
			order_listService.addOrder(null, orderType, user.getUserId(),userAddressId,payofflineflag,request);
			break;
			
		case 100:
			int productCount = (int)request.getAttribute("count");
			String productId1 = request.getParameter("productId");

			int lease_period =(int)request.getAttribute("lease_period");
			String requireInvoice = request.getParameter("requireinvoice");
//			short invoiceType = Short.valueOf(request.getParameter("invoicetype"));
//			String invoiceTitle = request.getParameter("invoicetitle");
//			String invoicecontent = request.getParameter("invoicecontent");
			//String paymentway = request.getParameter("paymentway");
//			String remark = request.getParameter("remark");
			String paymentway="01";
			order_listService.addLeaseOrder( orderType, user.getUserId(),userAddressId,0,productId1,productCount,lease_period,(short)1,"",paymentway);
			break;

		default:
			break;
		}
		
		RedirectAttributesModelMap modelMap = new RedirectAttributesModelMap();
		modelMap.put("test", "testvalue");
		//调用支付接口
		
//		switch (orderType) {
//			case 1:
//				return "redirect:orderList.do";
//			case 2:
//			case 3:
//				return "redirect:orderList.do?orderType="+orderType;
//			case 4:
//				return "redirect:orderList.do";
//			case 5:
//				return "redirect:orderList.do";
//			case 100:
//				//return "forward:/order/orderList/orderList.shtml?orderType="+orderType;
//			default:
//				break;
//		}
		return "redirect:orderList.do?orderType="+orderType;
	}
	
	@RequestMapping(value="cancelOrder.action",method=RequestMethod.POST)
	@ResponseBody
	public Object cancelOrder(String orderId){
		String str = order_listService.cancelOrder(orderId);
		return str;
	}
	
	@RequestMapping(value="orderPayment.action",method=RequestMethod.POST)
	@ResponseBody
	public Object orderPayment(String orderId,Integer orderType){
		String str = order_listService.orderPayment(orderId,orderType);
		return str;
	}
	
	@RequestMapping(value="applyRefund.action",method=RequestMethod.POST)
	@ResponseBody
	public Object applyRefund(String orderId){
		String str = order_listService.applyRefund(orderId);
		return str;
	}
	
	
	@RequestMapping("getOrderListByUserId.action")
	@ResponseBody
	public Object getOrder_listByUserId(Integer type,Integer orderType,String orderId,HttpServletRequest request){
		
		User user = null;
		if(orderType==1 || orderType==2)
		{
			if(!PublicUtil.userLoginState(request)){
				return "needLogin";
			}
			User_Ext_Personal user_Ext_Personal = PublicUtil.getUserOfSession(request);
			user = user_Ext_Personal.getUser();
		}
		else if(orderType==3)
		{
			if(!PublicUtil.entUserLoginState(request)){
				return "needLoginEnt";
			}
			User_Ext_Enterprise enterprise = (User_Ext_Enterprise) request.getSession().getAttribute(Constants.LOGIN_ENTER_UER);
			user = enterprise.getUser();
		}
		
		List<Order_list> order_listsList =  order_listService.getOrder_listByUserIdAndMethod(type,user.getUserId(),orderType,orderId);
		
		return order_listsList;
			
	}
	
	@ResponseBody
	@RequestMapping("getOrderDetail.action")
	public Object getOrderDetail(String orderId,HttpServletRequest request){
		
		User_Ext_Personal user_Ext_Personal = null;
		if(!PublicUtil.userLoginState(request)){
			return "needLogin";
		}
		if(PublicUtil.entUserLoginState(request)){
			return "entUser";
		}
		user_Ext_Personal = PublicUtil.getUserOfSession(request);
		
		List<Order_list> orderList = order_listService.getOrder_listByUserIdAndMethod(3, user_Ext_Personal.getUserId(), null, orderId);
		return	orderList;
	}
	/**==========================================*/
	@RequestMapping("orderList.do")
	public String getOrderList(String action,String pageNostr,String pageSizestr,HttpServletRequest request)
	{
		
		Integer pageNo = 0;
		Integer pageSize = 12;
		PagePojo pagePojo = new PagePojo();
		pagePojo.setPageno(pageNo);
		pagePojo.setPagesize(pageSize);
		
		if(pageNostr!=null && !pageNostr.equals("")){
			pageNo = Integer.parseInt(pageNostr);
			pagePojo.setPageno(pageNo-1);
		}
		if(pageSizestr!=null && !pageSizestr.equals("")){
			pageSize = Integer.parseInt(pageSizestr);
			pagePojo.setPagesize(pageSize);
		}
		
		List<Order_list> ordersList1 = order_listService.getAllOrderList(PublicUtil.getUserOfSession(request).getUserId(),pagePojo);
		request.setAttribute("orderList", ordersList1);
		
//		if("notfinished".equals(action)){
//			List<Order_list> ordersList1 = order_listService.getNotFinishedOrderList(PublicUtil.getUserOfSession(request).getUserId(),pagePojo);
//			request.setAttribute("orderList", ordersList1);
//			request.setAttribute("orderflag", "notfinish");
//		}
//		else{
//			List<Order_list> ordersList = order_listService.getFinishedOrderList(PublicUtil.getUserOfSession(request).getUserId(),pagePojo);
//			request.setAttribute("orderList", ordersList);
//			request.setAttribute("orderflag", "finish");
//		}
		
		request.setAttribute("flag", "shop");
		request.setAttribute("page", pagePojo);
		return "web/orders";
	}
	@RequestMapping("addOrder.shtml")
	public String addOrder_list(HttpServletRequest request,String userAddressId){

		Object obj = request.getSession().getAttribute(Constants.LOGIN_USER);
		if(obj==null){
			request.setAttribute("returnURL", request.getRequestURI());
			return "web/signup";
		}

		CartUtil cartUtil = (CartUtil) request.getSession().getAttribute("cart");
		if(cartUtil==null){
			
		}
		order_listService.addShopOrder(cartUtil,PublicUtil.getUserOfSession(request).getUserId(),userAddressId,0,request);
		
		request.setAttribute("pageNostr", "1");
		request.setAttribute("action", "notfinished");
		request.setAttribute("pageSizestr", "12");
		return "redirect:orderList.do";//edit by irving
	}
	
	@RequestMapping("confirmDelivery.do")
	@ResponseBody
	public void addOrder_list(HttpServletRequest request,String orderId,String productId){
		User_Ext_Personal userOfSession = PublicUtil.getUserOfSession(request);
		List<Order_list> order_listsList =  order_listService.getOrder_listByUserIdAndMethod(2,userOfSession.getUserId(),0,orderId);
		Order_list order_list = order_listsList.get(0);
		List<Order_detail> order_details = order_list.getOrder_detail();
		for (Order_detail order_detail : order_details) {
			if(order_detail.getProductId().equals(productId)){
				order_detail.setDeliverStatus((short)3);//用户确认收货
			}
		}
		//开始计算日期
		String leaseId= request.getParameter("leaseId");
		if(null!=leaseId&&order_list.getOrderType()==100){
			leaseBusinessService.startCountingRentDates(leaseId);
		}
		
	}
	
	
}
