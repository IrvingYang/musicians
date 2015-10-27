package com.qushop.common.web;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.entity.Sms_box;
import com.qushop.common.service.SmsCheckOutService;
import com.qushop.user.service.UserService;


@Controller
@RequestMapping("/common/checkcode")
public class CheckCodeController {

	@Resource
	SmsCheckOutService checkOutService;
	
	@Resource
	UserService userService;
	
	@RequestMapping("sendRegisterCode.action")
	@ResponseBody
	public Object sendRegisterCode(HttpServletRequest request,String telephone){
		
		List userList = userService.getUserByMethod(10, telephone);
		if(userList!=null){
			return "exists";
		}
		
		if(telephone.equals("")){
			return "phonenull";
		}
		Date sendDate = (Date) request.getSession().getAttribute("sendRegisterCodeTime");
		if(sendDate!=null && (System.currentTimeMillis()-sendDate.getTime())<(1000*60)){
			return "opfrequent";
		}
		
		int code = new Random().nextInt(899999)+100000;
		request.getSession().setAttribute("registercode", code);
		request.getSession().setAttribute("sendRegisterCodeTime", new Date());
		Sms_box smsbox = new Sms_box();
		smsbox.setMessage("本次校验验码为:"+code+"【彝家优品】");
		smsbox.setSmstype("01");
		smsbox.setCreatetime(new Date());
		smsbox.setTelephone(telephone);
		smsbox.setValidflag((short)1);
		
		String msg ="failed";
		if(checkOutService.addCheckOutInfo(smsbox)){
			msg = "success";
		}
		return msg;
	}
	
	@RequestMapping("sendForgetCode.action")
	@ResponseBody
	public Object sendForgetCode(HttpServletRequest request,String telephone){
		
		List userList = userService.getUserByMethod(10, telephone);
		if(userList==null){
			return "notexists";
		}
		
		if(telephone.equals("")){
			return "phonenull";
		}
		Date sendDate = (Date) request.getSession().getAttribute("sendForgetCodeTime");
		if(sendDate!=null && (System.currentTimeMillis()-sendDate.getTime())<(1000*60)){
			return "opfrequent";
		}
		
		int code = new Random().nextInt(899999)+100000;
		request.getSession().setAttribute("forgetCode", code);
		request.getSession().setAttribute("sendForgetCodeTime", new Date());
		Sms_box smsbox = new Sms_box();
		smsbox.setMessage("您正在修改密码，若非本人操作可以忽视，本次校验验码为:"+code+"【彝家优品】");
		smsbox.setSmstype("01");
		smsbox.setCreatetime(new Date());
		smsbox.setTelephone(telephone);
		smsbox.setValidflag((short)1);
		
		String msg ="failed";
		if(checkOutService.addCheckOutInfo(smsbox)){
			msg = "success";
		}
		return msg;
	}
	
}
