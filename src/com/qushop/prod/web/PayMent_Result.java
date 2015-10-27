package com.qushop.prod.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/payment_result.html")
public class PayMent_Result {

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView get(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = null;
		mv = new ModelAndView("/web/payment_result");
		return mv;
	}
}
