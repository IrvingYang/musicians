package com.qushop.provider.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.provider.entity.Provider_image;
import com.qushop.provider.service.ProviderImgService;


@Service
public class ProviderImgServiceImpl implements ProviderImgService {

	@Resource
	CommonDao commonDao;
	
	@Override
	public boolean addProviderImg(Provider_image provider_image) {
		
		try {
			commonDao.insert(provider_image);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean deleteProviderImgByProviderIdPhysically(String providerId) {

		return commonDao.executeBySql("delete from tb_provider_image where providerId=?", providerId);
		
	}

	@Override
	public List<Provider_image> getProviderImageByMethod(int type,
			String... params) {
		
		List<Provider_image> providersList = new ArrayList<Provider_image>();
		switch (type) {
		case 1:
			providersList = commonDao.findByHql("from Provider_image where providerId=? and imgType=? order by imgType asc", params[0],Short.parseShort(params[1]));
			break;
		case 2:
			providersList = commonDao.findByHql("from Provider_image where providerId=?", params[0]);
			break;
			
		}
		return providersList;
	}

}
