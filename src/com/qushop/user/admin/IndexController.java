package com.qushop.user.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qushop.prod.service.ProductTypeService;


@Controller
@RequestMapping("/manage/index.do")
public class IndexController {

	
	@RequestMapping(method=RequestMethod.GET)
	public String get(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "/admin/index";
	}
}
