package com.qushop.trip.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.trip.entity.Entityimage;
import com.qushop.trip.service.EntityimageService;
import com.qushop.user.entity.Oper;


@Service
public class EntityimageServiceImpl implements EntityimageService {

	@Resource
	CommonDao commonDao;
	
	@Override
	public List<Entityimage> getEntityimagesByMethod(int type, int maxCount,
			Oper oper, String... params) 
	{
		List<Entityimage> entityimagesList = new ArrayList<Entityimage>();
		switch (type) {
		case 0:
			entityimagesList = commonDao.findByHql("from Entityimage where entitytype=? and entityid=? order by imgType asc", params[0], params[1]);
			break;
		case 1:
			entityimagesList = commonDao.findByHql("from Entityimage where imageid=?", params[0]);
			break;

		default:
			break;
		}
		return entityimagesList;
	}

	@Override
	public String actualDelete(Entityimage entityimage) {
		
		try {
			commonDao.delete(entityimage);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}

	@Override
	public String addEntityImage(Entityimage entityimage) {
		
		try {
			entityimage.setUploadtime(new Date());
			commonDao.insert(entityimage);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
