package com.qushop.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.entity.CartUtil;
import com.qushop.common.util.PagePojo;
import com.qushop.common.util.PublicUtil;
import com.qushop.common.util.ShopTemp;
import com.qushop.common.util.UtilDate;
import com.qushop.dict.service.ExpressService;
import com.qushop.dict.service.Payment_MethodService;
import com.qushop.musicains.entity.LeaseConfig;
import com.qushop.musicains.service.LeaseConfigService;
import com.qushop.musicains.service.business.LeaseBusinessService;
import com.qushop.order.entity.Order_detail;
import com.qushop.order.entity.Order_list;
import com.qushop.order.service.Order_detailService;
import com.qushop.order.service.Order_listService;
import com.qushop.prod.entity.Product;
import com.qushop.prod.entity.Product_ext_bigDeal;
import com.qushop.prod.entity.Product_ext_groupBuy;
import com.qushop.prod.entity.Product_ext_shop;
import com.qushop.prod.service.ProductReviewService;
import com.qushop.prod.service.ProductService;
import com.qushop.prod.service.Product_ext_bigDealService;
import com.qushop.prod.service.Product_ext_groupBuyService;
import com.qushop.prod.service.Product_ext_shopService;
import com.qushop.user.entity.Oper;
import com.qushop.user.entity.User;
import com.qushop.user.entity.User_Ext_Enterprise;
import com.qushop.user.entity.User_Ext_Personal;
import com.qushop.user.service.UserAddressService;
import com.qushop.user.service.UserService;

@Service
public class Order_listServiceImpl implements Order_listService {

	@Resource
	CommonDao<Order_list> listDao;

	@Resource
	CommonDao<Order_detail> detailDao;

	@Resource
	CommonDao commonDao;

	@Resource
	ProductService productService;

	@Resource
	Product_ext_groupBuyService groupBuyService;

	@Resource
	Product_ext_bigDealService bigDealService;

	@Resource
	Product_ext_shopService ext_shopService;

	@Resource
	ExpressService expressService;

	@Resource
	UserAddressService userAddressService;

	@Resource
	UserService userService;

	@Resource
	Payment_MethodService payment_MethodService;

	@Resource
	Order_detailService detailService;

	@Resource
	ProductReviewService productReviewService;

	@Resource
	LeaseConfigService leaseConfigService;
	
	@Resource
	LeaseBusinessService leaseBusinessService;

