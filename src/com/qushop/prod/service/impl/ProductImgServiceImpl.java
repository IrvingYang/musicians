package com.qushop.prod.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.prod.entity.ProductImg;
import com.qushop.prod.service.ProductImgService;

@Service
public class ProductImgServiceImpl implements ProductImgService {

	@Resource
	CommonDao commonDao;
	
	@Override
	public List<ProductImg> getProductImgByMethod(int type, String... params) {
		List<ProductImg> productImgsList = null;
		switch (type) {
		case 1:
			productImgsList = commonDao.findByHql("from ProductImg where productId=? and imgType=? and validflag=1 order by serialNumber asc,imgType asc", params[0],Short.parseShort(params[1]));
			break;
		case 2:
			productImgsList = commonDao.findByHql("from ProductImg where productId=? and imgType=5 and validflag=1", params[0]);
			break;
		case 3:
			productImgsList = commonDao.findByHql("from ProductImg where productId=? and validflag = 1 order by serialNumber asc,imgType asc", params[0]);
			break;
		case 4:
			productImgsList = commonDao.findByHql("from ProductImg where productId=? and imgType in(1,2,4) order by serialNumber asc,imgType asc", params[0]);
			break;
		case 5:
			productImgsList = commonDao.findByHql("from ProductImg where productId=? and imgType in(1,2,3,4) order by serialNumber asc,imgType asc",params[0]);
			break;
		case 6:
			productImgsList = commonDao.findByHql("from ProductImg where productId=? and imgType in(5,6) order by serialNumber asc,imgType asc",params[0]);
			break;
		}
		return productImgsList;
	}


	@Override
	public boolean deleteProductImgPhysically(int type,String ...params) {
		
		switch (type) {
		case 0:
			commonDao.executeBySql("delete from tb_productImg where productId=? and imgType in(1,2,3,4)", params[0]);
			break;
		case 1:
			commonDao.executeBySql("delete from tb_productImg where productId=? and imgType in(5,6)", params[0]);
			break;
		case 2:
			commonDao.executeBySql("delete from tb_productImg where productId=?", params[0]);
			break;
		case 3:
			commonDao.executeBySql("delete from tb_productImg where productId in ("+params[0]+")");
			break;

		default:
			break;
		}
		return false;
	}


	@Override
	public boolean addProductImg(ProductImg productImg) {
		try {
			commonDao.insert(productImg);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;		
	}

}
