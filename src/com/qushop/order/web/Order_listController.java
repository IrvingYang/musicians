package com.qushop.order.web;

import java.util.ArrayList;
import java.util.Collection;
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

	@RequestMapping(value = "addOrder_list.do", method = RequestMethod.POST)
	public Object addOrder_list(String userAddressId, short orderTypes[], Integer payofflineflag,
			HttpServletRequest request) {

		for (short orderType : orderTypes) {

			if (userAddressId != null && !"".equals(userAddressId)) {
				Object obj = request.getSession().getAttribute(Constants.LOGIN_USER);
				if (!PublicUtil.userLoginState(request)) {
					if (!PublicUtil.entUserLoginState(request)) {
						return "needLogin";
					}
				}
			}

			User user = null;
			if (orderType == 1 || orderType == 2 || orderType == 4 || orderType == 5 || orderType == 100) {
				Object u = request.getSession().getAttribute(Constants.LOGIN_USER);
				if (u != null) {
					user = ((User_Ext_Personal) u).getUser();
				} else {
					return "redirect:index.html";
				}
			}
			if (orderType == 3) {
				Object u = request.getSession().getAttribute(Constants.LOGIN_ENTER_UER);
				if (u != null) {
					user = ((User_Ext_Enterprise) u).getUser();
				} else {
					return "redirect:index.html";
				}
			}
			List<ShopTemp> shopsList = new ArrayList<ShopTemp>();
			ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute(Constants.SHOPPING_CART);
			// 得到购物车哈希表
			Map<String, Map<String, Object>> map = shoppingCart.getMap();

			for (Map<String, Object> productMap : map.values()) {
				Product product = (Product) productMap.get("product");

				// 得到当前产品类型
				int cartType = (int) productMap.get("cartType");

				// 当前产品个数
				int productCount = (int) productMap.get("productCount");

				ShopTemp shopTemp = new ShopTemp();
				shopTemp.setCount(productCount);
				if (cartType == 0) {
					// 当前产品促销标志
					int promoteflag = (int) productMap.get("promoteflag");
					if (promoteflag == 1) {
						// 当前产品促销价格
						double promotePrice = (double) productMap.get("promotePrice");
						shopTemp.setPrice(promotePrice);
						shopTemp.setTotalamt(promotePrice * productCount);
					} else {
						// 当前产品价格
						double originalPrice = (double) productMap.get("originalPrice");
						shopTemp.setPrice(originalPrice);
						shopTemp.setTotalamt(originalPrice * productCount);
					}
				} else if (cartType == 1) {
					// 当前产品合作社价格
					double shopPrice = (double) productMap.get("shopPrice");
					shopTemp.setPrice(shopPrice);
					shopTemp.setTotalamt(shopPrice * productCount);
				} else if (cartType == 2) {
					// 当前产品合作社价格
					double shopPrice = (double) productMap.get("shopPrice");
					shopTemp.setYajin((double) productMap.get("yajin"));
					shopTemp.setLeaseCycle(Integer.valueOf((String) productMap.get("leaseCycle")));
					shopTemp.setTotalamt(shopPrice * productCount);
					shopTemp.setLeaseType((int)productMap.get("leaseType"));
				}

				shopTemp.setProduct(product);
				shopTemp.setProviderId(product.getProviderId());
				shopsList.add(shopTemp);
			}
			List<ShopTemp> shopItems = new ArrayList<ShopTemp>();
			List<ShopTemp> leaseItems = new ArrayList<ShopTemp>();
			for (ShopTemp shopTemp : shopsList) {
				if (shopTemp.getLeaseCycle() == 0) {
					shopItems.add(shopTemp);
				} else {
					leaseItems.add(shopTemp);
				}
			}

			switch (orderType) {
			case 1:
				// Collections.sort(shopsList, new Comparator<ShopTemp>() {
				// @Override
				// public int compare(ShopTemp s0, ShopTemp s1) {
				// return s0.getProviderId().compareTo(s1.getProviderId());
				// }
				// });

				order_listService.addOrder(shopItems, orderType, user.getUserId(), userAddressId, payofflineflag,
						request);

				for (ShopTemp shopTemp : shopItems) {
					map.remove(shopTemp.getProduct().getProductId() + "|" + orderType);
				}

				break;
			case 2:
				order_listService.addOrder(null, orderType, user.getUserId(), userAddressId, payofflineflag, request);
				break;
			case 3:
				order_listService.addOrder(null, orderType, user.getUserId(), userAddressId, payofflineflag, request);
				break;
			case 4:
				order_listService.addOrder(null, orderType, user.getUserId(), userAddressId, payofflineflag, request);
				break;
			case 5:
				order_listService.addOrder(null, orderType, user.getUserId(), userAddressId, payofflineflag, request);
				break;

			case 100:

				// short invoiceType =
				// Short.valueOf(request.getParameter("invoicetype"));
				// String invoiceTitle =
				// request.getParameter("invoicetitle");
				// String invoicecontent =
				// request.getParameter("invoicecontent");
				// String paymentway = request.getParameter("paymentway");
				// String remark = request.getParameter("remark");

				String paymentway = "01";
				order_listService.addLeaseOrder(leaseItems, orderType, user.getUserId(), userAddressId, 0, 1, "",
						paymentway);
				break;

			default:
				break;
			}

		}
		request.getSession().removeAttribute(Constants.SHOPPING_CART);

		RedirectAttributesModelMap modelMap = new RedirectAttributesModelMap();
		modelMap.put("test", "testvalue");
		// 调用支付接口

		// switch (orderType) {
		// case 1:
		// return "redirect:orderList.do";
		// case 2:
		// case 3:
		// return "redirect:orderList.do?orderType="+orderType;
		// case 4:
		// return "redirect:orderList.do";
		// case 5:
		// return "redirect:orderList.do";
		// case 100:
		// //return
		// "forward:/order/orderList/orderList.shtml?orderType="+orderType;
		// default:
		// break;
		// }
		return "redirect:orderList.do";
	}

	@RequestMapping(value = "cancelOrder.action", method = RequestMethod.POST)
	@ResponseBody
	public Object cancelOrder(String orderId) {
		String str = order_listService.cancelOrder(orderId);
		return str;
	}

	@RequestMapping(value = "orderPayment.action", method = RequestMethod.POST)
	@ResponseBody
	public Object orderPayment(String orderId, Integer orderType) {
		String str = order_listService.orderPayment(orderId, orderType);
		return str;
	}

	@RequestMapping(value = "applyRefund.action", method = RequestMethod.POST)
	@ResponseBody
	public Object applyRefund(String orderId) {
		String str = order_listService.applyRefund(orderId);
		return str;
	}

	@RequestMapping("getOrderListByUserId.action")
	@ResponseBody
	public Object getOrder_listByUserId(Integer type, Integer orderType, String orderId, HttpServletRequest request) {

		User user = null;
		if (orderType == 1 || orderType == 2) {
			if (!PublicUtil.userLoginState(request)) {
				return "needLogin";
			}
			User_Ext_Personal user_Ext_Personal = PublicUtil.getUserOfSession(request);
			user = user_Ext_Personal.getUser();
		} else if (orderType == 3) {
			if (!PublicUtil.entUserLoginState(request)) {
				return "needLoginEnt";
			}
			User_Ext_Enterprise enterprise = (User_Ext_Enterprise) request.getSession()
					.getAttribute(Constants.LOGIN_ENTER_UER);
			user = enterprise.getUser();
		}

		List<Order_list> order_listsList = order_listService.getOrder_listByUserIdAndMethod(type, user.getUserId(),
				orderType, orderId);

		return order_listsList;

	}

	@ResponseBody
	@RequestMapping("getOrderDetail.action")
	public Object getOrderDetail(String orderId, HttpServletRequest request) {

		User_Ext_Personal user_Ext_Personal = null;
		if (!PublicUtil.userLoginState(request)) {
			return "needLogin";
		}
		if (PublicUtil.entUserLoginState(request)) {
			return "entUser";
		}
		user_Ext_Personal = PublicUtil.getUserOfSession(request);

		List<Order_list> orderList = order_listService.getOrder_listByUserIdAndMethod(3, user_Ext_Personal.getUserId(),
				null, orderId);
		return orderList;
	}

	/** ========================================== */
	@RequestMapping("orderList.do")
	public String getOrderList(String action, String pageNostr, String pageSizestr, HttpServletRequest request) {

		Integer pageNo = 0;
		Integer pageSize = 12;
		PagePojo pagePojo = new PagePojo();
		pagePojo.setPageno(pageNo);
		pagePojo.setPagesize(pageSize);

		if (pageNostr != null && !pageNostr.equals("")) {
			pageNo = Integer.parseInt(pageNostr);
			pagePojo.setPageno(pageNo - 1);
		}
		if (pageSizestr != null && !pageSizestr.equals("")) {
			pageSize = Integer.parseInt(pageSizestr);
			pagePojo.setPagesize(pageSize);
		}

		List<Order_list> ordersList1 = order_listService
				.getAllOrderList(PublicUtil.getUserOfSession(request).getUserId(), pagePojo);
		request.setAttribute("orderList", ordersList1);

		// if("notfinished".equals(action)){
		// List<Order_list> ordersList1 =
		// order_listService.getNotFinishedOrderList(PublicUtil.getUserOfSession(request).getUserId(),pagePojo);
		// request.setAttribute("orderList", ordersList1);
		// request.setAttribute("orderflag", "notfinish");
		// }
		// else{
		// List<Order_list> ordersList =
		// order_listService.getFinishedOrderList(PublicUtil.getUserOfSession(request).getUserId(),pagePojo);
		// request.setAttribute("orderList", ordersList);
		// request.setAttribute("orderflag", "finish");
		// }

		request.setAttribute("flag", "shop");
		request.setAttribute("page", pagePojo);
		return "web/orders";
	}

	@RequestMapping("addOrder.shtml")
	public String addOrder_list(HttpServletRequest request, String userAddressId) {

		Object obj = request.getSession().getAttribute(Constants.LOGIN_USER);
		if (obj == null) {
			request.setAttribute("returnURL", request.getRequestURI());
			return "web/signup";
		}

		CartUtil cartUtil = (CartUtil) request.getSession().getAttribute("cart");
		if (cartUtil == null) {

		}
		order_listService.addShopOrder(cartUtil, PublicUtil.getUserOfSession(request).getUserId(), userAddressId, 0,
				request);

		request.setAttribute("pageNostr", "1");
		request.setAttribute("action", "notfinished");
		request.setAttribute("pageSizestr", "12");
		return "redirect:orderList.do";// edit by irving
	}

	@RequestMapping("confirmDelivery.do")
	@ResponseBody
	public void addOrder_list(HttpServletRequest request, String orderId, String productId) {
		User_Ext_Personal userOfSession = PublicUtil.getUserOfSession(request);
		List<Order_list> order_listsList = order_listService.getOrder_listByUserIdAndMethod(2,
				userOfSession.getUserId(), 0, orderId);
		Order_list order_list = order_listsList.get(0);
		List<Order_detail> order_details = order_list.getOrder_detail();
		for (Order_detail order_detail : order_details) {
			if (order_detail.getProductId().equals(productId)) {
				order_detail.setDeliverStatus((short) 3);// 用户确认收货
			}
		}
		// 开始计算日期
		String leaseId = request.getParameter("leaseId");
		if (null != leaseId && order_list.getOrderType() == 100) {
			leaseBusinessService.startCountingRentDates(leaseId);
		}

	}

}
