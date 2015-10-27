package com.qushop.dict.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.PublicUtil;
import com.qushop.dict.entity.Payment_Method;
import com.qushop.dict.service.Payment_MethodService;


@Service
public class Payment_MethodServiceImpl implements Payment_MethodService {

	@Resource
	CommonDao commonDao;

	@Override
	public List<Payment_Method> getAllPaymentMethod() {
		
		List<Payment_Method> methodsList = commonDao.findByHql("from Payment_Method where validflag=1");
		
		return methodsList;
	}

	@Override
	public List<Payment_Method> getPayMentByMethod(int type, String... params) {
		
		List<Payment_Method> methodsList = null;
		switch (type) {
		case 0:
			methodsList = commonDao.findByHql("from Payment_Method where validflag=1");
			break;
		case 1:
			methodsList = commonDao.findByHql("from Payment_Method where paymentway=? and validflag=1",params[0]);
			break;
		case 2:
			methodsList = commonDao.findByHql("from Payment_Method where paymentway=?",params[0]);
			break;
		}
		return methodsList;
	}

	@Override
	public boolean addPayMent(Payment_Method payment) {

		List<Payment_Method> list = commonDao.findPagingByHql("from Payment_Method  order by paymentway desc",0,1);
		if(list.size()>0){
			String paymentway = list.get(0).getPaymentway();
			Integer paynum = Integer.parseInt(paymentway);
			paynum+=1;
			String endNum="";
			for (int i = (paynum+"").length(); i < 2; i++) {
				endNum+="0";
			}
			endNum+=paynum;
			payment.setPaymentway(endNum);
		}else{
			payment.setPaymentway("01");
		}
		try {
			commonDao.insert(payment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deletePayMent(String keyword,HttpServletRequest request) {
		
		try {
			boolean bool = false;
			String sql = "update tb_payment_method set validflag=0,lastUpdateTime=?,operid=? where paymentway in ("+keyword+")";
			bool = commonDao.executeBySql(sql,new Date(),PublicUtil.getOper(request).getOperId());
			return bool;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePayMent(Payment_Method payment,String paymentway,HttpServletRequest request) {

		try {
			String sql = "update tb_payment_method set paymentway=?,instname=?, "
					+ "recvbranchname=?, recvbranchnumber=?, recvaccountname=?, "
					+ "recvaccountnumber=?, merchantId=?,swiftname=?,swiftnumber=?,"
					+ "imagepath=?,lastUpdateTime=?,operid=? where paymentway=? and validflag=1";
			commonDao.executeBySql(sql,payment.getPaymentway(),payment.getInstname(),payment.getRecvbranchname()
					,payment.getRecvbranchnumber(),payment.getRecvaccountname(),payment.getRecvaccountnumber()
					,payment.getMerchantId(),payment.getSwiftname(),payment.getSwiftnumber(),
					payment.getImgpath(),new Date(),PublicUtil.getOper(request).getOperId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePayMent(Payment_Method payment) {
		
		try {
			commonDao.update(payment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
