package com.qushop.provider.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.provider.entity.Provider_Account;
import com.qushop.provider.service.ProviderAccountService;
import com.qushop.user.entity.Oper;


@Controller
@RequestMapping("/manage/provideraccount")
public class MProviderAccountController {

	
	@Resource
	ProviderAccountService accountService;
	
	
	@RequestMapping("getAllProviderAccount.do")
	public String getAllProviderAccount(HttpServletRequest request){
		
		Oper oper = PublicUtil.getOper(request);
		List<Provider_Account> providersList = accountService.getProviderAccountByMethod(0,oper);
		request.setAttribute("accountList", providersList);
		return "/admin/providerAccountList";
	}
	
	@RequestMapping("toEditProviderAccount.do")
	public String toEditProviderAccount(String[] keyword,HttpServletRequest request){
		
		// 合作社id and 账户number
		String[] keywords = keyword[0].split("and");
		Oper oper = PublicUtil.getOper(request);
		List<Provider_Account> accountList = accountService.getProviderAccountByMethod(1,oper, keywords[0],keywords[1]);
		if(accountList!=null && accountList.size()>0){
			request.setAttribute("providerAccount",accountList.get(0));
		}
		request.setAttribute("action", "update");
		return "/admin/editProviderAccount";
	}
	
	@ResponseBody
	@RequestMapping("addProviderAccount.do")
	public Object addProviderAccount(Provider_Account account,HttpServletRequest request){
		
		boolean bool = false;
		account.setLastUpdateTime(new Date());
		account.setOperid(PublicUtil.getOper(request).getOperId());
		account.setValidflag((short)1);
		String providerId = request.getParameter("providerAccount.providerId");
		account.setProviderId(providerId);
		bool = accountService.addProviderAccount(account);
		
		if(bool){
			return DwzUtil.opSuccess("操作成功", "provideraccount");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("toAddProviderAccount.do")
	public Object toAddProviderAccount(HttpServletRequest request){
		request.setAttribute("action", "add");
		return "/admin/editProviderAccount";
	}
	
	@ResponseBody
	@RequestMapping("updateProviderAccount.do")
	public Object updateProviderAccount(Provider_Account account,String uaccountNumber,HttpServletRequest request){

		boolean bool = false;
	
		String providerId = request.getParameter("providerAccount.providerId");
			bool = accountService.updateProviderAccount(account,providerId,uaccountNumber,request);
		if(bool){
			return DwzUtil.opSuccess("操作成功", "provideraccount");
		}
		return DwzUtil.opFailed("操作失败", "");
	}

	
	@ResponseBody
	@RequestMapping("deleteProviderAccount.do")
	public Object deleteProviderAccount(String[] keyword,HttpServletRequest request){

		boolean bool = false;
		for (String key : keyword) {
			String[] keywords = key.split("and");
			bool = accountService.deleteProviderAccount(keywords[0],keywords[1],request);
		}
		
		if(bool){
			return DwzUtil.opSuccess("操作成功", "provideraccount");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
}
