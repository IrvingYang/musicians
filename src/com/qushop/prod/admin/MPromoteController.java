package com.qushop.prod.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.prod.entity.Product_ext_shop;
import com.qushop.prod.service.Product_ext_shopService;



@Controller
@RequestMapping("/manage/promote")
public class MPromoteController {

	@Resource
	Product_ext_shopService ext_shopService;
	
	@RequestMapping("getAllPromoteProduct.do")
	public String getAllPromoteProduct(HttpServletRequest request){
		
		List<Product_ext_shop> ext_shopsList = ext_shopService.getShopProductByMethod(4);
		request.setAttribute("promoteList", ext_shopsList);
		return "admin/promoteProductList";
	}
	
	@RequestMapping("toAddpromoteProduct.do")
	public String toAddpromoteProduct(HttpServletRequest request){
		request.setAttribute("action", "add");
		return "admin/editPromoteProduct";
	}
	
	@RequestMapping("savePromoteProduct.do")
	@ResponseBody
	public Object savePromoteProduct(HttpServletRequest request) throws ParseException{
		String productId = request.getParameter("productPromote.productId");
		String promotePrice = request.getParameter("promotePrice");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date begin = null;
		Date end = null;
		if(beginTime!=null && !beginTime.equals("")){
			begin = format.parse(beginTime);
		}
		if(endTime!=null && !endTime.equals("")){
			end = format.parse(endTime);
		}
		List<Product_ext_shop> product_ext_shopsList = ext_shopService.getShopProductByMethod(5,productId);
		Product_ext_shop product_ext_shop = null;
		if(product_ext_shopsList!=null && product_ext_shopsList.size()>0){
			product_ext_shop = product_ext_shopsList.get(0);
		}
		product_ext_shop.setPromotePrice(Double.parseDouble(promotePrice));
		product_ext_shop.setPromoteStartTime(begin);
		product_ext_shop.setPromoteEndTime(end);
		product_ext_shop.setPromoteflag((short)1);
		product_ext_shop.setLastUpdateTime(new Date());
		boolean bool = ext_shopService.updateProductShop(product_ext_shop);
		if(bool){
			return DwzUtil.opSuccess("操作成功","promote");
		}
		return DwzUtil.opFailed("操作失败","");
	}
	
	@RequestMapping("updatePromoteProduct.do")
	@ResponseBody
	public Object updatePromoteProduct(HttpServletRequest request) throws ParseException{
		
		String productId = request.getParameter("productPromote.productId");
		String promotePrice = request.getParameter("promotePrice");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date begin = null;
		Date end = null;
		if(beginTime!=null && !beginTime.equals("")){
			begin = format.parse(beginTime);
		}
		if(endTime!=null && !endTime.equals("")){
			end = format.parse(endTime);
		}
		Product_ext_shop product_ext_shop = ext_shopService.getShopProductByMethod(5,productId).get(0);
		product_ext_shop.setPromotePrice(Double.parseDouble(promotePrice));
		product_ext_shop.setPromoteStartTime(begin);
		product_ext_shop.setPromoteEndTime(end);
		product_ext_shop.setPromoteflag((short)1);
		product_ext_shop.setLastUpdateTime(new Date());
		boolean bool = ext_shopService.updateProductShop(product_ext_shop);
		if(bool){
			return DwzUtil.opSuccess("操作成功","promote");
		}
		return DwzUtil.opFailed("操作失败","");
		
	}
	
	@RequestMapping("deletePromoteProduct.do")
	@ResponseBody
	public Object deletePromoteProduct(String productId,HttpServletRequest request){
		
		boolean bool = ext_shopService.deletePromoteProduct(productId,PublicUtil.getOper(request).getOperId());
		if(bool){
			return DwzUtil.opSuccess("操作成功","promote");
		}
		return DwzUtil.opFailed("操作失败","");
	}
	
	@RequestMapping("toEditPromoteProduct.do")
	public Object toEditPromoteProduct(String productId,HttpServletRequest request){
		
		Product_ext_shop product_ext_shop = ext_shopService.getShopProductByMethod(5, productId).get(0);
		request.setAttribute("shop", product_ext_shop);
		request.setAttribute("action", "update");
		return "admin/editPromoteProduct";
	}
	
}
