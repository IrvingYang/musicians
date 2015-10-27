package com.qushop.prod.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.PublicUtil;
import com.qushop.prod.entity.Product;
import com.qushop.prod.entity.ProductImg;
import com.qushop.prod.entity.Product_ext_bigDeal;
import com.qushop.prod.service.ProductImgService;
import com.qushop.prod.service.ProductService;
import com.qushop.prod.service.Product_ext_bigDealService;
@Service
public class Product_ext_bigDealServiceImpl implements
    Product_ext_bigDealService {

	@Resource
	CommonDao<Product_ext_bigDeal> bigdealdao;
	
	@Resource
	ProductService productService;
	
	@Resource
	ProductImgService productImgService;
	
	public List<Product_ext_bigDeal> getbigDealProductByMethod(int type, String ...params) {
		// TODO Auto-generated method stub
		List<Product_ext_bigDeal> bigDealList = null;
		List<ProductImg> imgsList = null;
		switch (type) {
		case 1:
			if (params[0].equals("00")) {
				bigDealList = bigdealdao.findPagingByHql("from Product_ext_bigDeal where  validflag = 1", 0,Integer.parseInt(params[1]));
			} else {

			}
			break;
		case 2:		
			bigDealList = bigdealdao.findPagingByHql("from Product_ext_bigDeal where bigdealid = ?  and validflag = 1",  0, Integer.parseInt(params[1]), params[0]);
			String prodtype = bigDealList.get(0).getProductId().substring(4, 6);
			bigDealList = bigdealdao.findPagingByHql("from Product_ext_bigDeal where substr(productid, 5, 2) = ? and bigdealid <> ? and validflag = 1",  0, Integer.parseInt(params[1]), prodtype, params[0]);
            break;
		case 3:		
			bigDealList = bigdealdao.findPagingByHql("from Product_ext_bigDeal where bigdealid = ?  and validflag = 1",  0, Integer.parseInt(params[1]), params[0]);
			break;
		case 4:
			if(Integer.parseInt(params[0])==0){
				bigDealList = bigdealdao.findByHql("from Product_ext_bigDeal where validflag=1 ");
			}
			if(Integer.parseInt(params[0])==1){
				bigDealList = bigdealdao.findBySql("select bigdeal.* from tb_product_ext_bigDeal bigdeal,tb_product product where bigdeal.productId=product.productId and bigdeal.validflag=1 and providerId=?",Product_ext_bigDeal.class,params[1]);
			}
			break;
		case 5:
			bigDealList = bigdealdao.findByHql("from Product_ext_bigDeal where bigdealId=?", params[0]);
			break;
		case 6:
			bigDealList = bigdealdao.findBySql("select * from tb_product_ext_bigDeal where productId in("+params[0]+") and validflag=1",null);
			return bigDealList;
		default:
			break;
		}
		for (Product_ext_bigDeal product_ext_bigDeal : bigDealList) {
			
			Product product = productService.getProductListByMethod(2, null, product_ext_bigDeal.getProductId()).get(0);
			switch(type) {
			case 1:
				imgsList = productImgService.getProductImgByMethod(6, product.getProductId());
				break;
			case 2:
				imgsList = productImgService.getProductImgByMethod(1, product.getProductId(), "4");
				break;
			case 3:
				imgsList = productImgService.getProductImgByMethod(4, product.getProductId());
				break;
			default:
				break;
			}
			
			product.setProductimglist(imgsList);
			product_ext_bigDeal.setProduct(product);
		}

		
		return bigDealList;
	}
	
//	@Override
//	public List<Product_ext_bigDeal> getProductBigDealByMethod(int type,
//			String... params) {
//		
//		List<Product_ext_bigDeal> bigDealsList = new ArrayList<Product_ext_bigDeal>();
//		switch (type) {
//		case 1:
//			if(Integer.parseInt(params[0])==0){
//				bigDealsList = bigdealdao.findByHql("from Product_ext_bigDeal where validflag=1 ");
//			}
//			if(Integer.parseInt(params[0])==1){
//				bigDealsList = bigdealdao.findBySql("select bigdeal.* from tb_product_ext_bigDeal bigdeal,tb_product product where bigdeal.productId=product.productId and bigdeal.validflag=1 and providerId=?",Product_ext_bigDeal.class,params[1]);
//			}
//			break;
//			
//		case 2:
//			bigDealsList = bigdealdao.findByHql("from Product_ext_bigDeal where bigdealId=? and validflag=1 ",params[0]);
//			break;
//		case 3:
//			bigDealsList = bigdealdao.findByHql("from Product_ext_bigDeal where bigdealId=? and validflag=0", params[0]);
//			break;
//		default:
//			break;
//		}
//		
//		for (Product_ext_bigDeal product_ext_bigDeal : bigDealsList) {
//			Product product = productService.getProductListByMethod(2, null, product_ext_bigDeal.getProductId()).get(0);
//			product_ext_bigDeal.setProduct(product);
//		}
//		
//		return bigDealsList;
//	}

	@Override
	public boolean addProductBigDeal(Product_ext_bigDeal bigDeal) {

		try {
			bigdealdao.insert(bigDeal);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateProductBigDeal(Product_ext_bigDeal bigDeal) {
		
		bigDeal.setLastUpdateTime(new Date());
		try {
			bigdealdao.update(bigDeal);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteProductBigDeal(String bigDealIds,HttpServletRequest request) {
		
		try {
			String sql = "update tb_product_ext_bigDeal set validflag=0,lastUpdateTime=?,operid=? where bigDealId in("+bigDealIds+") and validflag=1";
			return  bigdealdao.executeBySql(sql, new Date(),PublicUtil.getOper(request).getOperId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
