package com.qushop.musicains.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.musicains.entity.SiteEmail;
import com.qushop.musicains.service.SiteEmailService;


@Controller
@RequestMapping("/manage/siteEmail")
public class SiteEmailController {

	@Resource
	SiteEmailService siteEmailService;
	
	@RequestMapping("siteEmailList.do")
	public String siteEmailList(HttpServletRequest request)
	{
		List<SiteEmail> siteEmailsList = siteEmailService.siteEmailList();
		request.setAttribute("siteEmails", siteEmailsList);
		return "admin/siteEmailList";
	}
	
	@RequestMapping("deleteSiteEmail.do")
	@ResponseBody
	public Object deleteSiteEmail(String emailId,HttpServletRequest request)
	{
		String msg = siteEmailService.deleteEmailList(emailId);
		if("success".equals(msg)){
			return DwzUtil.opSuccess("操作成功", "siteemail");
		}
		return DwzUtil.opFailed("操作失败", msg);
	}
}
