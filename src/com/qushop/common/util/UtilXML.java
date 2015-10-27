package com.qushop.common.util;

import java.io.ByteArrayInputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class UtilXML {

	public static void parsingXML(String xmlStr) throws Exception {
		
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new ByteArrayInputStream(xmlStr.getBytes()));
		Element rootElement = document.getRootElement();
		Element success = rootElement.element("is_success");
		Element tradeBaseRoot = rootElement.element("tradeBase");
		
	}

	public static void main(String[] args) throws Exception {
		String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<alipay>"
				+ "<is_success>T</is_success>"
				+ "<request>"
				+ "<param name=\"trade_no\">2015042100001000700051671580</param>"
				+ "<param name=\"_input_charset\">utf-8</param>"
				+ "<param name=\"transport_type\">EXPRESS</param>"
				+ "<param name=\"service\">send_goods_confirm_by_platform</param>"
				+ "<param name=\"partner\">2088911123176702</param>"
				+ "<param name=\"invoice_no\">45678904674576</param>"
				+ "<param name=\"logistics_name\">顺丰</param>"
				+ "</request>"
				+ "<response>"
				+ "<tradeBase>"
				+ "<buyer_account>20883124199007000156</buyer_account>"
				+ "<buyer_actions>[REFUND, CONFIRM_GOODS]</buyer_actions>"
				+ "<buyer_login_email>18584091017</buyer_login_email>"
				+ "<buyer_type>PRIVATE_ACCOUNT</buyer_type>"
				+ "<buyer_user_id>2088312419900700</buyer_user_id>"
				+ "<channel>interface/escrow</channel>"
				+ "<create_time>2015-04-21 10:43:39</create_time>"
				+ "<currency>156</currency>"
				+ "<gathering_type>1</gathering_type>"
				+ "<last_modified_time>2015-04-21 10:49:44</last_modified_time>"
				+ "<operator_role>B</operator_role>"
				+ "<out_trade_no>20150417010001705527</out_trade_no>"
				+ "<partner_id>2088911123176702</partner_id>"
				+ "<seller_account>20889111231767020156</seller_account>"
				+ "<seller_actions>[EXTEND_TIMEOUT]</seller_actions>"
				+ "<seller_login_email>2858006018@qq.com</seller_login_email>"
				+ "<seller_type>CORPORATE_ACCOUNT</seller_type>"
				+ "<seller_user_id>2088911123176702</seller_user_id>"
				+ "<service_fee>0.00</service_fee>"
				+ "<service_fee_ratio>0.0</service_fee_ratio>"
				+ "<stop_timeout>F</stop_timeout>"
				+ "<total_fee>0.01</total_fee>"
				+ "<trade_from>INST_PARTNER</trade_from>"
				+ "<trade_no>2015042100001000700051671580</trade_no>"
				+ "<trade_status>WAIT_BUYER_CONFIRM_GOODS</trade_status>"
				+ "<trade_type>S</trade_type>"
				+ "</tradeBase>"
				+ "</response>"
				+ "<sign>d9e3581daefee38a8868d65590337a65</sign>"
				+ "<sign_type>MD5</sign_type>"
				+ "</alipay>";
		UtilXML.parsingXML(xmlStr);
	}
}
