package com.qushop.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


public class ExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse arg1, Object arg2, Exception e) {

		e.printStackTrace();
		String uri = request.getRequestURL().toString();
		ModelAndView mv = null;
		//如果是后台路径
		if(uri.contains("manage/")){
			mv = new ModelAndView("exceptionInfo");
			mv.addObject("message", e.getMessage());
		}//其他情况
		else{
			mv = new ModelAndView("web/pageException");
			mv.addObject("message", e.getMessage());
		}
		return mv;
	}

}
