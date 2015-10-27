package com.qushop.user.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.PublicUtil;
import com.qushop.user.entity.User_Ext_Personal;
import com.qushop.user.entity.Wishlist;
import com.qushop.user.service.WishlistService;


@Controller
@RequestMapping("/user/wishlist")
public class WishlistController {

	@Resource
	WishlistService wishlistService;
	
	@RequestMapping(value="addToWishList.action",method=RequestMethod.POST)
	@ResponseBody
	public Object addToWishList(String productId,Integer partnerflag,HttpServletRequest request){
		
		if(PublicUtil.entUserLoginState(request)){
			return "entUser";
		}
		if(!PublicUtil.userLoginState(request)){
			return "needLogin";
		}else{
			try {
				User_Ext_Personal user_Ext_Personal = PublicUtil.getUserOfSession(request);
				List<Wishlist> list = wishlistService.getWishListByMethod(2, productId,user_Ext_Personal.getUserId());
				if(list.size()>0){
					return "already";
				}
				else{

					list = wishlistService.getWishListByMethod(1, productId,user_Ext_Personal.getUserId());
					Wishlist wishlist = null;
					if(list.size()>0){
						wishlist = list.get(0);
						wishlist.setValidflag((short)1);
						wishlist.setPartnerflag(partnerflag.intValue());
						wishlistService.updateWishList(wishlist);
					}else{
						wishlist = new Wishlist();
						wishlist.setProductId(productId);
						wishlist.setUserId(user_Ext_Personal.getUserId());
						wishlist.setValidflag((short)1);
						wishlist.setPartnerflag(partnerflag.intValue());
						wishlistService.addWishlist(wishlist);
					}
				}
				
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
		}
	}
	
	@RequestMapping("getWishList.action")
	@ResponseBody
	public Object getWishList(Integer maxCount,HttpServletRequest request){
		
		if(PublicUtil.entUserLoginState(request)){
			return "entUser";
		}
		if(!PublicUtil.userLoginState(request)){
			return "needLogin";
		}
		User_Ext_Personal user_Ext_Personal = PublicUtil.getUserOfSession(request);
		List<Wishlist> wishlistsList = wishlistService.getWishList(user_Ext_Personal.getUserId(),maxCount==null?0:maxCount);
		
		return wishlistsList;
		
	}
	
	@ResponseBody
	@RequestMapping("getWishListCount.action")
	public Object getWishListCount(HttpServletRequest request){
		
		if(PublicUtil.entUserLoginState(request)){
			return "entUser";
		}
		if(!PublicUtil.userLoginState(request)){
			return "needLogin";
		}
		User_Ext_Personal user_Ext_Personal = PublicUtil.getUserOfSession(request);
		return wishlistService.getWishListCount(user_Ext_Personal.getUserId());
	}
	
	@ResponseBody
	@RequestMapping("deleteWishList.action")
	public Object deleteWishList(String productId,HttpServletRequest request){
		
		if(PublicUtil.entUserLoginState(request)){
			return "entUser";
		}
		if(!PublicUtil.userLoginState(request)){
			return "needLogin";
		}
		User_Ext_Personal user_Ext_Personal = PublicUtil.getUserOfSession(request);
		return wishlistService.deleteWishList(productId, user_Ext_Personal.getUserId());
	}
}
