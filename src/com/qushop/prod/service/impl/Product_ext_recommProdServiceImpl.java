package com.qushop.prod.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.prod.entity.Product_ext_recommProd;
import com.qushop.prod.entity.Product_ext_shop;
import com.qushop.prod.service.ProductImgService;
import com.qushop.prod.service.Product_ext_recommProdService;
import com.qushop.prod.service.Product_ext_shopService;

/**
 * 
 * 
 * @author xie
 *
 */

@Service
public class Product_ext_recommProdServiceImpl implements Product_ext_recommProdService {

	@Resource
	CommonDao<Product_ext_recommProd> commonDao;

	@Resource
	Product_ext_shopService ext_shopService;

	@Resource
	ProductImgService productImgService;

	@Override
	public boolean addRecommendProduct(Product_ext_recommProd recommProd) {

		try {
			commonDao.insert(recommProd);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List getRecommProductByMethod(int type, String... params) {

		List<Product_ext_recommProd> product_ext_recommProdsList = null;

		switch (type) {
		case 0:
			if(null!=params&&params.length>0){
				product_ext_recommProdsList = commonDao.findBySql(
						"select * from tb_product_ext_recommProd where recommEndTime > now() and validflag=1 and productId!=?",
						Product_ext_recommProd.class, params[0]);
			}else{
				product_ext_recommProdsList = commonDao.findBySql(
						"select * from tb_product_ext_recommProd where recommEndTime > now() and validflag=1",
						Product_ext_recommProd.class);
			}
			break;
		case 1:
			product_ext_recommProdsList = commonDao.findBySql(
					"select * from tb_product_ext_recommProd where productId=? and validflag=1",
					Product_ext_recommProd.class, params[0]);
			break;
		case 2:
			product_ext_recommProdsList = commonDao.findBySql(
					"select * from tb_product_ext_recommProd where productId=? and (validflag=0 or recommEndTime < now())",
					Product_ext_recommProd.class, params[0]);
			break;
		case 3:
			return commonDao.findBySql(
					"select count(*) from tb_product_ext_recommProd where productId=? and (validflag=1 and recommEndTime > now())",
					null, params[0]);
		case 4:
			product_ext_recommProdsList = commonDao.findPagingBySql(
					"select * from tb_product_ext_recommProd where recommEndTime > now() and validflag=1", 0,
					Integer.parseInt(params[0]), Product_ext_recommProd.class);
			break;
		case 5:
			product_ext_recommProdsList = commonDao.findBySql(
					"select * from tb_product_ext_recommProd where productId in(" + params[0] + ") and validflag=1",
					Product_ext_recommProd.class);
			break;
		default:
			break;
		}
		for (Product_ext_recommProd product_ext_recommProd : product_ext_recommProdsList) {
				Product_ext_shop product_ext_shop = ext_shopService
						.getShopProductByMethod(5, product_ext_recommProd.getProductId()).get(0);
				product_ext_shop.getProduct().setProductimglist(
						productImgService.getProductImgByMethod(1, product_ext_recommProd.getProductId(), "4"));
				product_ext_recommProd.setProduct_ext_shop(product_ext_shop);
		}
		return product_ext_recommProdsList;
	}

	@Override
	public boolean updateRecommendProduct(Product_ext_recommProd recommProd) {

		try {
			commonDao.update(recommProd);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteRecommendProduct(String productId, String operId) {
		try {
			return commonDao.executeBySql("update tb_product_ext_recommProd set validflag = 0,lastUpdateTime=?,"
					+ "operid=? where productId in (" + productId + ") and validflag=1", new Date(), operId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