	@Override
	public void addOrder(List<ShopTemp> shopsList, short orderType, String userId, String userAddressId,
			Integer payofflineflag, HttpServletRequest request) {

		Order_list order_list = new Order_list();
		order_list.setLastUpdateTime(new Date());
		Order_detail order_detail = null;
		switch (orderType) {
		case 1:
			String providerId = "";
			for (ShopTemp shopTemp : shopsList) {

				productService.updateStockNumber(shopTemp.getProduct().getProductId(), 0 - shopTemp.getCount());
				if (shopTemp.getProviderId().equals(providerId)) {
					// 同一订单
					order_list.setTotalamt(order_list.getTotalamt() + shopTemp.getTotalamt());
					order_detail = new Order_detail();
					order_detail.setOrderId(order_list.getOrderId());
					order_detail.setTotalamt(shopTemp.getTotalamt());
					order_detail.setPrice(shopTemp.getPrice());
					order_detail.setProductId(shopTemp.getProduct().getProductId());
					order_detail.setOrderType(orderType);
					order_detail.setQuantity((short) shopTemp.getCount());
					order_list.setPayofflineflag(payofflineflag.shortValue());
					order_detail.setValidflag((short) 1);
					detailDao.insert(order_detail);
					commonDao.update(order_list);

				} else {

					providerId = shopTemp.getProviderId();
					// 新订单 9999999999
					order_list = new Order_list();
					order_list.setStatus("01");
					order_list.setOrderType(orderType);
					order_list.setUserId(userId);
					order_list.setUserAddressId(userAddressId);
					order_list.setProviderid(shopTemp.getProviderId());
					order_list.setTotalamt(shopTemp.getTotalamt());
					order_list.setOrderId(UtilDate.getNowDateNo_() + "0" + orderType + shopTemp.getProviderId()
							+ (new Random().nextInt(899999) + 100000));// TBD
					order_list.setCreateTime(new Date());
					order_list.setRequireinvoice(Short.parseShort(request.getParameter("requireinvoice")));
					order_list.setInvoicetype(Short.parseShort(request.getParameter("invoicetype")));
					order_list.setInvoicetitle(request.getParameter("invoicetitle"));
					order_list.setInvoicecontent(request.getParameter("invoicecontent"));
					order_list.setPaymentway(request.getParameter("paymentway"));
					order_list.setInvoiceprinted((short) 0);
					order_list.setRemark(request.getParameter("remark"));
					order_list.setLastUpdateTime(new Date());
					order_list.setPayofflineflag(payofflineflag.shortValue());
					order_list.setValidflag((short) 1);
					listDao.insert(order_list);

					order_detail = new Order_detail();
					order_detail.setOrderId(order_list.getOrderId());
					order_detail.setTotalamt(shopTemp.getTotalamt());
					order_detail.setPrice(shopTemp.getPrice());
					order_detail.setProductId(shopTemp.getProduct().getProductId());
					order_detail.setOrderType(orderType);
					order_detail.setQuantity((short) shopTemp.getCount());
					order_detail.setValidflag((short) 1);
					detailDao.insert(order_detail);
				}
			}
			break;

		case 2:
			String count = request.getParameter("count");
			String groupbuyid = request.getParameter("groupbuyid");
			Product_ext_groupBuy groupbuy = null;
			groupBuyService.updateStockNumber(groupbuyid, 0 - Integer.parseInt(count));
			groupbuy = groupBuyService.getgroupBuyProductByMethod(3, groupbuyid, "1").get(0);

			order_list.setStatus("01");
			order_list.setOrderType(orderType);
			order_list.setUserId(userId);
			order_list.setUserAddressId(userAddressId);
			order_list.setProviderid(groupbuy.getProduct().getProviderId());
			order_list.setTotalamt(groupbuy.getGroupBuyPrice() * Integer.parseInt(count));
			order_list.setOrderId(UtilDate.getNowDateNo_() + "0" + orderType + groupbuy.getProduct().getProviderId()
					+ (new Random().nextInt(899999) + 100000));
			order_list.setCreateTime(new Date());
			Short requireinvoice = Short.parseShort(request.getParameter("requireinvoice"));
			order_list.setRequireinvoice(requireinvoice);
			if (requireinvoice.equals(1)) {
				order_list.setInvoicetype(Short.parseShort(request.getParameter("invoicetype")));
				order_list.setInvoicetitle(request.getParameter("invoicetitle"));
				order_list.setInvoicecontent(request.getParameter("invoicecontent"));
			}
			order_list.setPaymentway(request.getParameter("paymentway"));
			order_list.setInvoiceprinted((short) 0);
			order_list.setPayofflineflag(payofflineflag.shortValue());
			order_list.setValidflag((short) 1);
			order_list.setRemark(request.getParameter("remark"));

			commonDao.insert(order_list);

			order_detail = new Order_detail();
			order_detail.setOrderId(order_list.getOrderId());
			order_detail.setPrice(groupbuy.getGroupBuyPrice());
			order_detail.setProductId(groupbuy.getGroupbuyid());
			order_detail.setOrderType(orderType);
			order_detail.setQuantity(Short.parseShort(count));
			order_detail.setTotalamt(groupbuy.getGroupBuyPrice() * Integer.parseInt(count));
			order_detail.setValidflag((short) 1);
			detailDao.insert(order_detail);

			break;
		case 3:
			String bigdealcount = request.getParameter("count");
			String bigdealid = request.getParameter("bigdealid");
			Product_ext_bigDeal bigDeal = bigDealService.getbigDealProductByMethod(3, bigdealid, "1").get(0);

			order_list.setStatus("08");
			order_list.setOrderType(orderType);
			order_list.setProviderid(bigDeal.getProduct().getProviderId());
			order_list.setTotalamt(bigDeal.getReferencePrice() * Integer.parseInt(bigdealcount));
			order_list.setUserId(userId);
			order_list.setOrderId(UtilDate.getNowDateNo_() + "0" + orderType + bigDeal.getProduct().getProviderId()
					+ (new Random().nextInt(899999) + 100000));
			order_list.setCreateTime(new Date());
			order_list.setInvoiceprinted((short) 0);
			order_list.setRemark(request.getParameter("remark"));
			order_list.setPayofflineflag((short) 1);
			order_list.setValidflag((short) 1);

			commonDao.insert(order_list);

			order_detail = new Order_detail();
			order_detail.setOrderId(order_list.getOrderId());
			order_detail.setPrice(bigDeal.getReferencePrice());
			order_detail.setProductId(bigDeal.getBigdealId());
			order_detail.setOrderType(orderType);
			order_detail.setQuantity(Short.parseShort(bigdealcount));
			order_detail.setTotalamt(bigDeal.getReferencePrice() * Integer.parseInt(bigdealcount));
			order_detail.setValidflag((short) 1);
			detailDao.insert(order_detail);
			break;

		case 4:
			String productCount = request.getParameter("count");
			String productId = request.getParameter("productId");
			Product_ext_shop product_ext_shop1 = null;
			product_ext_shop1 = ext_shopService.getShopProductByMethod(5, productId).get(0);

			order_list.setStatus("01");
			order_list.setOrderType((short) 1);
			order_list.setUserId(userId);
			order_list.setUserAddressId(userAddressId);
			order_list.setProviderid(product_ext_shop1.getProduct().getProviderId());
			if (product_ext_shop1.getPromoteflag() == 1) {
				order_list.setTotalamt(product_ext_shop1.getPromotePrice() * Integer.parseInt(productCount));
			} else {
				order_list.setTotalamt(product_ext_shop1.getOriginalPrice() * Integer.parseInt(productCount));
			}
			order_list.setOrderId(UtilDate.getNowDateNo_() + "0" + orderType
					+ product_ext_shop1.getProduct().getProviderId() + (new Random().nextInt(899999) + 100000));
			order_list.setCreateTime(new Date());
			order_list.setRequireinvoice(Short.parseShort(request.getParameter("requireinvoice")));
			order_list.setInvoicetype(Short.parseShort(request.getParameter("invoicetype")));
			order_list.setInvoicetitle(request.getParameter("invoicetitle"));
			order_list.setInvoicecontent(request.getParameter("invoicecontent"));
			order_list.setPaymentway(request.getParameter("paymentway"));
			order_list.setInvoiceprinted((short) 0);
			order_list.setPayofflineflag(payofflineflag.shortValue());
			order_list.setValidflag((short) 1);
			order_list.setRemark(request.getParameter("remark"));

			commonDao.insert(order_list);

			order_detail = new Order_detail();
			order_detail.setOrderId(order_list.getOrderId());

			if (product_ext_shop1.getPromoteflag() == 1) {
				order_detail.setPrice(product_ext_shop1.getPromotePrice());
			} else {
				order_detail.setPrice(product_ext_shop1.getOriginalPrice());
			}

			order_detail.setProductId(product_ext_shop1.getProductId());
			order_detail.setOrderType(orderType);
			order_detail.setQuantity(Short.parseShort(productCount));

			if (product_ext_shop1.getPromoteflag() == 1) {
				order_detail.setTotalamt(product_ext_shop1.getPromotePrice() * Integer.parseInt(productCount));
			} else {
				order_detail.setTotalamt(product_ext_shop1.getOriginalPrice() * Integer.parseInt(productCount));
			}

			order_detail.setValidflag((short) 1);
			detailDao.insert(order_detail);

			break;

		case 5:
			String providerProductCount = request.getParameter("count");
			String providerProductProductId = request.getParameter("productId");
			Product product = null;
			product = productService.getProductListByMethod(2, null, providerProductProductId).get(0);

			order_list.setStatus("01");
			order_list.setOrderType((short) 1);
			order_list.setUserId(userId);
			order_list.setUserAddressId(userAddressId);
			order_list.setProviderid(product.getProviderId());
			order_list.setTotalamt(product.getShopPrice());
			order_list.setOrderId(UtilDate.getNowDateNo_() + "0" + orderType + product.getProviderId()
					+ (new Random().nextInt(899999) + 100000));
			order_list.setCreateTime(new Date());
			order_list.setRequireinvoice(Short.parseShort(request.getParameter("requireinvoice")));
			order_list.setInvoicetype(Short.parseShort(request.getParameter("invoicetype")));
			order_list.setInvoicetitle(request.getParameter("invoicetitle"));
			order_list.setInvoicecontent(request.getParameter("invoicecontent"));
			order_list.setPaymentway(request.getParameter("paymentway"));
			order_list.setInvoiceprinted((short) 0);
			order_list.setPayofflineflag(payofflineflag.shortValue());
			order_list.setValidflag((short) 1);
			order_list.setRemark(request.getParameter("remark"));

			commonDao.insert(order_list);

			order_detail = new Order_detail();
			order_detail.setOrderId(order_list.getOrderId());

			order_detail.setPrice(product.getShopPrice());

			order_detail.setProductId(product.getProductId());
			order_detail.setOrderType(orderType);
			order_detail.setQuantity(Short.parseShort(providerProductCount));

			order_detail.setTotalamt(product.getShopPrice() * Integer.parseInt(providerProductCount));

			order_detail.setValidflag((short) 1);
			detailDao.insert(order_detail);

			break;

		default:
			break;
		}

	}

