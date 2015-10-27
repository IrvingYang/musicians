package com.qushop.ad.admin;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.ad.entity.Ad_para;
import com.qushop.ad.service.Ad_paraService;
import com.qushop.common.util.Constants;
import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.prod.entity.Product;
import com.qushop.prod.service.ProductService;


@Controller
@RequestMapping("/manage/ad")
public class MAdvertisingController {

	@Resource
	Ad_paraService adparaService;
	
	@Resource
	ProductService productService;
	
	@RequestMapping("getAllAdvertising.do")
	public String getAllAdvertising(HttpServletRequest request){
		List<Ad_para> parasList = adparaService.getAd_ParaByMethod(0);
		request.setAttribute("adlist",parasList);
		return "advertisingList";
	}
	
	@RequestMapping("toAddAdvertising.do")
	public String toAddAdvertising(HttpServletRequest request){
		request.setAttribute("action", "add");
		return "editAdvertising";
	}
	@RequestMapping("toEditAdvertising.do")
	public String toEditAdvertising(String[] adid,HttpServletRequest request){
		List<Ad_para> parasList = adparaService.getAd_ParaByMethod(1,adid[0]);
		if(parasList!=null && parasList.size()>0){
			request.setAttribute("advertising",parasList.get(0));
		}
		request.setAttribute("action", "update");
		return "editAdvertising";
	}
	
	@RequestMapping("updateAdvertisting.do")
	@ResponseBody
	public Object updateAdvertisting(Ad_para ad_para,HttpServletRequest request){
		
		boolean bool = false;
		String uploadtime = request.getParameter("uploadtimes");
		bool = adparaService.updateAdPara(ad_para, request);
		
		if(bool){
			return DwzUtil.opSuccess("操作成功", "ad");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	
	@RequestMapping("addAdvertising.do")
	@ResponseBody
	public Object addAdvertising(Ad_para ad_para,HttpServletRequest request){
	
		//页面代码
		String code1 = request.getParameter("code1");
		//页面位置代码
		String code2 = request.getParameter("code2");
		ad_para.setAdarea(code1+code2);
		
		if(Arrays.binarySearch(Constants.SERIALNUMBER_ARRAY, ad_para.getAdarea())<0){
			List list = adparaService.getAd_ParaByMethod(2, ad_para.getAdarea()+"");
			if(list.size()>0){
				return DwzUtil.opFailed("该广告位已被占用！", "");
			}
		}
		String adproid = request.getParameter("entity.productId");
		String adlink=request.getContextPath()+"/";
		List<Product> products = productService.getProductListByMethod(2, PublicUtil.getOper(request), adproid);
		
		switch (ad_para.getAdtype()) {
		case 1:
			String productTypeId = null;
			if(products!=null && products.size()>0){
				Product product = products.get(0);
				productTypeId = product.getProductTypeId();
				if(product.getProviderId().equals(Constants.MAIN_PROVIDERID))
				{
					adlink+="product_page.html?productId="+adproid+"&productTypeId="+productTypeId+"&requestType=page";
				}
				else
				{
					adlink+="provider_product_page.html?productId="+adproid+"&productTypeId="+productTypeId+"&requestType=page";
				}
			}
			break;
		case 2:
			break;
		case 3:
			adlink+="groupbuy_product_page.html?groupbuyid="+adproid;
			break;
		case 4:
			break;
		case 5:
			break;
		default:
			break;
		}
		boolean bool = false;
		ad_para.setAdlink(adlink);
		bool = adparaService.addAdPara(ad_para, request);
		
		if(bool){
			return DwzUtil.opSuccess("操作成功", "ad");
		}
		return DwzUtil.opFailed("该广告位所含滚动广告条目数已达上限", "");
	}
	
	@RequestMapping("deleteAdvertising.do")
	@ResponseBody
	public Object deleteAdvertising(String adid,HttpServletRequest request){
		
		boolean bool = false;
		
		bool = adparaService.deleteAdPara(adid, request);
		
		if(bool){
			return DwzUtil.opSuccess("操作成功", "ad");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
}

