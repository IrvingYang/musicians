package com.qushop.common.web;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.qushop.alipay.config.AlipayConfig;
import com.qushop.common.util.Constants;

/**
 * 
 * @author xie
 * 
 */
@Component
public class InitApp implements BeanPostProcessor {

	private static Properties pro = new Properties();
	
	public static Properties getPro() {
		return pro;
	}
	static{
		
		try {
			pro.load(new InitApp().getClass().getClassLoader().getResourceAsStream("mycustom.properties"));
		} catch (IOException e) {
			System.out.println("出现错误了");
			e.printStackTrace();
		}
		
		Constants.SMS_USERNAME = pro.getProperty("sms_user");
		Constants.SMS_PWD = pro.getProperty("sms_pwd");
		AlipayConfig.partner = pro.getProperty("partner");
		AlipayConfig.key = pro.getProperty("key");
		Constants.SENDADDR= pro.getProperty("sendaddress");
		Constants.SENDUSER= pro.getProperty("senduser");
		Constants.SENDUSERPHONE= pro.getProperty("senduserphone");
		Constants.POSTCODE= pro.getProperty("postcode");
	}
	
	@Override
	public Object postProcessAfterInitialization(Object obj, String arg1)
			throws BeansException {
		
		return obj;
	}

	@Override
	public Object postProcessBeforeInitialization(Object obj, String arg1)
			throws BeansException {
		
		return obj;
	}

}