	public Order_list addLeaseOrder( short orderType, String userId, String userAddressId,
			Integer payofflineflag,String productId1, int productCount1,int lease_period,int invoicetype,String remark,String paymentway) {

		Order_list order_list = new Order_list();
		order_list.setLastUpdateTime(new Date());
		Order_detail order_detail = null;
		Product_ext_shop product_ext_shop11 = ext_shopService.getShopProductByMethod(5, productId1).get(0);
		
		Double totalamt = leaseBusinessService.calculateTotalRentPrice(productId1, productCount1,
				lease_period,product_ext_shop11.getOriginalPrice() );

		order_list.setTotalamt(totalamt);
		order_list.setStatus("01");
		order_list.setOrderType(orderType);
		order_list.setUserId(userId);
		order_list.setUserAddressId(userAddressId);
		order_list.setProviderid(product_ext_shop11.getProduct().getProviderId());
		
		order_list.setOrderId(UtilDate.getNowDateNo_() + "0" + orderType
				+ product_ext_shop11.getProduct().getProviderId() + (new Random().nextInt(899999) + 100000));
		order_list.setCreateTime(new Date());
	//	order_list.setRequireinvoice(Short.parseShort(request.getParameter("requireinvoice")));
		order_list.setInvoicetype((short)invoicetype);
	//  order_list.setInvoicetitle(request.getParameter("invoicetitle"));
	//	order_list.setInvoicecontent(request.getParameter("invoicecontent"));
		order_list.setPaymentway(paymentway);
	//	order_list.setInvoiceprinted((short) 0);
		order_list.setPayofflineflag(payofflineflag.shortValue());
		order_list.setValidflag((short) 1);
		order_list.setRemark(remark);

		commonDao.insert(order_list);

		order_detail = new Order_detail();
		order_detail.setOrderId(order_list.getOrderId());

		order_detail.setProductId(product_ext_shop11.getProductId());
		order_detail.setOrderType(orderType);
		order_detail.setQuantity((short)productCount1);

		order_detail.setTotalamt(totalamt);

		order_detail.setValidflag((short) 1);
		
		detailDao.insert(order_detail);
		List<Order_detail> order_details=new ArrayList<Order_detail>();
		order_details.add(order_detail);
		
		order_list.setOrder_detail(order_details);
		
		return order_list;
	}

