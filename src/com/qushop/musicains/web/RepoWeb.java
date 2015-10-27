package com.qushop.musicains.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.Constants;
import com.qushop.common.util.PublicUtil;
import com.qushop.common.util.UtilDate;
import com.qushop.musicains.entity.Discountconfig;
import com.qushop.musicains.entity.PDiscountconfig;
import com.qushop.musicains.entity.Repo;
import com.qushop.musicains.service.DiscountconfigService;
import com.qushop.musicains.service.RepoDaoService;
import com.qushop.order.entity.Order_list;
import com.qushop.order.service.Order_listService;
import com.qushop.prod.entity.Product_ext_shop;
import com.qushop.prod.service.Product_ext_shopService;


@Controller
@RequestMapping("/web/repo")
public class RepoWeb {

	@Resource
	RepoDaoService repoService;
	
	@Resource
	Product_ext_shopService ext_shopService;
	
	@Resource
	DiscountconfigService discountconfigService;
	
	@Resource
	Order_listService order_listService;
	
	
	@RequestMapping("addRepo.action")
	public String addRepo(String productId,Double price,Short repoType,
			String telephone,String email,String bankcard,String cardname,
			HttpServletRequest request){
		
		List<Product_ext_shop> productsList = ext_shopService.getShopProductByMethod(5, productId);
		Product_ext_shop ext_shop = null;
//		Double price = null;
		if(productsList==null || productsList.size()<=0)
		{
//			ext_shop = productsList.get(0);
//			if(ext_shop.getPromoteflag()==1){
//				price = ext_shop.getPromotePrice();
//			}
//			else{
//				price = ext_shop.getOriginalPrice();
//			}
			return "redirect:/";
		}
		Repo repo = new Repo();
		repo.setUserId(PublicUtil.getUserOfSession(request).getUserId());
		repo.setRepoType(repoType);
		repo.setStatus("01");
		repo.setCreateTime(new Date());
		repo.setProductId(productId);
		repo.setRepoId(UtilDate.getNowDateTimeNo_());
		repo.setBankcard(bankcard);
		repo.setCardname(cardname);
		repo.setTelephone(telephone);
		repo.setProviderId("0001");
		repo.setTotalamt(price);
		repo.setValidflag((short)1);
		repoService.saveRepo(repo);
		
		return "web/repoOk";
	}
	
	@RequestMapping("repoStart.shtml")
	public String toRepoStart(String productId,HttpServletRequest request){
		
		Object obj = request.getSession().getAttribute(Constants.LOGIN_USER);
		if(obj==null){
			request.setAttribute("returnURL", request.getRequestURI());
			return "web/signup";
		}
		return "web/repoStart";
	}
	@RequestMapping("otherRepo.shtml")
	public String toOtherRepo(HttpServletRequest request)
	{
		return "web/otherRepo";
	}

	@RequestMapping("getDisCount.action")
	@ResponseBody
	public Object getDisCount(String productId,HttpServletRequest request){
		
		List<Product_ext_shop> productsList = ext_shopService.getShopProductByMethod(5, productId);
		Product_ext_shop ext_shop = null;
		if(productsList!=null && productsList.size()>0)
		{
			ext_shop = productsList.get(0);
			List<PDiscountconfig> pdiscountconfigs = discountconfigService.getDiscountConfigProductId(productId);
			if(pdiscountconfigs==null || pdiscountconfigs.size()<=0)
			{
				List<Discountconfig> discountconfigs = discountconfigService.getDiscountConfigByProductTypeId(ext_shop.getProduct().getProductTypeId());
				return discountconfigs;
			}
			else
			{
				return pdiscountconfigs;
			}
		}
		return "error";
	}
	
	@RequestMapping("repoType.action")
	public String repoType(String repotype,String productId,HttpServletRequest request)
	{
		String percent= request.getParameter("percent");
		List<Product_ext_shop> productsList = ext_shopService.getShopProductByMethod(5, productId);
		Product_ext_shop ext_shop = null;
		Double price = null;
		if(productsList!=null && productsList.size()>0)
		{
			ext_shop = productsList.get(0);
			if(ext_shop.getPromoteflag()==1){
				price = ext_shop.getPromotePrice();
			}
			else{
				price = ext_shop.getOriginalPrice();
			}
		}
		else{
			return "redirect:/";
		}
		request.setAttribute("shop", ext_shop);
		request.setAttribute("percent", percent);
		request.setAttribute("productId", productId);
		request.setAttribute("price", (price*Double.parseDouble(percent)/100));
		request.setAttribute("sendaddr", Constants.SENDADDR);
		request.setAttribute("senduser", Constants.SENDUSER);
		request.setAttribute("senduserphone", Constants.SENDUSERPHONE);
		request.setAttribute("postcode", Constants.POSTCODE);
		if("discount".equals(repotype)){
			
			return "web/repoDiscount";
		}
		return "web/repoDepreciation";
	}
	
	@RequestMapping("validateProduct.shtml")
	@ResponseBody
	public boolean validateProduct(String productId,HttpServletRequest request)
	{
		List<Order_list> order_lists = order_listService.existsOrderListByProductId(PublicUtil.getUserOfSession(request).getUserId(), productId);
		
		Integer count = Integer.parseInt(order_lists.get(0)+"");
			
		return count>0?true:false;
	}
	
}
