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
import com.qushop.prod.service.Product_ext_newService;
import com.qushop.prod.service.Product_ext_recommProdService;
import com.qushop.prod.service.Product_ext_shopService;


@Controller
@RequestMapping("/manage/productshop")
public class MProductShopController {
	
	@Resource
	Product_ext_shopService ext_shopService;
	
	@Resource
	Product_ext_newService newService;
	
	@Resource
	Product_ext_recommProdService recommProdService;
	
	@RequestMapping("queryAllProductShop.do")
	public String queryAllProductShop(String action,Integer typeId,String type,HttpServletRequest request){
		
		List<Product_ext_shop> product_ext_shopsList =  ext_shopService.getShopProductByMethod(typeId,type);
		request.setAttribute("productShop", product_ext_shopsList);
		if("dialog".equals(action)){
			return "admin/lookDialog/lookProductShop";
		}
		return "admin/product_ext_shopsList";
		
	}
	
	@RequestMapping("getAllCanAddRelactionProduct.do")
	public String getAllCanAddRelactionProduct(String action,String productId,HttpServletRequest request){
		List<Product_ext_shop> product_ext_shopsList = null;
		if(productId!=null && !productId.equals("")){
			product_ext_shopsList = ext_shopService.getShopProductByMethod(2,productId);
		}else{
			product_ext_shopsList = ext_shopService.getShopProductByMethod(2);
		}
		request.setAttribute("productShop", product_ext_shopsList);
		if("relation".equals(action)){
			return "admin/lookDialog/lookCanAddRlation";
		}
		return "admin/lookDialog/lookProductShop";
	}
	
	@RequestMapping("deleteProductShop.do")
	@ResponseBody
	public Object deleteProductShop(String productId,HttpServletRequest request){
		
		List newList = newService.getNewProductByMethod(4, productId);
		if(newList!=null && newList.size()>0){
			return DwzUtil.opFailed("选中商城商品含新到商品不能删除", "");
		}
		
		List recommendList = recommProdService.getRecommProductByMethod(5, productId);
		if(recommendList!=null && recommendList.size()>0){
			return DwzUtil.opFailed("选中商城商品含推荐商品不能删除", "");
		}
		
		boolean bool = ext_shopService.deleteProductShop(productId,PublicUtil.getOper(request).getOperId());
		if(bool){
			return DwzUtil.opSuccess("操作成功", "productShop");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	
	@RequestMapping("toAddProductShop.do")
	public String toAddProductShop(HttpServletRequest request){
		request.setAttribute("action", "add");
		return "admin/editProductShop";
	}
	
	@RequestMapping("toEditProductShop.do")
	public String toEditProductShop(String productId,HttpServletRequest request){
		productId = productId.split(",")[0];
		Product_ext_shop product_ext_shop = ext_shopService.getShopProductByMethod(6,productId).get(0);
		request.setAttribute("shop",product_ext_shop);
		request.setAttribute("action","update");
		return "admin/editProductShop";
	}
	
	@RequestMapping("getProductShopInId.do")
	@ResponseBody
	public Object getProductShopInId(String productId,HttpServletRequest request){
		
		List<Product_ext_shop> product_ext_shopsList = ext_shopService.getShopProductByMethod(1, productId);
		return product_ext_shopsList;
	}
	

	@RequestMapping("saveProductShop.do")
	@ResponseBody
	public Object saveProductShop(Product_ext_shop shop,HttpServletRequest request) throws ParseException{
		
		String unit = request.getParameter("orgLookup.unit");
		String productId = request.getParameter("orgLookup.productId");
		List<Product_ext_shop> product_ext_shopsList = null;
		product_ext_shopsList = ext_shopService.getShopProductByMethod(6, productId);

		boolean bool = false;
		if(product_ext_shopsList!=null && product_ext_shopsList.size()>0){

			Product_ext_shop ext_shop = product_ext_shopsList.get(0);
			if(ext_shop.getValidflag()==(short)1){
				return DwzUtil.opFailed("商城中已经含有该商品，不能重复添加", "");
			}
			else{
				ext_shop.setOperid(PublicUtil.getOper(request).getOperId());
				ext_shop.setValidflag((short) 1);
				ext_shop.setPromoteflag((short)0);
				ext_shop.setLastUpdateTime(new Date());
				ext_shop.setOnTime(new Date());
				ext_shop.setSalesarea(shop.getSalesarea());
				ext_shop.setOriginalPrice(shop.getOriginalPrice());
				ext_shop.setUnit(unit);
				bool = ext_shopService.updateProductShop(ext_shop);
			}
		}
		else{
			shop.setProductId(productId);
			shop.setOperid(PublicUtil.getOper(request).getOperId());
			shop.setValidflag((short) 1);
			shop.setPromoteflag((short)0);
			shop.setLastUpdateTime(new Date());
			shop.setOnTime(new Date());
			shop.setUnit(unit);
			bool = ext_shopService.addProductShop(shop);
		}

		if(bool){
			return DwzUtil.opSuccess("操作成功", "productShop");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
	

	@RequestMapping("updateProductShop.do")
	@ResponseBody
	public Object updateProductShop(Product_ext_shop shop,HttpServletRequest request) throws ParseException{

		String ontimes = request.getParameter("ontimes");
		String promoteStartTimes = request.getParameter("promoteStartTimes");
		String promoteEndTimes = request.getParameter("promoteEndTimes");
		String unit = request.getParameter("orgLookup.unit");
		String productId = request.getParameter("orgLookup.productId");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		shop.setUnit(unit);
		if(promoteStartTimes!=null && !promoteStartTimes.equals(""))
		{
			shop.setPromoteStartTime(format.parse(promoteStartTimes));
		}
		if(promoteEndTimes!=null && !promoteEndTimes.equals(""))
		{
			shop.setPromoteEndTime(format.parse(promoteEndTimes));
		}
		shop.setProductId(productId);
		shop.setOperid(PublicUtil.getOper(request).getOperId());
		shop.setValidflag((short) 1);
		shop.setLastUpdateTime(new Date());
		shop.setOnTime(format.parse(ontimes));
		boolean bool = ext_shopService.updateProductShop(shop);
		
		if(bool){
			return DwzUtil.opSuccess("操作成功", "productShop");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
}











