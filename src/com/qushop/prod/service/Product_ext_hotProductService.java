package com.qushop.prod.service;

import java.util.Map;

public interface Product_ext_hotProductService {

	public Map getHotProductByType(Integer type,String typeId,Integer maxCount);
	
	public boolean deleteHotProductByProductId(String productId);
}
