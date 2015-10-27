package com.qushop.prod.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.prod.entity.ProductTrack;
import com.qushop.prod.service.ProductTrackService;
import com.qushop.user.entity.Oper;


@Service
public class ProductTrackServiceImpl implements ProductTrackService {

	@Resource
	CommonDao commonDao;
	
	@Override
	public List<ProductTrack> getProductTrackByMethod(int type, Oper oper,
			String... params) {
		List<ProductTrack> productTracksList = new ArrayList();
		switch (type) {
		case 0:
			productTracksList = commonDao.findByHql("from ProductTrack where productId=? and validflag=1", params[0]);
			break;
		case 1:
			productTracksList = commonDao.findByHql("from ProductTrack where productId=?", params[0]);
			break;

		default:
			break;
		}
		
		return productTracksList;
	}

	@Override
	public boolean addProductTrack(ProductTrack productTrack) {
		
		try {
			commonDao.insert(productTrack);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateProductTrack(ProductTrack productTrack) {

		try {
			commonDao.update(productTrack);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteProductTrack(String productId, Oper oper) {
		
		String sql = "update tb_product_track set validflag=0,lastUpdatetime=? where productId in ("+productId+") and operid=?";
		boolean bool = commonDao.executeBySql(sql, new Date(),oper.getOperId());
		return bool;
	}
}
