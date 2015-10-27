package com.qushop.dict.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.dict.entity.Brand_vendor;
import com.qushop.dict.service.Brand_vendorService;
import com.qushop.prod.service.ProductService;



@RequestMapping("/manage/brand_vendor")
@Controller
public class MBrand_vendorController {

	@Resource
	Brand_vendorService brand_vendorService;	
	
	@Resource
	ProductService productService;
	
	
	@RequestMapping("getAllbrand_vendor.do")
	public String getAllbrand_vendor(String action,HttpServletRequest request){
		
		List<Brand_vendor> brand_vendorsList = (List<Brand_vendor>) brand_vendorService.getVendorByMethod(1);
		request.setAttribute("vendorsList", brand_vendorsList);
		if("dialog".equals(action)){
			return "admin/lookDialog/lookVendor";
		}
		return "admin/vbrandList";
	}
	
	@RequestMapping("toEditBrand.do")
	public String toEditBrand(String action,String[] brandid,HttpServletRequest request){
		
		if("add".equals(action)){
			request.setAttribute("action", "add");
		}
		else if("update".equals(action)){
			request.setAttribute("action", "update");
			List<Brand_vendor> vendorsList = brand_vendorService.getVendorByMethod(3, brandid[0]);
			if(vendorsList.size()>0){
				request.setAttribute("brand", vendorsList.get(0));
			}
		}

		return "admin/editvbrand";
	}
	
	@RequestMapping("saveOrUpdate.do")
	@ResponseBody
	public Object saveOrUpdate(String action,Brand_vendor brand,HttpServletRequest request){
		
		String imagepath = request.getParameter("image1b");
		boolean bool = false;
		brand.setValidflag((short)1);
		brand.setImagepath(imagepath);
		if("add".equals(action)){
			bool = brand_vendorService.addBrand(brand);
		}
		else if("update".equals(action)){
			bool = brand_vendorService.updateBrand(brand);
		}

		if(bool){
			return DwzUtil.opSuccess("操作成功", "brand");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("deleteBrand.do")
	@ResponseBody
	public Object deleteBrand(String brandid,HttpServletRequest request){

		boolean bool = false;
		if(productService.getProductCountByBrandVendor(brandid)>0){
			return DwzUtil.opFailed("该品牌下含有商品，不可删除", "");
		}
		bool = brand_vendorService.deleteBrand(brandid);

		if(bool){
			return DwzUtil.opSuccess("操作成功", "brand");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
}
