package com.qushop.musicains.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qushop.common.util.DateUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.common.util.UtilDate;
import com.qushop.musicains.entity.SiteEmail;
import com.qushop.musicains.service.SiteEmailService;

@Controller
@RequestMapping("/web/site")
public class SiteEmailWeb {

	@Resource
	SiteEmailService emailService;
	
	@RequestMapping("addSite.shtml")
	public String addSite(SiteEmail siteEmail, HttpServletRequest request){
		
		siteEmail.setEmailId(UtilDate.getNowDateTimeNo_()+PublicUtil.getUserOfSession(request).getUserId());
		siteEmail.setUserId(PublicUtil.getUserOfSession(request).getUserId());
		siteEmail.setValidflag((short)1);
		String msgStr = emailService.saveSiteEmail(siteEmail);
		if("success".equals(msgStr)){
			return "web/repoOk";
		}
		return "redirect:/";
	}
}
