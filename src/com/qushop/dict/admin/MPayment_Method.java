package com.qushop.dict.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.Constants;
import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.dict.entity.Payment_Method;
import com.qushop.dict.service.Payment_MethodService;


@RequestMapping("/manage/payment")
@Controller
public class MPayment_Method {

	@Resource
	Payment_MethodService paymentMethodService;
	
	@RequestMapping("getAllPaymentMethod.do")
	public String getAllExpress(String action,HttpServletRequest request){
		
		List<Payment_Method> payment_MethodsList = paymentMethodService.getPayMentByMethod(0);
		request.setAttribute("paymentList", payment_MethodsList);
		return "admin/vpaymentList";
	}
	
	@RequestMapping("toEditPaymentMethod.do")
	public String toEditExpress(String action,String[] paymentway,HttpServletRequest request){
		
		if("add".equals(action)){
			request.setAttribute("action", "add");
		}
		else if("update".equals(action)){
			request.setAttribute("action", "update");
			List<Payment_Method> methodsList = paymentMethodService.getPayMentByMethod(1, paymentway[0]);
			if(methodsList!=null && methodsList.size()>0){
				request.setAttribute("payment", methodsList.get(0));
			}
		}

		return "admin/editvPayment";
	}
	
	@RequestMapping("saveOrUpdate.do")
	@ResponseBody
	public Object saveOrUpdate(String action,Payment_Method payment_Method,HttpServletRequest request){
		
		boolean bool = false;
		payment_Method.setValidflag((short)1);
		payment_Method.setLastUpdateTime(new Date());
		payment_Method.setOperid(PublicUtil.getOper(request).getOperId());
		if("add".equals(action)){
			bool = paymentMethodService.addPayMent(payment_Method);
		}
		else if("update".equals(action)){
			bool = paymentMethodService.updatePayMent(payment_Method);
		}
		if(bool){
			return DwzUtil.opSuccess("操作成功", "payment");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("deletepaymentMethod.do")
	@ResponseBody
	public Object deleteExpress(String paymentway,HttpServletRequest request){

		boolean bool = false;
		bool = paymentMethodService.deletePayMent(paymentway,request);

		if(bool){
			return DwzUtil.opSuccess("操作成功", "payment");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
	
}
