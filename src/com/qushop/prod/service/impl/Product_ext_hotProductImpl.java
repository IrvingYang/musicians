package com.qushop.prod.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.prod.entity.Product_ext_hotProduct;
import com.qushop.prod.entity.Product_ext_shop;
import com.qushop.prod.service.ProductImgService;
import com.qushop.prod.service.Product_ext_hotProductService;
import com.qushop.prod.service.Product_ext_shopService;

@Service
public class Product_ext_hotProductImpl implements
		Product_ext_hotProductService {
	
	@Resource
	CommonDao commonDao;

	@Resource
	Product_ext_shopService shopService;
	
	@Resource
	ProductImgService productImgService;
	
	/**
	 * type:0 侧边栏热点商品信息查询 , 1查询热门信息个数
	 * 
	 */
	@Override
	public Map getHotProductByType(Integer type,String typeId,Integer maxCount) {
		
		Map map = new HashMap();
		switch (type) {
		case 0:
			List<Product_ext_hotProduct> hotlist = commonDao.findPagingByHql("from Product_ext_hotProduct where validflag=1",0,maxCount);
			for (Product_ext_hotProduct product_ext_hotProduct : hotlist) {
				Product_ext_shop product_ext_shop = shopService.getShopProductByMethod(8, product_ext_hotProduct.getProductId()).get(0);
				product_ext_shop.getProduct().setProductimglist(productImgService.getProductImgByMethod(1, product_ext_hotProduct.getProductId(),"4"));
				product_ext_hotProduct.setProduct_ext_shop(product_ext_shop);
			}
			map.put("product_ext_hotProduct", hotlist);
			break;
		case 1:
			List countList = commonDao.findByHql("select count(*) from Product_ext_hotProduct where productId=? and validflag=1",typeId);
			if(countList!=null && countList.size()>0){
				map.put("count", Integer.parseInt(countList.get(0)+""));
			}
			break;
		default:
			break;
		}
		return map;
	}

	@Override
	public boolean deleteHotProductByProductId(String productId) {
		
		boolean bool = false;
		try {
			bool = commonDao.executeBySql("update tb_product_ext_hotproduct set validflag=0 where productId in ("+productId+")");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

}
