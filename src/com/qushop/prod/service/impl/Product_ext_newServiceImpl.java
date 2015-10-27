package com.qushop.prod.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.prod.entity.Product_ext_new;
import com.qushop.prod.entity.Product_ext_shop;
import com.qushop.prod.service.ProductImgService;
import com.qushop.prod.service.Product_ext_newService;
import com.qushop.prod.service.Product_ext_shopService;

/**
 * 
 * 
 * @author xie
 *
 */
@Service
public class Product_ext_newServiceImpl implements Product_ext_newService {

	@Resource
	CommonDao comonDao;

	@Resource
	Product_ext_shopService ext_shopService;
	
	@Resource
	ProductImgService productImgService;
	
	@Override
	public boolean addNewProduct(Product_ext_new ext_new) {
		
		try {
			List<Product_ext_new> ext_newsList =  comonDao.findByHql("from Product_ext_new where productId = ? and validflag=1", ext_new.getProductId());
			if(ext_newsList!=null && ext_newsList.size()>0){
				Product_ext_new ext_news = ext_newsList.get(0);
				ext_news.setValidflag((short)1);
				ext_news.setLastUpdateTime(new Date());
				comonDao.update(ext_news);
				return true;
			}
			comonDao.insert(ext_new);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 
	 * @param type   0 查询列表    1查询详情       2根据id查询新品曾经是否存在 3查询商品是否存在
	 * @param params
	 * @return
	 */
	@Override
	public List getNewProductByMethod(int type,
			String... params) {

		List<Product_ext_new> product_ext_newsList = new ArrayList<Product_ext_new>();
		
		switch (type) {
		case 0:
			//判断是否查询指定个数还是查询全部 params[1]=maxcount
			if(params !=null && params.length>=2 && params[1]==null && !params[1].equals(""))
			{
				product_ext_newsList = comonDao.findPagingByHql("from Product_ext_new where validflag = 1",0,Integer.parseInt(params[1]));
			}else
			{
				product_ext_newsList = comonDao.findByHql("from Product_ext_new where validflag = 1");
			}
			break;

		case 1:
			product_ext_newsList = comonDao.findByHql("from Product_ext_new where productId=? and validflag=1", params[0]);
			break;
		case 2:
			product_ext_newsList = comonDao.findByHql("from Product_ext_new where productId=? and validflag=0", params[0]);
			break;
		case 3:
			return comonDao.findByHql("select count(*) from Product_ext_new where productId=? and validflag=1", params[0]);
		case 4:
			return comonDao.findBySql("select * from tb_product_ext_new where productId in ("+params[0]+") and validflag=1",null);
		default:
			break;
		}

		for (Product_ext_new product_ext_new : product_ext_newsList) 
		{
			Product_ext_shop product_ext_shop = ext_shopService.getShopProductByMethod(8, product_ext_new.getProductId()).get(0);
			product_ext_shop.getProduct().setProductimglist(productImgService.getProductImgByMethod(1, product_ext_new.getProductId(),"3"));
			product_ext_new.setProduct_ext_shop(product_ext_shop);
		}

		return product_ext_newsList;
	}

	@Override
	public boolean updateProduct(Product_ext_new product_ext_new) {

		try {
			comonDao.update(product_ext_new);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteNewProduct(String productId,String operId) {

		try {
			String sql = "update tb_product_ext_new set validflag = 0,lastupdatetime=?,operid=? where productId in("+productId+") and validflag=1";
			return comonDao.executeBySql(sql,new Date(),operId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
