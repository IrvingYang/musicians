package com.qushop.prod.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.PagePojo;
import com.qushop.prod.entity.Product;
import com.qushop.prod.entity.ProductImg;
import com.qushop.prod.entity.Product_ext_shop;
import com.qushop.prod.service.ProductImgService;
import com.qushop.prod.service.ProductService;
import com.qushop.prod.service.Product_ext_hotProductService;
import com.qushop.prod.service.Product_ext_newService;
import com.qushop.prod.service.Product_ext_recommProdService;
import com.qushop.prod.service.Product_ext_relationService;
import com.qushop.prod.service.Product_ext_shopService;

@Service
@SuppressWarnings("unchecked")
public class Product_ext_shopServiceImpl implements Product_ext_shopService {

	@Resource
	CommonDao commonDao;

	@Resource
	ProductService productService;

	@Resource
	ProductImgService productImgService;

	@Resource
	Product_ext_hotProductService product_ext_hotProductService;

	@Resource
	Product_ext_hotProductService hotProductService;

	@Resource
	Product_ext_relationService relationService;

	@Resource
	Product_ext_newService ext_newService;

	@Resource
	Product_ext_recommProdService ext_recommProdService;

	@Override
	public Map getShopProductByProductTypeId(String level, String productTypeId, String ordercondition, int pageno,
			int pagesize, String listtype) {

		String sql = "";
		if ("new".equals(listtype)) {
			sql = "from tb_product_ext_shop where validflag=1";
		} else if ("sale".equals(listtype)) {
			sql = "from tb_product_ext_shop where promoteflag=1 and validflag=1 order by onTime desc";
		} else {
			switch (Integer.valueOf(level)) {
			case 0:
				break;
			case 1:
			case 2:
				sql = "from tb_product_ext_shop where productId in (select productId from tb_product where productTypeId like '"
						+ productTypeId + "%' and validflag = 1) and validflag = 1";
				break;
			case 3:
				sql = "from tb_product_ext_shop where productId in (select productId from tb_product where productTypeId='"
						+ productTypeId + "' and validflag = 1) and validflag = 1";
				break;
			default:
				sql = "from tb_product_ext_shop where validflag=1";
				break;
			}
		}

		// Map<String,String> params = new HashMap<String,String>();

		List countList = commonDao.getSession().createSQLQuery("select count(*) " + sql).list();
		int totalCount = Integer.parseInt(countList.get(0) + "");
		PagePojo pagePojo = new PagePojo();
		pagePojo.setPagesize(pagesize);
		pagePojo.setTotalcount(totalCount);

		if (pageno <= 0) {
			pageno = 1;
		}
		if (pageno >= pagePojo.getTotalpage()) {
			pageno = pagePojo.getTotalpage();
		}
		pagePojo.setPageno(pageno);

		// params.put("pageno",
		// (pagePojo.getPageno()-1)*pagePojo.getPagesize()+"");
		// params.put("pagesize", pagePojo.getPagesize()+"");

		if (ordercondition != null && !"".equals(ordercondition)) {

			if ("priceasc".equals(ordercondition)) {
				if ("new".equals(listtype)) {
					sql += " order by originalPrice asc";
				} else if ("sale".equals(listtype)) {
					sql += ", originalPrice asc";
				} else {
					sql += " order by originalPrice asc";

				}

			} else if ("pricedesc".equals(ordercondition)) {
				if ("new".equals(listtype)) {
					sql += " order by originalPrice desc";
				} else if ("sale".equals(listtype)) {
					sql += ",originalPrice desc";
				} else {
					sql += " order by originalPrice desc";

				}
			}
		}

		List<Product_ext_shop> product_ext_shops = commonDao.findPagingBySql("select * " + sql,
				pagePojo.getPageno() - 1, pagePojo.getPagesize(), Product_ext_shop.class);

		for (Product_ext_shop product_ext_shop : product_ext_shops) {
			Product product = productService.getProductListByMethod(2, null, product_ext_shop.getProductId()).get(0);
			List<ProductImg> productImgsList = productImgService.getProductImgByMethod(1,
					product_ext_shop.getProductId(), "3");
			product.setProductimglist(productImgsList);
			product_ext_shop.setProduct(product);
		}

		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("shopproduct", product_ext_shops);
		rMap.put("page", pagePojo);
		return rMap;
	}

	@Override
	public boolean existsExtShopProductInProductId(String productId) {
		Integer count = 0;
		if (productId != null && !productId.trim().equals("")) {
			count = Integer.parseInt(commonDao.findBySql(
					"select count(*) from tb_product_ext_shop where productId in(" + productId + ") and validflag=1",
					null).get(0) + "");
		}
		return count > 0 ? true : false;
	}

	@Override
	public boolean deleteProductShop(String productIds, String operId) {
		boolean bool = false;
		if (productIds != null && !productIds.trim().equals("")) {
			String sql = "update tb_product_ext_shop set validflag=0,lastUpdateTime=?,operid=? where productId in("
					+ productIds + ") and validflag=1";
			// 热销可以删除，relation可以删除
			bool = hotProductService.deleteHotProductByProductId(productIds);
			bool = relationService.deleteRelationByOneProduct(productIds, operId);
			bool = product_ext_hotProductService.deleteHotProductByProductId(productIds);
			bool = commonDao.executeBySql(sql, new Date(), operId);
		}
		return bool;
	}

