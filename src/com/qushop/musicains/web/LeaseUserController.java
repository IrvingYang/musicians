package com.qushop.musicains.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.Constants;
import com.qushop.musicains.entity.Lease;
import com.qushop.musicains.entity.LeaseConfig;
import com.qushop.musicains.service.LeaseConfigService;
import com.qushop.musicains.service.LeaseDaoService;
import com.qushop.musicains.service.business.LeaseBusinessService;
import com.qushop.order.entity.Order_list;
import com.qushop.order.service.Order_listService;
import com.qushop.prod.entity.Product_ext_shop;
import com.qushop.prod.service.Product_ext_shopService;
import com.qushop.user.entity.User;
import com.qushop.user.entity.User_Ext_Personal;
import com.qushop.user.service.UserAddressService;
import com.qushop.user.service.UserService;

@Controller
@RequestMapping("/web/lease")
public class LeaseUserController {

	@Resource
	UserService userService;

	@Resource
	LeaseDaoService leaseDaoService;

	@Resource
	LeaseConfigService leaseConfigService;

	@Resource
	LeaseBusinessService leaseBusinessService;

	@Resource
	UserAddressService userAddressService;

	@Resource
	Product_ext_shopService product_ext_shopService;

	@Resource
	Order_listService order_listService;

	/*
	 * not used
	 */
	@RequestMapping(value = "makeALease.do", method = RequestMethod.POST)
	@ResponseBody
	@Deprecated
	public Lease makeALease(HttpServletRequest request, String productId, String productTypeId, int count,
			int leaseType, int leaseCycle, String addressId) {
		User_Ext_Personal user_Ext_Personal = (User_Ext_Personal) request.getSession()
				.getAttribute(Constants.LOGIN_USER);
		User user = user_Ext_Personal.getUser();

		Lease lease = new Lease();
		lease.setProductId(productId);
		// lease.setProductTypeId(productTypeId);
		// lease.setCount(count);;
		lease.setLeaseType(leaseType);
		lease.setLeaseCycle(leaseCycle);
		lease.setUserId(user.getUserId());

		lease.setUser(user);

		// String userAddressAsString =
		// leaseBusinessService.assembleAddressFromUserAddress(user.getUserId(),lease.getAddressId());

		// lease.setAddress(userAddressAsString);

		List<Product_ext_shop> ext_shopsList = product_ext_shopService.getShopProductByMethod(5, lease.getProductId());

		if (ext_shopsList != null && ext_shopsList.size() > 0)

		{
			Product_ext_shop ext_shop = ext_shopsList.get(0);
			lease.setExt_shop(ext_shop);

			// Double totalamt =
			// leaseBusinessService.calculateTotalRentPrice(lease.getProductId(),
			// lease.getCount(),
			// lease.getLeaseCycle(),ext_shop.getOriginalPrice() );
			//
			// lease.setTotalamt(totalamt);
		}

		int productCount = count;
		String productId1 = lease.getProductId();

		int lease_period = leaseCycle;
		String requireInvoice = request.getParameter("requireinvoice");

//		Order_list addedLeaseOrder = order_listService.addLeaseOrder((short) 100, user.getUserId(), addressId, 0,
//				productId1, productCount, lease_period, (short) 1, "", "01");
//
//		lease.setLeaseOrderDetail(addedLeaseOrder.getOrder_detail().get(0));
//
//		lease.setOrderId(addedLeaseOrder.getOrderId());

		leaseDaoService.saveLease(lease);

		return lease;
	}

	@RequestMapping(value = "payALease.action", method = RequestMethod.POST)
	public void payALease(HttpServletRequest request, Lease lease) {
		leaseDaoService.updateLease(lease);
	}

	@RequestMapping(value = "leaseList.action")
	public Object myLeases(HttpServletRequest request) {
		String userId;
		Object obj = request.getSession().getAttribute(Constants.LOGIN_ENTER_UER);
		if (obj == null) {
			obj = request.getSession().getAttribute(Constants.LOGIN_USER);
			if (obj == null) {
				return "needlogin";
			} else {
				User_Ext_Personal user_Ext_Personal = (User_Ext_Personal) obj;
				userId = user_Ext_Personal.getUserId();
			}
		} else {
			return "entUser";
		}

		List<Lease> leaseListByUserId = leaseDaoService.getLeaseListByUserId(userId);
		return leaseListByUserId;
	}

	@RequestMapping("deleteALease.action")
	public void deleteALease(HttpServletRequest request, String leaseId) {
	}

	@RequestMapping("getLeasePrice.action")
	@ResponseBody
	public Object getLeasePrice(HttpServletRequest request, String productTypeId) {
		List<LeaseConfig> leaseList = leaseConfigService.getLeaseConfigList(productTypeId);
		return leaseList;
	}

}