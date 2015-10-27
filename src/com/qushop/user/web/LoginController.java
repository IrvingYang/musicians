package com.qushop.user.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.qushop.common.util.AuthorizationForUserException;
import com.qushop.user.entity.User;

@Controller
public class LoginController {
	
	@RequestMapping("/toLogin")
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping("")
	public void code(HttpServletResponse response){
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(HttpServletRequest request,User user) {
		request.setAttribute("loginAttribute", user);
		String referer = request.getHeader("Referer");
		request.setAttribute("returnUrl", referer);
		return "web/signup";
    }
}
