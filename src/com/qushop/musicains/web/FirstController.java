package com.qushop.musicains.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qushop.ad.entity.Ad_para;
import com.qushop.ad.service.Ad_paraService;
import com.qushop.prod.entity.ProductType;
import com.qushop.prod.entity.Product_ext_recommProd;
import com.qushop.prod.service.ProductTypeService;
import com.qushop.prod.service.Product_ext_recommProdService;

@Controller
@RequestMapping("/first.shtml")
public class FirstController {

	@Resource
	Product_ext_recommProdService product_ext_recommProdService;
	
	@Resource
	ProductTypeService productTypeService;
	
	@Resource
	Ad_paraService ad_paraService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(HttpServletRequest request,HttpServletResponse response){
		
		//推荐产品
		List<Product_ext_recommProd> recommendProdsList = product_ext_recommProdService.getRecommProductByMethod(0);
		//广告
		List<Ad_para> ad_parasList = ad_paraService.getAd_ParaByArea(0, "0101", 5);
		
		List<ProductType> productTypesList = new ArrayList<ProductType>();
		ServletContext application = request.getSession().getServletContext();
		Object obj = application.getAttribute("producttypelist");
		if(obj==null)
		{
			productTypesList = productTypeService.getProductType();
			application.setAttribute("productTypeList",productTypesList);
		}
		else{
			productTypesList = (List<ProductType>) obj;
		}
		
		request.setAttribute("recommendProdsList", recommendProdsList);
		request.setAttribute("ad_parasList", ad_parasList);
		
		return "/web/index";
	}
	
}
