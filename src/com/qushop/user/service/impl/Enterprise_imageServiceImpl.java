package com.qushop.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.user.entity.Enterprise_image;
import com.qushop.user.service.Enterprise_imageService;


@Service
public class Enterprise_imageServiceImpl implements Enterprise_imageService {

	@Resource
	CommonDao<Enterprise_image> imageCommonDao;
	
	@Override
	public void addEnterprise_image(Enterprise_image image) {
		
		try {
			imageCommonDao.insert(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteRellayImageByUserId(String userId) {

		return imageCommonDao.executeBySql("delete from tb_enterprise_image where userId=?", userId);
		
	}

	@Override
	public List<Enterprise_image> getImageByMethod(int type, String... params) {
		
		List<Enterprise_image> imagesList = new ArrayList();
		
		switch (type) {
		case 1:
			imagesList = imageCommonDao.findByHql("from Enterprise_image where userId=?", params[0]);
			break;
		}
		
		return imagesList;
	}

}
