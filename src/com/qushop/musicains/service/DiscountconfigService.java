package com.qushop.musicains.service;

import java.util.List;

import com.qushop.musicains.entity.Discountconfig;
import com.qushop.musicains.entity.PDiscountconfig;

public interface DiscountconfigService {

	public List<Discountconfig> getDiscountConfigByProductTypeId(String productTypeId);

	public List<PDiscountconfig> getDiscountConfigProductId(String productId);
	
}
