package com.qushop.alipay.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.alipay.config.AlipayConfig;
import com.qushop.alipay.util.AlipayNotify;
import com.qushop.alipay.util.AlipaySubmit;
import com.qushop.common.util.Constants;
import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.order.entity.Order_detail;
import com.qushop.order.entity.Order_list;
import com.qushop.order.service.Order_detailService;
import com.qushop.order.service.Order_listService;
import com.qushop.prod.entity.Product_ext_groupBuy;
import com.qushop.prod.service.Product_ext_groupBuyService;
import com.qushop.user.entity.Oper;
import com.qushop.user.entity.User_Ext_Personal;

@Controller
@RequestMapping("/alipay")
public class AlipayController {

	@Resource
	Order_listService order_listService;
	
	@Resource
	Order_detailService detailService;
	
	@Resource
	Product_ext_groupBuyService groupBuyService;
	
	/**
	 * 
	 * @param request httpRequest对象
	 * @param orderId 订单编号
	 * @param orderType 订单类型
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="pay.action",method=RequestMethod.POST)
	@ResponseBody
	public Object pay(HttpServletRequest request,HttpServletResponse response,String orderId,Integer orderType) 
			throws Exception{

		//支付类型
		String payment_type = "1";
		
		User_Ext_Personal personal = PublicUtil.getUserOfSession(request);
		
		//必填，不能修改
		//服务器异步通知页面路径
		String notify_url = "http://acyangjun.imwork.net:10331/ROOT/alipay/notifyUrl.action";
		//需http://格式的完整路径，不能加?id=123这类自定义参数

		//页面跳转同步通知页面路径
		String return_url = "http://acyangjun.imwork.net:10331/ROOT/alipay/returnUrl.action";
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
		
		List<Order_list> order_lists = order_listService.getOrder_listDetail(orderId, orderType==3?true:false);

		//商户网站订单系统中唯一订单号，必填
		//商户订单号
		String out_trade_no = order_lists.get(0).getOrderId();
		
		//订单名称
		String subject = order_lists.get(0).getUserAddress().getName()+"在乐星的购货订单";//new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
		//必填

		//付款金额
		String price = Double.toString(order_lists.get(0).getTotalamt());
		//必填

		//商品数量
		String quantity = "1";
		//必填，建议默认为1，不改变值，把一次交易看成是一次下订单而非购买一件商品
				
		//物流费用
		String logistics_fee = "0.00";
		//必填，即运费
				
		//物流类型
		String logistics_type = "EXPRESS";
		//必填，三个值可选：EXPRESS（快递）、POST（平邮）、EMS（EMS）
		
		//物流支付方式
		String logistics_payment = "SELLER_PAY";
		//必填，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）
		
		//订单描述
		String body = "暂无信息";//new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
		
		//商品展示地址
		String show_url = "#";//new String(request.getParameter("WIDshow_url").getBytes("ISO-8859-1"),"UTF-8");
		//需以http://开头的完整路径，如：http://www.商户网站.com/myorder.html

		//收货人姓名
		String receive_name = order_lists.get(0).getUserAddress().getName();
		//如：张三

		//收货人地址
		String receive_address =  order_lists.get(0).getUserAddress().getStateId()
				+order_lists.get(0).getUserAddress().getCityId()
				+order_lists.get(0).getUserAddress().getStreet();
		//如：XX省XXX市XXX区XXX路XXX小区XXX栋XXX单元XXX号

		//收货人邮编
		String receive_zip = order_lists.get(0).getUserAddress().getPostCode();
		//如：123456

		//收货人电话号码
		String receive_phone = order_lists.get(0).getUserAddress().getTelephone();
		//如：0571-88158090

		//收货人手机号码
		String receive_mobile =  order_lists.get(0).getUserAddress().getTelephone();
		//如：13312341234
		
		
		//////////////////////////////////////////////////////////////////////////////////
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_partner_trade_by_buyer");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_email", AlipayConfig.seller_email);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("price", price);
		sParaTemp.put("quantity", quantity);
		sParaTemp.put("logistics_fee", logistics_fee);
		sParaTemp.put("logistics_type", logistics_type);
		sParaTemp.put("logistics_payment", logistics_payment);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("receive_name", receive_name);
		sParaTemp.put("receive_address", receive_address);
		sParaTemp.put("receive_zip", receive_zip);
		sParaTemp.put("receive_phone", receive_phone);
		sParaTemp.put("receive_mobile", receive_mobile);
		System.out.println(sParaTemp);
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		return sHtmlText;
	}
	
	/**
	 * 
	 * @param request
	 * @param out_trade_no 商户订单号
	 * @param trade_no 支付宝交易号
	 * @param trade_status 交易状态
	 * 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("returnUrl.action")
	public Object returnUrl(HttpServletRequest request) throws UnsupportedEncodingException{
		
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		System.out.println(params);
		
		if(verify_result){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			request.setAttribute("img", "success");
			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("WAIT_SELLER_SEND_GOODS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
			}
			
			//该页面可做页面美工编辑
			return "web/payment_result";
			
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{
			request.setAttribute("img", "failed");
			//该页面可做页面美工编辑
			return "web/payment_result";
		}
	}
	
	/**
	 * 异步返回页
	 * @param request
	 * @param out_trade_no 商户订单号
	 * @param trade_no 支付宝交易号
	 * @param trade_status 交易状态
	 * 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("notifyUrl.action")
	@ResponseBody
	public String notifyUrl(HttpServletRequest request,String out_trade_no,String trade_no,String trade_status) throws UnsupportedEncodingException{
		
		System.out.println("Alipay Send Message ===============>>"+trade_status);
		System.out.println("orderId=============>>"+out_trade_no);
		
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		//String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		//String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		//String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		String orderIdStr = out_trade_no.substring(8, 9);
		Integer orderType = Integer.parseInt(orderIdStr);
		List<Order_list> ordersList = order_listService.getOrder_listDetail(out_trade_no, orderType==3?true:false);

		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			//List<Order_list> ordersList = order_listService.getOrder_listDetail(out_trade_no, orderType==3?true:false);
			Order_list orderList = ordersList.get(0);
			orderList.setUpdateTime(new Date());
			orderList.setLastUpdateTime(new Date());
			order_listService.updateOrderList(orderList);
			
			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if(trade_status.equals("WAIT_BUYER_PAY")){
				//该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				orderList.setStatus("01");
				System.out.println("修改支付宝交易号");
				orderList.setThirdpartorderno(trade_no);
				order_listService.updateOrderList(orderList);
				return "success";	//请不要修改或删除
					
			} else if(trade_status.equals("WAIT_SELLER_SEND_GOODS")){
				//该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				if(orderList.getOrderType()==2){
					List<Order_detail> details = detailService.getOrderdetailByMethod(1,null,orderList.getOrderId());
					if(details!=null && details.size()>0){
						Order_detail detail = details.get(0);
						String groupbuyId = detail.getProductId();
						List<Product_ext_groupBuy>  groupBuys = groupBuyService.getgroupBuyProductByMethod(5, groupbuyId);
						if(groupBuys!=null && groupBuys.size()>0){
							Product_ext_groupBuy groupBuy = groupBuys.get(0);
							//如果是预开团模式，改变订单状态为等待开团
							if(groupBuy.getGroupBuyType()==0){
								orderList.setStatus("04");
							}
							//如果是立即开团，状态改变为等待配送中
							else{
								orderList.setStatus("06");
							}
						}
					}
				}
				else{
					orderList.setStatus("06");
				}
				order_listService.updateOrderList(orderList);
				return "success";	//请不要修改或删除
				
			} else if(trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")){
				//该判断表示卖家已经发了货，但买家还没有做确认收货的操作
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				
				orderList.setStatus("07");
				order_listService.updateOrderList(orderList);
				return "success";	//请不要修改或删除
				
			} else if(trade_status.equals("TRADE_FINISHED")){
				//该判断表示买家已经确认收货，这笔交易完成
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				
				orderList.setStatus("08");
				order_listService.updateOrderList(orderList);
				return "success";	//请不要修改或删除
				
			}
			//退款处理中
			else if(trade_status.equals("WAIT_SELLER_AGREE")){
				
				orderList.setStatus("08");
				order_listService.updateOrderList(orderList);
				return "success";
			}
			//同意退款，等待退货
			else if(trade_status.equals("WAIT_BUYER_RETURN_GOODS")){
				
				orderList.setStatus("09");
				order_listService.updateOrderList(orderList);
				return "success";
			}
			//已经退货，商家等待收货 
			else if(trade_status.equals("WAIT_SELLER_CONFIRM_GOODS")){
				
				orderList.setStatus("10");
				order_listService.updateOrderList(orderList);
				return "success";
			}
			//不同意退货
			else if(trade_status.equals("SELLER_REFUSE_BUYER")){
				
				orderList.setStatus("11");
				order_listService.updateOrderList(orderList);
				return "success";
			}
			//退款成功
			else if(trade_status.equals("REFUND_SUCCESS")){
				
				orderList.setStatus("13");
				order_listService.updateOrderList(orderList);
				return "success";
			}
			else if(trade_status.equals("TRADE_CLOSED")){
				
				orderList.setStatus("02");
				order_listService.updateOrderList(orderList);
				return "success";
			}
			else if(trade_status.equals("TRADE_FINISHED")){
				
				orderList.setStatus("13");
				order_listService.updateOrderList(orderList);
				return "success";
			}
			else {
				return "success";	//请不要修改或删除
			}

		}else{//验证失败
			return "fail";
		}
	}
	
	@RequestMapping(value="sendGoods.action",method=RequestMethod.POST)
	@ResponseBody
	public Object sendGoods(String orderId,String waybill,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Oper oper = (Oper) request.getSession().getAttribute(Constants.OPER_USER);
		List<Order_list> order_listsList = order_listService.getOrder_listByMethod(oper,8, oper.getOperId(), orderId);

		if(order_listsList!=null && order_listsList.size()>0){
			Order_list orderList = order_listsList.get(0);
			if(!orderList.getProviderid().equals(oper.getProviderId())){
				return DwzUtil.opFailed("您不是该订单合作社操作员，不可操作", "");
			}
		}
		
		String expressid = request.getParameter("orderexpress.expressid");
		if(order_listsList!=null && order_listsList.size()>0){
			Order_list orderList = order_listsList.get(0);
			if(orderList.getPayofflineflag()==1){
				orderList.setStatus("07");
				orderList.setExpressvendor(expressid);
				orderList.setExpressnumber(waybill);
				order_listService.updateOrderList(orderList);
				return DwzUtil.opSuccess("操作成功", "productOrder");
			}
		}
		
		//支付宝交易号
		String trade_no = order_listService.getOrder_listDetail(orderId, false).get(0).getThirdpartorderno();//new String(request.getParameter("WIDtrade_no").getBytes("ISO-8859-1"),"UTF-8");
		System.out.println(trade_no);
		//必填

		//物流公司名称
		String logistics_name = request.getParameter("orderexpress.expressname");
		System.out.println(logistics_name);
		//必填

		//物流发货单号
		String invoice_no = waybill;
		//物流运输类型
		
		String transport_type = "EXPRESS";
		
		//////////////////////////////////////////////////////////////////////////////////
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "send_goods_confirm_by_platform");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("trade_no", trade_no);
		sParaTemp.put("logistics_name", logistics_name);
		sParaTemp.put("invoice_no", invoice_no);
		sParaTemp.put("transport_type", transport_type);
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);
		
		Document document = DocumentHelper.parseText(sHtmlText);
		Element rootElement = document.getRootElement();
		Element success = rootElement.element("is_success");
		System.out.println(sHtmlText);
		if("T".equals(success.getText().trim())){
			return DwzUtil.opSuccess("操作成功", "productOrder");
		}
		else{
			return DwzUtil.opFailed("发货不成功", "");
		}
		
	}
}