	@Override
	public boolean updateProductShop(Product_ext_shop shop) {

		try {
			commonDao.update(shop);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addProductShop(Product_ext_shop shop) {
		try {
			commonDao.insert(shop);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deletePromoteProduct(String productIds, String operId) {
		boolean bool = false;
		if (productIds != null && !productIds.trim().equals("")) {
			String sql = "update tb_product_ext_shop set promoteflag=0,promotePrice=null,lastupdatetime=?,operid=? where productId in("
					+ productIds + ") and validflag=1";
			bool = commonDao.executeBySql(sql, new Date(), operId);
		}
		return bool;

	}

	@Override
	public List<Product_ext_shop> getShopProductByMethod(int type, String... params) {
		List<Product_ext_shop> product_ext_shopsList = new ArrayList<Product_ext_shop>();
		switch (type) {
		case 0:
			product_ext_shopsList = commonDao.findByHql("from Product_ext_shop where validflag =1");
			break;
		case 1:
			List<String> list = commonDao.findBySql(
					"SELECT productId1 FROM tb_product_ext_relation WHERE productId2 = ? and validflag=1 "
							+ "UNION SELECT productId2 FROM tb_product_ext_relation WHERE productId1 = ? and validflag=1",
					null, params[0], params[0]);
			for (String proId : list) {
				Product_ext_shop ext_shop = (Product_ext_shop) commonDao
						.findByHql("from Product_ext_shop where productId=? and validflag =1", proId).get(0);
				product_ext_shopsList.add(ext_shop);
			}
			break;
		case 2:
			String sql = "select * from tb_product_ext_shop shop ";
			if (params != null && params.length > 0) {
				sql += "where productId NOT IN (" + " SELECT productId1	FROM tb_product_ext_relation relation1	"
						+ " WHERE relation1.productId2 =  " + params[0]
						+ " and validflag=1 UNION SELECT	productId2 FROM tb_product_ext_relation relation2 "
						+ " WHERE relation2.productId1 =  " + params[0] + " and validflag=1) and shop.productId <> "
						+ params[0] + " and validflag=1";
			}
			product_ext_shopsList = commonDao.findBySql(sql, Product_ext_shop.class);
			break;

		case 3:
			String promotesql = "select * from tb_product_ext_shop where (promoteflag = 0  or promoteEndTime < now() or promoteEndTime is null) and validflag=1";
			product_ext_shopsList = commonDao.findBySql(promotesql, Product_ext_shop.class);
			break;

		case 4:
			product_ext_shopsList = commonDao.findBySql(
					"select * from tb_product_ext_shop where promoteflag = 1 and promoteEndTime > now()  and validflag=1 ",
					Product_ext_shop.class);
			break;

		case 5:
			product_ext_shopsList = commonDao.findByHql("from Product_ext_shop where productId=? and validflag =1",
					params[0]);
			break;

		case 6:
			product_ext_shopsList = commonDao.findByHql("from Product_ext_shop where productId=?", params[0]);
			break;

		case 7:
			String tables = null;
			String shopsql = "select * from tb_product_ext_shop where validflag=1 ";

			if (params[0] != null && !"".equals(params[0])) {
				String notProductId = "";
				if ("recommend".equals(params[0])) {
					tables = "tb_product_ext_recommprod where now() < recommEndTime";
				} else if ("new".equals(params[0])) {
					tables = "tb_product_ext_new where 1=1 ";
				}
				String newAndRecommendsql = "select productId from " + tables + " and validflag=1";
				List<String> newAndRecommendList = commonDao.findBySql(newAndRecommendsql, null);
				if (newAndRecommendList != null && newAndRecommendList.size() > 0) {
					for (int i = 0; i < newAndRecommendList.size(); i++) {
						String productId = newAndRecommendList.get(i);
						if (i != newAndRecommendList.size() - 1) {
							notProductId += (productId + ",");
							continue;
						}
						notProductId += productId;
					}
					shopsql += "and productId not in (" + notProductId + ")";
				}
			}
			product_ext_shopsList = commonDao.findBySql(shopsql, Product_ext_shop.class);

			break;
		case 8:
			product_ext_shopsList = commonDao.findByHql("from Product_ext_shop where productId=? and validflag =1",
					params[0]);
			for (Product_ext_shop product_ext_shop : product_ext_shopsList) {
				Product product = productService.getProductListByMethod(4, null, product_ext_shop.getProductId())
						.get(0);
				product_ext_shop.setProduct(product);
			}
			return product_ext_shopsList;
		default:
			break;
		}

		for (Product_ext_shop product_ext_shop : product_ext_shopsList) {
			Product product = productService.getProductListByMethod(2, null, product_ext_shop.getProductId()).get(0);
			product_ext_shop.setProduct(product);
		}
		return product_ext_shopsList;
	}

	@Override
	public Map searchShopProductByProductShop(String searchKey, String ordercondition, int pageno, int pagesize) {

		String sql = "from tb_product_ext_shop shop,tb_product product where " + "shop.productId=product.productId "
				+ "and product.productName like '%" + searchKey + "%' "
				+ "and shop.validflag=1 order by shop.onTime desc";

		List countList = commonDao.getSession().createSQLQuery("select count(*) " + sql).list();
		int totalCount = Integer.parseInt(countList.get(0) + "");
		PagePojo pagePojo = new PagePojo();
		pagePojo.setPagesize(pagesize);
		pagePojo.setTotalcount(totalCount);
		// if(pageno<=0){
		// pageno = 1;
		// }
		if (pageno >= pagePojo.getTotalpage()) {
			pageno = pagePojo.getTotalpage();
		}
		pagePojo.setPageno(pageno);

		if (ordercondition != null && !"".equals(ordercondition)) {
			if ("priceasc".equals(ordercondition)) {
				sql += " order by originalPrice asc";
			} else if ("pricedesc".equals(ordercondition)) {
				sql += " order by originalPrice desc";
			}
		}

		List<Product_ext_shop> product_ext_shops = commonDao.findPagingBySql("select * " + sql,
				pagePojo.getPageno() - 1, pagePojo.getPagesize(), Product_ext_shop.class);

		for (Product_ext_shop product_ext_shop : product_ext_shops) {
			Product product = productService.getProductListByMethod(2, null, product_ext_shop.getProductId()).get(0);
			List<ProductImg> productImgsList = productImgService.getProductImgByMethod(1,
					product_ext_shop.getProductId(), "3");
			product.setProductimglist(productImgsList);
			product_ext_shop.setProduct(product);
		}

		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("shopproduct", product_ext_shops);
		rMap.put("page", pagePojo);

		return rMap;
	}

	@Override
	public List<Product_ext_shop> getProductShopPageByCondition(PagePojo pagePojo, String pricestr, String order,
			String searchKey, String grade, String brandstr,String typestr) {

		String sql = "from tb_product_ext_shop shop,tb_product product where shop.productId=product.productId and shop.validflag=1 and product.productName like '%"
				+ searchKey + "%' ";
		if (null != pricestr && !"".equals(pricestr)) {
			boolean mutilple = false;
			String[] prices = pricestr.split(",");
			for (String price : prices) {

				if (mutilple) {
					sql += " or ";
				} else {
					sql += " and (";
				}

				if (price != null && !"".equals(price)) {
					if ("100".equals(price))
						sql += "(shop.originalPrice>=0 and shop.originalPrice<=100 )";
					else if ("500".equals(price))
						sql += "(shop.originalPrice>100 and shop.originalPrice<=500) ";
					else if ("1000".equals(price))
						sql += "(shop.originalPrice>500 and shop.originalPrice<=1000 )";
					else if ("3000".equals(price))
						sql += "(shop.originalPrice>1000 and shop.originalPrice<=3000) ";
					else if ("*".equals(price))
						sql += "(shop.originalPrice>3000) ";
				}

				mutilple = true;
			}
			sql += " )";
		}
		if (brandstr != null && !"".equals(brandstr)) {
			boolean mutilple = false;
			String[] brands = brandstr.split(",");
			for (String brand : brands) {

				if (mutilple) {
					sql += " or ";
				} else {
					sql += " and (";
				}

				sql += " product.brandid = '" + brand +"'";
				
				mutilple = true;
			}
			sql += " )";

		}
		
		

		if (typestr != null && !"".equals(typestr)) {
			sql += " and product.productTypeId like '"+ typestr + "%'";
		}
		if (grade != null && !"".equals(grade)) {
			sql += " and product.productGrade >=" + grade + " ";
		}
		if (order != null && !"".equals(order)) {
			if ("sell".equals(order))
				sql += " order by product.salesvolume desc";
			else if ("priceH".equals(order))
				sql += " order by shop.originalPrice desc";
			else if ("priceL".equals(order))
				sql += " order by shop.originalPrice asc";
			else if ("laest".equals(order))
				sql += " order by shop.onTime desc";
			else if ("name".equals(order))
				sql += " order by product.salesvolume desc";
			else if ("point".equals(order))
				sql += " order by product.productName desc";
		}

		List<Product_ext_shop> shopsList = commonDao.findPagingBySql("select shop.* " + sql, pagePojo.getPageno(),
				pagePojo.getPagesize(), Product_ext_shop.class);
		List listCount = commonDao.findBySql("select count(*) " + sql, null);
		Integer totalcount = Integer.parseInt(listCount.get(0) + "");
		pagePojo.setTotalcount(totalcount);

		for (Product_ext_shop product_ext_shop : shopsList) {
			Product product = productService.getProductListByMethod(2, null, product_ext_shop.getProductId()).get(0);
			List<ProductImg> productImgsList = productImgService.getProductImgByMethod(1,
					product_ext_shop.getProductId(), "3");
			product.setProductimglist(productImgsList);
			product_ext_shop.setProduct(product);
		}

		return shopsList;
	}

}
