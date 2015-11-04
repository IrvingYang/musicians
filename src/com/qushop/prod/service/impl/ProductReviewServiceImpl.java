package com.qushop.prod.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.PublicUtil;
import com.qushop.prod.entity.Product;
import com.qushop.prod.entity.ProductReview;
import com.qushop.prod.service.ProductReviewService;
import com.qushop.prod.service.ProductService;
import com.qushop.user.entity.Oper;
import com.qushop.user.entity.User;
import com.qushop.user.service.UserService;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

	@Resource
	CommonDao<ProductReview> commonDao;

	@Resource
	UserService userService;

	@Resource
	ProductService productService;

	@Override
	public List<ProductReview> getProductReviewByMethod(int type, String... params) {

		List<ProductReview> productReviewsList = new ArrayList();

		switch (type) {
		case 0:
			productReviewsList = commonDao.findByHql("from ProductReview where validflag = 1");
			for (ProductReview productReview : productReviewsList) {
				Product product = productService.getProductListByMethod(4, null, productReview.getProductId()).get(0);
				User user = (User) userService.getUserByMethod(11, productReview.getUserId()).get(0);
				productReview.setUser(user);
				productReview.setProduct(product);
			}
			break;
		case 1:
			if (params.length > 1) {
				int pageNo = Integer.parseInt(params[1]);
				int pageSize = Integer.parseInt(params[2]);
				productReviewsList = commonDao.findPagingByHql(
						"from ProductReview where productId=? and  validflag = 1", pageNo, pageSize, params[0]);
			} else {
				productReviewsList = commonDao.findByHql("from ProductReview where productId=? and  validflag = 1",
						params[0]);
			}

			for (ProductReview productReview : productReviewsList) {
				User user = (User) userService.getUserByMethod(11, productReview.getUserId()).get(0);
				productReview.setUser(user);
			}
			break;
		}

		return productReviewsList;
	}

	@Override
	public boolean deleteProductReview(String reviewId, HttpServletRequest request) {

		try {
			return commonDao.executeBySql(
					"update tb_productreview set validflag=0, operid=?, lastupdatetime=? where reviewId=? and validflag=1",
					PublicUtil.getOper(request).getOperId(), new Date(), reviewId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteProductReviewByProduct(String productId, Oper oper) {

		try {
			return commonDao.executeBySql(
					"update tb_productreview set validflag=0,operid=?,lastupdatetime=? where productId in (" + productId
							+ ") ",
					oper.getOperId(), new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<ProductReview> getProductReviewPaging(String productId, Integer pageNo, Integer pageSize) {
		List<ProductReview> productReviewsList = new ArrayList<ProductReview>();
		productReviewsList = commonDao.findPagingByHql("from ProductReview where productId=? and  validflag = 1",
				pageNo, pageSize, productId);
		return productReviewsList;
	}
	
	
	
	
	public List<ProductReview> getProductReviewByOrderId(String orderId){
		String sql = "from ProductReview where orderId=?";
		List<ProductReview> findByHql = commonDao.findByHql(sql,orderId);
		return findByHql;
	}

	@Override
	public boolean addProductReview(String productId, Integer rate, String productComments, String orderId,
			String userId) {

		String sql = "from ProductReview where productId=? and orderId=?";

		List<ProductReview> findByHql = commonDao.findByHql(sql,productId,orderId);

		if (findByHql != null && findByHql.size() > 0) {
			return false;
		} else {

			ProductReview productReview = new ProductReview();
			productReview.setProductId(productId);
			productReview.setCommentsDate(new Date());
			productReview.setUserId(userId);
			productReview.setRate(rate.shortValue());
			productReview.setValidflag(new Integer(1).shortValue());
			productReview.setProductComments(productComments);
			productReview.setLastUpdateTime(new Date());
			productReview.setOrderId(orderId);
			try {
				commonDao.insert(productReview);
				return true;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
