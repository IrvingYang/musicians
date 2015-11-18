package com.qushop.musicains.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qushop.common.util.DwzUtil;
import com.qushop.musicains.entity.LeaseConfig;
import com.qushop.musicains.service.LeaseConfigService;
import com.qushop.prod.entity.ProductType;
import com.qushop.prod.service.ProductTypeService;

@Controller
@RequestMapping("manage/leaseConfig")
public class LeaseConfigController {

	@Resource
	LeaseConfigService leaseConfigService;
	
	@Resource
	ProductTypeService productTypeService;

	@RequestMapping("getAllLeaseConfig.do")
	public String getAllLeaseConfig(HttpServletRequest request) {
		List<LeaseConfig> allLeaseConfigList = leaseConfigService.getAllLeaseConfigList();
		request.setAttribute("leaseConfigList", allLeaseConfigList);
		return "admin/leaseConfigList";
	}

	@RequestMapping("toAdd.do")
	public String toAdd(HttpServletRequest request) {
		request.setAttribute("action", "add");
		return "admin/editLeaseConfig";
	}

	@RequestMapping("toUpdate.do")
	public String toUpdate(HttpServletRequest request, String lcId) {

		LeaseConfig leaseConfig = leaseConfigService.getLeaseConfig(lcId);
		request.setAttribute("action", "update");
		request.setAttribute("leaseConfig", leaseConfig);
		return "admin/editLeaseConfig";
	}

	@RequestMapping("add.do")
	@ResponseBody
	public Object add(Integer day, Double money, Double depositPercent, ProductType productType,
			HttpServletRequest request) {
		String productTypeId = request.getParameter("productType.productTypeId");
		LeaseConfig leaseConfig = new LeaseConfig();
		leaseConfig.setDay(day);
		leaseConfig.setMoney(money);
		leaseConfig.setProductTypeId(productTypeId);
		leaseConfig.setLastUpdateTime(new Date());
		leaseConfig.setValidflag((short) 1);
		leaseConfig.setDepositPercent(depositPercent * .01);
		String result = leaseConfigService.saveLeaseConfig(leaseConfig);
		if (result.equals("success")) {
			return DwzUtil.opSuccess("操作成功", "productTypeId");
		}
		return DwzUtil.opFailed("操作失败", "");
	}

	@RequestMapping("update.do")
	@ResponseBody
	public Object update(Integer day, Double money, Double depositPercent, String lcId, HttpServletRequest request) {
		String productTypeId = request.getParameter("productType.productTypeId");
		LeaseConfig leaseConfig = leaseConfigService.getLeaseConfig(lcId);
		leaseConfig.setDay(day);
		leaseConfig.setMoney(money);
		leaseConfig.setProductTypeId(productTypeId);
		leaseConfig.setLastUpdateTime(new Date());
		leaseConfig.setDepositPercent(depositPercent * .01);
		String result = leaseConfigService.updateLeaseConfig(leaseConfig);
		if (result.equals("success")) {
			return DwzUtil.opSuccess("操作成功", "productTypeId");
		}
		return DwzUtil.opFailed("操作失败", "");
	}

	@RequestMapping("getAllProductType.do")
	public ModelAndView getAllProductType(HttpServletRequest request) {
		List<ProductType> productTypeList = productTypeService.getProductTypeByMethod(7);
		ModelAndView mv = new ModelAndView("admin/lookDialog/lookProductTypeForRent");
		mv.addObject("productTypes", productTypeList);
		return mv;

	}

}
