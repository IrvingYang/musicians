package com.qushop.musicains.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.musicains.entity.SiteEmail;
import com.qushop.musicains.service.SiteEmailService;
@Service
public class SiteEmailServiceImpl implements SiteEmailService {

	@Resource
	CommonDao<SiteEmail> commonDao;
	
	@Override
	public String saveSiteEmail(SiteEmail siteEmail) {
		try {
			commonDao.insert(siteEmail);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SiteEmail> siteEmailList() {
		
		List<SiteEmail> siteEmailsList = commonDao.findByHql("from SiteEmail where validflag=1");
		for (SiteEmail siteEmail : siteEmailsList) {
			
		}
		return siteEmailsList;
	}

	@Override
	public String deleteEmailList(String emailId) {
		
		List<SiteEmail> siteEmailsList = commonDao.findByHql("from SiteEmail where validflag=1");
		if(siteEmailsList!=null && siteEmailsList.size()>0){
			SiteEmail siteEmail = siteEmailsList.get(0);
			siteEmail.setValidflag((short)0);
			try {
				commonDao.update(siteEmail);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
		}
		return "emailId is null";
	}
}
