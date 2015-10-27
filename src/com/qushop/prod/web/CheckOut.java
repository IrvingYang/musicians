package com.qushop.prod.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/checkout.html")
public class CheckOut {

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView get(HttpServletRequest request,HttpServletResponse response){
		String checkouttype = request.getParameter("checkouttype");
		ModelAndView mv = null;
		if(checkouttype==null || "".equals(checkouttype) ){
			RedirectView redirectView = new RedirectView("/index.html");
			mv = new ModelAndView(redirectView);
		}
		else{
			mv = new ModelAndView("/web/checkout");
			mv.addObject("checkouttype", Integer.parseInt(checkouttype));
		}
		return mv;
	}
	
}
