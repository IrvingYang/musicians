package com.qushop.musicains.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.musicains.entity.Lease;
import com.qushop.musicains.service.LeaseDaoService;

@Controller
@RequestMapping("/manage/lease")
public class LeaseController {

	@Resource
	LeaseDaoService leaseService;

	@RequestMapping("business2.do")
	@ResponseBody
	public Object makeOrder(String leaseId) {
		Lease lease = leaseService.getLeaseByLeaseId(leaseId);

		String msg = leaseService.updateLease(lease);
		if ("success".equals(msg)) {
			return DwzUtil.opSuccess("操作成功", "");
		} else {
			return DwzUtil.opSuccess("操作失败：" + msg, "");
		}
	}

	@RequestMapping("business.do")
	@ResponseBody
	public Object business(String[] leaseId, String action) {

		if (leaseId == null || leaseId.length <= 0) {
			return DwzUtil.opFailed("数据出现问题,操作失败", "");
		}
		String opLeaseId = leaseId[0];
		Lease lease = leaseService.getLeaseByLeaseId(opLeaseId);
		/*
		 * 01：已下单 02：已付款 03：已发货 04：已收货 05：已完成
		 */
		String msg = "";
		if ("recevieGoods".equals(action)) {
			lease.setStatus("03");
		} else if ("remittance".equals(action)) {
			lease.setStatus("04");
		} else if ("finishBusiness".equals(action)) {
			lease.setStatus("05");
		} else {
			return DwzUtil.opFailed("操作失败", "");
		}
		msg = leaseService.updateLease(lease);
		if ("success".equals(msg)) {
			return DwzUtil.opSuccess("操作成功", "");
		} else {
			return DwzUtil.opSuccess("操作失败：" + msg, "");
		}
	}

	@RequestMapping("/getLeaseList.do")
	public String getLeaseList(HttpServletRequest request) {

		List list = leaseService.getLeaseList();
		request.setAttribute("leaseList", list);
		return "/admin/sLeaseList";
	}

	@RequestMapping("todelivery.do")
	public Object todelivery(String[] leaseId, HttpServletRequest request) {

		Lease leaseByLeaseId = leaseService.getLeaseByLeaseId(leaseId[0]);

		request.setAttribute("orderList", leaseByLeaseId.getLeaseOrderList());
		return "admin/orderbusiness/delivery";
	}

}