	/**
	 * type 0通过 userId orderType 去取订单列表
	 */
	@Override
	public List<Order_list> getOrder_listByUserIdAndMethod(int type, String userId, Integer orderType,
			String... params) {

		List<Order_list> list = null;

		switch (type) {

		case 0:
			list = listDao.findByHql(
					"from Order_list where userId = ?  and orderType=?  and validflag = 1 order by createTime desc",
					userId, orderType.shortValue());
			break;
		case 1:
			list = listDao.findByHql(
					"from Order_list where userId = ?  and  orderId<>?  and validflag = 1 order by createTime desc",
					userId, params[0]);
			break;
		case 2:
			list = listDao.findByHql("from Order_list where userId = ? and orderId=? and validflag = 1", userId,
					params[0]);
			break;
		case 3:
			list = listDao.findByHql("from Order_list where orderId=? and validflag = 1", params[0]);
			break;
		default:
			break;
		}

		for (Order_list order_list : list) {

			List<Order_detail> detailsList = commonDao.findByHql("from Order_detail where orderId=?",
					order_list.getOrderId());

			if (order_list.getOrderType() != 3) {
				order_list.setUserAddress(userAddressService
						.getUserAddressByMethod(3, order_list.getUserAddressId(), order_list.getUserId()).get(0));
			}
			if (order_list.getExpressvendor() != null && !order_list.getExpressvendor().equals("")) {
				order_list.setExpress(expressService.getExpressByMethod(2, order_list.getExpressvendor()).get(0));
			}

			for (Order_detail order_detail : detailsList) {

				switch (order_list.getOrderType()) {

				case 1:
					order_detail.setProduct(
							productService.getProductListByMethod(2, null, order_detail.getProductId()).get(0));
					break;
				case 2:
					order_detail.setGroupBuy(
							groupBuyService.getgroupBuyProductByMethod(5, order_detail.getProductId(), "1").get(0));
					break;
				case 3:
					order_detail.setBigDeal(
							bigDealService.getbigDealProductByMethod(5, order_detail.getProductId(), "1").get(0));
					break;

				default:
					break;
				}
			}
			order_list.setOrder_detail(detailsList);
		}
		return list;

	}

