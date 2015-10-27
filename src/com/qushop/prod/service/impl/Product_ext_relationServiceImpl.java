package com.qushop.prod.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.prod.entity.Product_ext_relation;
import com.qushop.prod.entity.Product_ext_shop;
import com.qushop.prod.service.ProductImgService;
import com.qushop.prod.service.Product_ext_relationService;
import com.qushop.prod.service.Product_ext_shopService;


@Service
@SuppressWarnings("unchecked")
public class Product_ext_relationServiceImpl implements
		Product_ext_relationService {

	@Resource
	CommonDao commonDao;
	
	@Resource
	SessionFactory sessionFactory;
	
	@Resource
	Product_ext_shopService ext_shopService;
	
	@Resource
	ProductImgService productImgService;
	
	@Override
	public boolean addRelation(Product_ext_relation product_ext_relation){
		
//		Session session = null;
		try {
//			session = sessionFactory.openSession();
//			session.getTransaction().begin();
			commonDao.insert(product_ext_relation);
//			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}finally{
//			session.close();
		}
		return false;
	}

	/**
	 * 
	 * @param type 0 查询所有 1查询曾经是否存在关联
	 * @param params
	 * @return
	 */
	@Override
	public List<Product_ext_relation> getRelationProductByMethod(int type, String... params) {
		
		List<Product_ext_relation> ext_relationsList = null;
		switch (type) {
		case 0:
			ext_relationsList = commonDao.findByHql("from Product_ext_relation where validflag = 1 order by productId1");
			for (Product_ext_relation product_ext_relation : ext_relationsList) {
			    Product_ext_shop product_ext_shop1 = ext_shopService.getShopProductByMethod(8, product_ext_relation.getProductId1()).get(0);
			    Product_ext_shop product_ext_shop2 = ext_shopService.getShopProductByMethod(8, product_ext_relation.getProductId2()).get(0);
				product_ext_relation.setProduct_ext_shop1(product_ext_shop1);
				product_ext_relation.setProduct_ext_shop2(product_ext_shop2);
			}
			break;
		case 1:
			
			ext_relationsList = commonDao.findByHql("from Product_ext_relation where "
					+ "((productId1=? and productId2=?) or (productId2=? and productId1 = ?))", 
					params[0],params[1],
					params[0],params[1]);
			
			break;
		default:
			break;
		}
		return ext_relationsList;
	}

	@Override
	public boolean deleteRelationProduct(String[] productId,String operId) {
		

//		Session session = null;
		try {
//			session = sessionFactory.openSession();
//			session.getTransaction().begin();
			if(productId!=null && productId.length>0){
				for (String ids : productId) {
					String[] productIds = ids.split("and");
//					Product_ext_relation ext_relation = 
//					(Product_ext_relation) session.createQuery("from Product_ext_relation where ((productId1=? and productId2=?) or (productId2=? and productId1=?))")
//					.setParameter(0, productIds[0]).setParameter(1,productIds[1]).setParameter(2, productIds[0]).setParameter(3,productIds[1]).list().get(0);
//					ext_relation.setValidflag((short)0);
//					session.update(ext_relation);

					commonDao.executeBySql("update tb_product_ext_relation set validflag=0,lastupdateTime=?,operId=?"
							+ " where ((productId1=? and productId2=?) or (productId2=? and productId1=?))",new Date(),operId, productIds[0],productIds[1],productIds[0],productIds[1]);
				}
			}
//			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}finally{
//			session.close();
		}
		
		return false;
	}

	@Override
	public boolean updateProduct_ext_relation(
			Product_ext_relation product_ext_relation) {
//		Session session = null;
		try {
//			session = sessionFactory.openSession();
//			session.getTransaction().begin();
			commonDao.update(product_ext_relation);
//			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		finally{
//			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteRelationByOneProduct(String productIds, String operId) {
		
		try {
			commonDao.executeBySql("update tb_product_ext_relation set validflag=0,lastupdateTime=?,operId=?"
					+ " where ((productId1 in ("+productIds+")) or (productId2 in ("+productIds+")))",new Date(),operId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
		return false;
	}
}
