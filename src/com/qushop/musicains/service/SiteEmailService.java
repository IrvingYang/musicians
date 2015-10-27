package com.qushop.musicains.service;

import java.util.List;

import com.qushop.musicains.entity.SiteEmail;

public interface SiteEmailService {

	public String saveSiteEmail(SiteEmail siteEmail);
	
	public List<SiteEmail> siteEmailList();
	
	public String deleteEmailList(String emailId);
	
}
