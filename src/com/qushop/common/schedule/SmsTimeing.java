package com.qushop.common.schedule;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qushop.common.entity.Sms_box;
import com.qushop.common.sms.webservice.LinkWS;
import com.qushop.common.sms.webservice.LinkWSSoap;
import com.qushop.common.util.Constants;

/**
 * @author irving
 *
 */
/**
 * @author irving
 *
 */
@Component
public class SmsTimeing {

	@Resource
	SessionFactory sessionFactory;
	
	@Scheduled(cron="0/5 * * * * ?")
	public void work(){
		
//		Session session=sessionFactory.openSession();
//
//		try {
//			List<Sms_box> smsboxList = session.createSQLQuery("select * from tb_sms_box where sendtime = '' or sendtime is null").addEntity(Sms_box.class).setCacheable(true).list();
//			if(smsboxList.size()>0){
//				for (Sms_box code : smsboxList) {
//					String telephone = code.getTelephone();
//					String message = code.getMessage();
//					LinkWSSoap linkWSSoap = new LinkWS().getLinkWSSoap();
//					int sendCode = linkWSSoap.batchSend(Constants.SMS_USERNAME,Constants.SMS_PWD, telephone, message, "", "");
//					if(sendCode>=0){
//						code.setSendtime(new Date());
//						session.beginTransaction();
//						session.update(code);
//						session.getTransaction().commit();
//					}
//				}
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			session.close();
//		}
	}
	
	
	/**
	 * 18584091017
	 * @param args
	 */
	public static void main(String[] args) {
		LinkWSSoap linkWSSoap = new LinkWS().getLinkWSSoap();
		int sendCode = linkWSSoap.batchSend("LKSDK0003798","jkwqm814@", "18982237727", "验证码为：98989【彝家优品】", "", "");
		if(sendCode>=0){
		}
	}
}
