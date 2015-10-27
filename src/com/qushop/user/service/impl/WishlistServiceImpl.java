package com.qushop.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.prod.entity.Product;
import com.qushop.prod.entity.ProductImg;
import com.qushop.prod.service.ProductImgService;
import com.qushop.prod.service.ProductService;
import com.qushop.prod.service.Product_ext_shopService;
import com.qushop.user.entity.Wishlist;
import com.qushop.user.service.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Resource
	CommonDao<Wishlist> commonDao;
	
	@Resource
	CommonDao baseDao;
	
	@Resource
	ProductService productService;
	
	@Resource
	ProductImgService productImgService;
	
	@Resource
	Product_ext_shopService ext_shopService;
	
	@Override
	public void addWishlist(Wishlist wishlist) {
		commonDao.insert(wishlist);
	}
	@Override
	public List<Wishlist> getWishList(String userId,Integer maxCount) {
		List<Wishlist> wishlistsList = null;
		if(maxCount==0)
			wishlistsList = commonDao.findByHql("from Wishlist where userId=? and validflag = 1", userId);
		else
			wishlistsList = commonDao.findPagingByHql("from Wishlist where userId=? and validflag = 1 ORDER BY RAND()", 0, maxCount,userId);
		
		for (Wishlist wishlist : wishlistsList) {
			
			if(wishlist.getPartnerflag()==0)
			{
				wishlist.setProduct_ext_shop(ext_shopService.getShopProductByMethod(5,wishlist.getProductId()).get(0));
			}
			else
			{
				Product product = productService.getProductListByMethod(2, null,  wishlist.getProductId()).get(0);
				List<ProductImg> productimglist = productImgService.getProductImgByMethod(1, product.getProductId(),"4");
				product.setProductimglist(productimglist);
				wishlist.setProduct(product);
			}
		}
		
		return wishlistsList;
	}
	@Override
	public int getWishListCount(String userId) {
		
		int count = Integer.parseInt(baseDao.findByHql("select count(*) from Wishlist where userId=? and validflag = 1", userId).get(0)+"");
		return count;
	}
	@Override
	public boolean deleteWishList(String productId,String userId) {
		
		try {
			return baseDao.executeBySql("update tb_wishlist set validflag=0 where userId=? and productId=? ",userId, productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean deleteWishListByProduct(String productId) {
		
		try {
			return baseDao.executeBySql("update tb_wishlist set validflag=0 where productId in ("+productId+") ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<Wishlist> getWishListByMethod(int type, String... params) {

		List<Wishlist> wishlistsList = null;
		switch (type) {
		case 1:
			wishlistsList = baseDao.findByHql("from Wishlist where productId=? and userId=? and validflag = 0", params);
			break;
		case 2:
			wishlistsList = baseDao.findByHql("from Wishlist where productId=? and userId=? and validflag = 1", params);
			break;
		default:
			break;
		}
		return wishlistsList;
	}
	@Override
	public void updateWishList(Wishlist wishlist) {
		baseDao.update(wishlist);
	}
	@Override
	public boolean deleteWishListByUserIds(String userIds) {
		return baseDao.executeBySql("update tb_wishlist set validflag=0 where userId in ("+userIds+")");
	}
}
