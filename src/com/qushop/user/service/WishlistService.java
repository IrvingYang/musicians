package com.qushop.user.service;

import java.util.List;

import com.qushop.user.entity.Wishlist;

public interface WishlistService {

	public void addWishlist(Wishlist wishlist);
	
	public List<Wishlist> getWishList(String userId,Integer maxCount);
	
	public int getWishListCount(String userId);
	
	public boolean deleteWishList(String productId,String userId);
	
	public boolean deleteWishListByProduct(String productId);
	
	/**
	 * 
	 * @param type 1查询是否删除 2查询是否存在
	 * @param params
	 * @return
	 */
	public List<Wishlist> getWishListByMethod(int type,String...params);
	
	public void updateWishList(Wishlist wishlist);
	
	public boolean deleteWishListByUserIds(String userIds);
	
}
