package com.qushop.provider.service;

import java.util.List;

import com.qushop.provider.entity.Provider_image;

public interface ProviderImgService {
	
	
	public boolean addProviderImg(Provider_image provider_image);
	
	public boolean deleteProviderImgByProviderIdPhysically(String providerId);
	
	/**
	 * 
	 * @param type 1通过providerId 和类型查询图片信息
	 * @param params
	 * @return
	 */
	public List<Provider_image> getProviderImageByMethod(int type,String...params);
	

}