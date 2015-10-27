package com.qushop.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/login")
public class CheckLoginController {
//	@Resource
//	private UserMapperDao dao;
//	public void setDao(UserMapperDao dao) {
//		this.dao = dao;
//	}

//	@RequestMapping("/login")
//	public String login(
//		AdminInfo admin,Model model,
//		HttpSession session){
//		//检查验证码
//		String scode = (String) 
//			session.getAttribute("scode");
//		System.out.println("用户输入:"+admin.getCode());
//		System.out.println("Session:"+scode);
//		if(!admin.getCode().equals(scode)){
//			//验证码错误
//			model.addAttribute("code_error", "验证码错误");
//			return "login";
//		}
//		//检查账号和密码
//		AdminInfo info = 
//			dao.findByAdminCodeAndPwd(admin);
//		if(info != null){//有记录
//			session.setAttribute(
//				"user", info.getAdmin_code());
//			return "index";//进入index.jsp
//		}else{//无记录,错误
//			model.addAttribute(
//				"error", "用户名或密码不正确");
//			return "login";//进入login.jsp
//		}
//	}
}


