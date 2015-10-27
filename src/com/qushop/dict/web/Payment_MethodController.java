package com.qushop.dict.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.dict.entity.Payment_Method;
import com.qushop.dict.service.Payment_MethodService;



@Controller
@RequestMapping("order/payment_Method")
public class Payment_MethodController {

	@Resource
	Payment_MethodService payment_MethodService;
	
	@ResponseBody
	@RequestMapping("paymentMethods.action")
	public Object getAllPayment_Method(HttpServletRequest  request){
		
		List<Payment_Method> payment_MethodsList = payment_MethodService.getAllPaymentMethod();
		
		return payment_MethodsList;
	}
	
	@RequestMapping("getPaymentDetail.action")
	@ResponseBody
	public Object getPaymentDetail(String payment,HttpServletRequest request){
		
		List<Payment_Method> methods = payment_MethodService.getPayMentByMethod(1, payment);
		if(methods!=null && methods.size()>0){
			return methods.get(0);
		}
		return null;
	}
}