	@Override
	public List<Order_list> getOrder_listByMethod(Oper oper, int type, String providerId, String... params) {

		List<Order_list> orderLists = new ArrayList();
		String hql = "from Order_list where validflag=1 ";
		if (oper.getPartnerflag() != 0) {
			hql += " and providerId=" + providerId + "  ";
		}

		if (params.length >= 1 && params[0] != null && !"".equals(params[0])) {
			hql += " and status='" + params[0] + "'  ";
		}
		if (params.length >= 2 && params[1] != null && !"".equals(params[1])) {
			hql += " and createTime>= '" + params[1] + "'  ";
		}
		if (params.length >= 3 && params[2] != null && !"".equals(params[2])) {
			hql += " and createTime<= '" + params[2] + " 23:59:99'  ";
		}

		switch (type) {
		case 1:
			orderLists = commonDao.findByHql(hql + "and orderType=1  order by createTime desc");
			break;
		case 2:
			orderLists = commonDao.findByHql(hql + "and orderType=2  order by createTime desc");
			break;
		case 3:
			orderLists = commonDao.findByHql(hql + "and orderType=3  order by createTime desc");
			break;
		// 合作社订单暂时没有用到
		case 4:
			orderLists = commonDao.findByHql(hql + "and orderType=4  order by createTime desc");
			break;
		case 5:
			List<Order_detail> orderDetails = commonDao
					.findByHql("from Order_detail where orderType=2 and productId='" + params[0] + "'");
			for (Order_detail order_detail : orderDetails) {
				List<Order_list> tempList = commonDao.findBySql("select * from tb_order_list where orderId=?",
						Order_list.class, order_detail.getOrderId());

				if (tempList != null && tempList.size() > 0) {

					Order_list order_list = tempList.get(0);
					User user = (User) userService.getUserByMethod(11, order_list.getUserId()).get(0);
					switch (user.getUserType()) {
					case 1:
						User_Ext_Personal personal = (User_Ext_Personal) userService
								.getUserByMethod(12, order_list.getUserId()).get(0);
						user.setPersonal(personal);
						break;
					case 2:
						User_Ext_Enterprise enterprise = (User_Ext_Enterprise) userService
								.getUserByMethod(13, order_list.getUserId()).get(0);
						user.setEnterprise(enterprise);
						break;
					default:
						break;
					}
					order_list.setUser(user);
					orderLists.add(order_list);
				}
			}
			return orderLists;
		case 6:
			// 查询出影响删除团购的订单
			orderLists = commonDao.findBySql("select list.* from tb_order_detail detail,tb_order_list list where "
					+ "detail.orderid = list.orderid and list.validflag=1 and list.status not in ('02','08','13') and detail.productId in ("
					+ params[0] + ") and list.orderType=2", Order_list.class);
			break;

		case 7:
			orderLists = commonDao.findByHql(
					"from Order_list where userId=? and validflag = 1 and (status<>'02' and status<>'08' and status<>'13')",
					params[0]);
			break;
		case 8:
			orderLists = commonDao.findByHql("from Order_list where orderId=? and validflag=1", params[0]);
			break;
		case 9:
			orderLists = commonDao.findByHql("from Order_list where orderId=? and status=? and validflag=1", params);
			break;
		case 10:
			orderLists = commonDao.findByHql(hql + " and (status='09' or status='10' or status='11')");
			break;
		case 11:
			orderLists = commonDao.findByHql(hql + " and (status='10' or status='12' or status='13')");
			break;
		case 12:
			orderLists = commonDao.findByHql("from Order_list where orderId = '" + params[0]
					+ "' and validflag=1 and (status<>'02' and status<>'08' and status<>'13')");
			break;
		case 13:
			orderLists = commonDao.findByHql("from Order_list where providerId=? and status not in ('02','08','13')",
					providerId);
			return orderLists;
		}

		for (Order_list order_list : orderLists) {

			User user = (User) userService.getUserByMethod(11, order_list.getUserId()).get(0);
			order_list.setUser(user);

			if (order_list.getOrderType() == 3) {
				List<Order_detail> detailsList = detailService.getOrderdetailByMethod(1, oper, order_list.getOrderId());
				// .findByHql("from Order_detail where orderId=?", );
				for (Order_detail order_detail : detailsList) {
					Product_ext_bigDeal bigDeal = bigDealService
							.getbigDealProductByMethod(5, order_detail.getProductId(), "1").get(0);
					order_detail.setProduct(bigDeal.getProduct());
				}
				order_list.setOrder_detail(detailsList);
			} else if (order_list.getOrderType() == 2) {
				List<Order_detail> detailsList = detailService.getOrderdetailByMethod(1, oper, order_list.getOrderId());
				// .findByHql("from Order_detail where orderId=?", );
				for (Order_detail order_detail : detailsList) {
					Product_ext_groupBuy bigDeal = groupBuyService
							.getgroupBuyProductByMethod(5, order_detail.getProductId()).get(0);
					order_detail.setProduct(bigDeal.getProduct());
				}
				order_list.setOrder_detail(detailsList);
			} else {
				order_list.setUserAddress(userAddressService
						.getUserAddressByMethod(3, order_list.getUserAddressId(), order_list.getUserId()).get(0));
				order_list.setPayment_Method(
						payment_MethodService.getPayMentByMethod(2, order_list.getPaymentway()).get(0));
			}

			// order_list.setExpress(expressService.getExpressByMethod(2,
			// order_list.getExpressvendor()).get(0));

		}
		return orderLists;
	}

