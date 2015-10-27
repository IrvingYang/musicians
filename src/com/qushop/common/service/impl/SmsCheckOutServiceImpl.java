package com.qushop.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.entity.Sms_box;
import com.qushop.common.service.SmsCheckOutService;


@Service
public class SmsCheckOutServiceImpl implements SmsCheckOutService {
	
	@Resource
	CommonDao<Sms_box> commonDao;
	
	@Override
	public List<Sms_box> getSmsCheckOutByMethod(int type, String...params) {
		
		List<Sms_box> checkcodesList = new ArrayList<Sms_box>();
		
		switch (type) {
		
		case 1:
			checkcodesList = commonDao.findPagingByHql("from Sms_box where telephone=? and validflag=1 order by createtime desc", 0, 1, params[0]);
			break;

		default:
			break;
		}
		
		return checkcodesList;
	}

	@Override
	public boolean updateSmsCheckOut(Sms_box checkcode) {
		
		try {
			commonDao.update(checkcode);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean addCheckOutInfo(Sms_box checkcode) {

		try {
			commonDao.insert(checkcode);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
