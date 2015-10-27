package com.qushop.musicains.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.musicains.entity.Discountconfig;
import com.qushop.musicains.entity.PDiscountconfig;
import com.qushop.musicains.service.DiscountconfigService;

@Service
public class DiscountconfigServiceImpl implements DiscountconfigService {

	@Resource
	CommonDao<Discountconfig> commonDao;

	@Resource
	CommonDao<PDiscountconfig> pcommonDao;

	@Override
	public List<Discountconfig> getDiscountConfigByProductTypeId(
			String productTypeId) {
		
		return commonDao.findByHql("from Discountconfig where productTypeId=?", productTypeId);
		
	}

	@Override
	public List<PDiscountconfig> getDiscountConfigProductId(String productId) {

		List<PDiscountconfig> discountconfigs = pcommonDao.findByHql("from PDiscountconfig where productId=?", productId);
		if(discountconfigs!=null && discountconfigs.size()>0){
			return discountconfigs;
		}
		return null;
	}
	
}