	@Override
	public List<Order_list> getOrder_listDetail(String orderId, boolean bigdeal) {

		List<Order_list> orderLists = new ArrayList();

		orderLists = commonDao.findByHql("from Order_list where orderId=? and validflag=1", orderId);

		for (Order_list order_list : orderLists) {

			User user = (User) userService.getUserByMethod(6, order_list.getUserId()).get(0);
			order_list.setUser(user);

			if (bigdeal) {
				List<Order_detail> detailsList = detailService.getOrderdetailByMethod(1, null, order_list.getOrderId());
				for (Order_detail order_detail : detailsList) {
					Product_ext_bigDeal bigDeal = bigDealService
							.getbigDealProductByMethod(5, order_detail.getProductId(), "1").get(0);
					order_detail.setProduct(bigDeal.getProduct());
				}
				order_list.setOrder_detail(detailsList);
			} else {
				order_list.setUserAddress(userAddressService
						.getUserAddressByMethod(3, order_list.getUserAddressId(), order_list.getUserId()).get(0));
				order_list.setPayment_Method(
						payment_MethodService.getPayMentByMethod(2, order_list.getPaymentway()).get(0));
			}

		}
		return orderLists;
	}

	@Override
	public boolean updateOrderList(Order_list order_list) {

		try {
			commonDao.update(order_list);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	@Override
	public boolean deleteOrderList(String orderId, HttpServletRequest request) {

		boolean bool = false;
		try {
			String sql = "update tb_order_list set validflag=0,lastUpdateTime=?,operid=? where orderId in (" + orderId
					+ ")";
			detailService.deleteOrderDetail(orderId);
			bool = detailDao.executeBySql(sql, new Date(), PublicUtil.getOper(request).getOperId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 * 撤销需要还原库存 团购撤销需要未开团状态，
	 */
	@Override
	public String cancelOrder(String orderId) {

		Order_list orderList = (Order_list) commonDao
				.findByHql("from Order_list where orderId=? and validflag = 1", orderId).get(0);
		List<Order_detail> detailsList = detailService.getOrderdetailByMethod(2, null, orderId);
		Integer count = 0;
		for (Order_detail order_detail : detailsList) {
			count = (int) order_detail.getQuantity();
			if (orderList.getOrderType() == 1) {
				productService.updateStockNumber(order_detail.getProductId(), count);
			} else if (orderList.getOrderType() == 2) {
				groupBuyService.updateStockNumber(order_detail.getProductId(), count);
			}
		}
		try {
			boolean bool = commonDao.executeBySql("UPDATE tb_order_list SET STATUS = '02' WHERE orderId IN ('" + orderId
					+ "') and STATUS ='01' and validflag=1", null);
			if (bool) {
				return "success";
			}
			return "failed";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public String applyRefund(String orderId) {
		try {
			boolean bool = commonDao.executeBySql("UPDATE tb_order_list SET STATUS = '09' WHERE orderId IN ('" + orderId
					+ "') and STATUS ='08' and validflag=1", null);
			if (bool) {
				return "success";
			}
			return "failed";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public String orderPayment(String orderId, int orderType) {

		try {
			boolean bool = false;
			if (orderType == 1) {
				bool = commonDao.executeBySql("UPDATE tb_order_list SET STATUS = '03' WHERE orderId ='" + orderId
						+ "' and STATUS ='01' and validflag=1", null);
			} else {
				/* 如果开放立刻开团模式,状态需要改为05 */
				bool = commonDao.executeBySql("UPDATE tb_order_list SET STATUS = '04' WHERE orderId ='" + orderId
						+ "' and STATUS ='01' and validflag=1", null);
			}
			if (bool) {
				return "success";
			}
			return "failed";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public List<Order_list> getNotFinishedOrderList(String userId, PagePojo pagePojo) {

		String hql = "from Order_list where providerid='0001' and validflag=1 and status<>'08' and status<>'02' and userId=? order by createTime desc";

		List<Order_list> orderLists = commonDao.findPagingByHql(hql, pagePojo.getPageno(), pagePojo.getPagesize(),
				userId);
		List listCount = commonDao.findByHql("select count(*) " + hql, userId);
		Integer totalcount = Integer.parseInt(listCount.get(0) + "");
		pagePojo.setTotalcount(totalcount);

		for (Order_list order_list : orderLists) {

			User user = (User) userService.getUserByMethod(11, order_list.getUserId()).get(0);
			order_list.setUser(user);

			if (order_list.getOrderType() == 3) {
				List<Order_detail> detailsList = detailService.getOrderdetailByMethod(1, null, order_list.getOrderId());
				// .findByHql("from Order_detail where orderId=?", );
				for (Order_detail order_detail : detailsList) {
					Product_ext_bigDeal bigDeal = bigDealService
							.getbigDealProductByMethod(5, order_detail.getProductId(), "1").get(0);
					order_detail.setProduct(bigDeal.getProduct());
				}
				order_list.setOrder_detail(detailsList);
			} else if (order_list.getOrderType() == 2) {
				List<Order_detail> detailsList = detailService.getOrderdetailByMethod(1, null, order_list.getOrderId());
				// .findByHql("from Order_detail where orderId=?", );
				for (Order_detail order_detail : detailsList) {
					Product_ext_groupBuy bigDeal = groupBuyService
							.getgroupBuyProductByMethod(5, order_detail.getProductId()).get(0);
					order_detail.setProduct(bigDeal.getProduct());
				}
				order_list.setOrder_detail(detailsList);
			} else {
				order_list.setUserAddress(userAddressService
						.getUserAddressByMethod(3, order_list.getUserAddressId(), order_list.getUserId()).get(0));
				order_list.setPayment_Method(
						payment_MethodService.getPayMentByMethod(2, order_list.getPaymentway()).get(0));
			}

			// order_list.setExpress(expressService.getExpressByMethod(2,
			// order_list.getExpressvendor()).get(0));

		}

		return orderLists;

	}

	@Override
	public List<Order_list> getFinishedOrderList(String userId, PagePojo pagePojo) {

		String hql = "from Order_list where providerid='0001' and status='08' and status='02' and validflag=1  and userId=?";

		List<Order_list> orderLists = commonDao.findPagingByHql(hql, pagePojo.getPageno(), pagePojo.getPagesize(),
				userId);
		List listCount = commonDao.findByHql("select count(*) " + hql, userId);
		Integer totalcount = Integer.parseInt(listCount.get(0) + "");
		pagePojo.setTotalcount(totalcount);

		for (Order_list order_list : orderLists) {

			User user = (User) userService.getUserByMethod(11, order_list.getUserId()).get(0);
			order_list.setUser(user);

			if (order_list.getOrderType() == 3) {
				List<Order_detail> detailsList = detailService.getOrderdetailByMethod(1, null, order_list.getOrderId());
				// .findByHql("from Order_detail where orderId=?", );
				for (Order_detail order_detail : detailsList) {
					Product_ext_bigDeal bigDeal = bigDealService
							.getbigDealProductByMethod(5, order_detail.getProductId(), "1").get(0);
					order_detail.setProduct(bigDeal.getProduct());
				}
				order_list.setOrder_detail(detailsList);
			} else if (order_list.getOrderType() == 2) {
				List<Order_detail> detailsList = detailService.getOrderdetailByMethod(1, null, order_list.getOrderId());
				// .findByHql("from Order_detail where orderId=?", );
				for (Order_detail order_detail : detailsList) {
					Product_ext_groupBuy bigDeal = groupBuyService
							.getgroupBuyProductByMethod(5, order_detail.getProductId()).get(0);
					order_detail.setProduct(bigDeal.getProduct());
				}
				order_list.setOrder_detail(detailsList);
			} else {
				order_list.setUserAddress(userAddressService
						.getUserAddressByMethod(3, order_list.getUserAddressId(), order_list.getUserId()).get(0));
				order_list.setPayment_Method(
						payment_MethodService.getPayMentByMethod(2, order_list.getPaymentway()).get(0));
			}
			// order_list.setExpress(expressService.getExpressByMethod(2,
			// order_list.getExpressvendor()).get(0));
		}

		return orderLists;
	}

	@Override
	public List<Order_list> getAllOrderList(String userId, PagePojo pagePojo) {

		String hql = "from Order_list where providerid='0001' and validflag=1  and userId=? order by createTime desc";

		List<Order_list> orderLists = commonDao.findPagingByHql(hql, pagePojo.getPageno(), pagePojo.getPagesize(),
				userId);
		List listCount = commonDao.findByHql("select count(*) " + hql, userId);
		Integer totalcount = Integer.parseInt(listCount.get(0) + "");
		pagePojo.setTotalcount(totalcount);

		for (Order_list order_list : orderLists) {

			User user = (User) userService.getUserByMethod(11, order_list.getUserId()).get(0);
			order_list.setUser(user);

			if (order_list.getOrderType() == 3) {
				List<Order_detail> detailsList = detailService.getOrderdetailByMethod(1, null, order_list.getOrderId());
				// .findByHql("from Order_detail where orderId=?", );
				for (Order_detail order_detail : detailsList) {
					Product_ext_bigDeal bigDeal = bigDealService
							.getbigDealProductByMethod(5, order_detail.getProductId(), "1").get(0);
					order_detail.setProduct(bigDeal.getProduct());
				}
				order_list.setOrder_detail(detailsList);
			} else if (order_list.getOrderType() == 2) {
				List<Order_detail> detailsList = detailService.getOrderdetailByMethod(1, null, order_list.getOrderId());
				// .findByHql("from Order_detail where orderId=?", );
				for (Order_detail order_detail : detailsList) {
					Product_ext_groupBuy bigDeal = groupBuyService
							.getgroupBuyProductByMethod(5, order_detail.getProductId()).get(0);
					order_detail.setProduct(bigDeal.getProduct());
				}
				order_list.setOrder_detail(detailsList);
			} else if (order_list.getOrderType() == 1||order_list.getOrderType() == 100) {
				List<Order_detail> detailsList = detailService.getOrderdetailByMethod(1, null, order_list.getOrderId());
				// .findByHql("from Order_detail where orderId=?", );
				for (Order_detail order_detail : detailsList) {
					Product_ext_shop product = ext_shopService.getShopProductByMethod(5, order_detail.getProductId())
							.get(0);
					order_detail.setProduct(product.getProduct());
				}
				order_list.setOrder_detail(detailsList);
				order_list.setProductReviews(productReviewService.getProductReviewByOrderId(order_list.getOrderId()));
				order_list.setUserAddress(userAddressService
						.getUserAddressByMethod(3, order_list.getUserAddressId(), order_list.getUserId()).get(0));
				order_list.setPayment_Method(
						payment_MethodService.getPayMentByMethod(2, order_list.getPaymentway()).get(0));
			}
			// order_list.setExpress(expressService.getExpressByMethod(2,
			// order_list.getExpressvendor()).get(0));
		}

		return orderLists;
	}

	@Override
	public List existsOrderListByProductId(String userId, String productId) {

		String sql = "select count(*) from tb_order_list orders,tb_order_detail detail where detail.orderId=orders.orderId and orders.userId=? and detail.productId=?";
		List list = commonDao.findBySql(sql, null, userId, productId);
		return list;
	}

	@Override
	public void addShopOrder(CartUtil cartUtil, String userId, String userAddressId, Integer payofflineflag,
			HttpServletRequest request) {

		Double totalPrice = 0.0;
		Integer totalProductCount = 0;
		if (cartUtil != null) {
			for (Iterator<?> iterator = cartUtil.getMap().keySet().iterator(); iterator.hasNext();) {
				String key = iterator.next() + "";
				Double subtotal = cartUtil.getMap().get(key).getSubtotal();
				Integer count = cartUtil.getMap().get(key).getCount();
				totalPrice += subtotal;
				totalProductCount += count;
			}
		}

		Order_list order_list = new Order_list();
		order_list.setStatus("01");
		order_list.setOrderType((short) 1);
		order_list.setUserId(userId);
		order_list.setUserAddressId(userAddressId);
		order_list.setProviderid("0001");
		order_list.setTotalamt(totalPrice);
		order_list.setOrderId(UtilDate.getNowDateNo_() + "0" + 1 + "0001" + (new Random().nextInt(899999) + 100000));
		order_list.setCreateTime(new Date());
		order_list.setRequireinvoice(Short.parseShort(request.getParameter("requireinvoice")));
		order_list.setInvoicetype(Short.parseShort(request.getParameter("invoicetype")));
		order_list.setInvoicetitle(request.getParameter("invoicetitle"));
		order_list.setInvoicecontent(request.getParameter("invoicecontent"));
		order_list.setPaymentway(request.getParameter("paymentway"));
		order_list.setInvoiceprinted((short) 0);
		order_list.setRemark(request.getParameter("remark"));
		order_list.setLastUpdateTime(new Date());
		order_list.setPayofflineflag(payofflineflag.shortValue());
		order_list.setValidflag((short) 1);
		listDao.insert(order_list);

	}
}
