package com.qushop.dict.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.dict.entity.Express_vendor;
import com.qushop.dict.service.ExpressService;


@Controller
@RequestMapping("/manage/express")
public class MExpressController {

	@Resource
	ExpressService expressService;
	
	@RequestMapping("getAllExpress.do")
	public String getAllExpress(String action,HttpServletRequest request){
		
		List<Express_vendor> expressList = expressService.getExpressByMethod(0);
		request.setAttribute("expressList", expressList);
		if("dialog".equals(action)){
			return "admin/lookDialog/lookExpress";
		}
		return "admin/vexpressList";
	}
	
	@RequestMapping("toEditExpress.do")
	public String toEditExpress(String action,String[] vendorid,HttpServletRequest request){
		
		if("add".equals(action)){
			request.setAttribute("action", "add");
		}
		else if("update".equals(action)){
			request.setAttribute("action", "update");
			List<Express_vendor> vendors = expressService.getExpressByMethod(1, vendorid[0]);
			if(vendors!=null && vendors.size()>0){
				request.setAttribute("express", vendors.get(0));
			}
		}

		return "admin/editvExpress";
	}
	
	@RequestMapping("saveOrUpdate.do")
	@ResponseBody
	public Object saveOrUpdate(String action,Express_vendor vendor,HttpServletRequest request){
		
		boolean bool = false;
		vendor.setValidflag((short)1);
		if("add".equals(action)){
			bool = expressService.addExpress(vendor);
		}
		else if("update".equals(action)){
			bool = expressService.updateExpress(vendor);
		}

		if(bool){
			return DwzUtil.opSuccess("操作成功", "express");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("deleteExpress.do")
	@ResponseBody
	public Object deleteExpress(String vendorid,HttpServletRequest request){

		boolean bool = false;
		
		bool = expressService.deleteExpress(vendorid);

		if(bool){
			return DwzUtil.opSuccess("操作成功", "express");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
	
}
