package com.qushop.prod.service;

import java.util.List;

import com.qushop.prod.entity.ProductTrack;
import com.qushop.user.entity.Oper;

public interface ProductTrackService {

	/**
	 * 
	 * @param type  0通过产品id查询溯源	1通过产品id查询溯源包含validflag=0
	 * @param oper
	 * @param params
	 * @return
	 */
	public List<ProductTrack> getProductTrackByMethod(int type,Oper oper,String ...params);
	
	public boolean addProductTrack(ProductTrack productTrack);
	
	public boolean updateProductTrack(ProductTrack productTrack);
	
	public boolean deleteProductTrack(String productId,Oper oper);
	
}
