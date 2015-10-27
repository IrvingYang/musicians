package com.qushop.dict.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qushop.dict.entity.Express_vendor;
import com.qushop.dict.entity.Payment_Method;

public interface Payment_MethodService {

	public List<Payment_Method> getAllPaymentMethod();
	
	/**
	 * 
	 * @param type 0查询所有支付方式 1查询支付方式详情 2查询单个包含validflag=0
	 * @param params
	 * @return
	 */
	public List<Payment_Method> getPayMentByMethod(int type,String...params);
	
	public boolean addPayMent(Payment_Method payment);
	
	public boolean deletePayMent(String keyword,HttpServletRequest request);
	
	public boolean updatePayMent(Payment_Method payment,String paymentway,HttpServletRequest request);
	
	public boolean updatePayMent(Payment_Method payment);
	
}
