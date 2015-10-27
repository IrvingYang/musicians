package com.qushop.common.schedule;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;




@Component
public class AdvOutofDate {

	@Resource
	SessionFactory mysessionfactory;
	
	
	@Scheduled(cron="0 0 23 * * ?")
	public void Work(){


		
		Session session = mysessionfactory.openSession();
		try {
			session.getTransaction().begin();
			session.createSQLQuery("update tb_ad_para set validflag = 0 where sysdate() > adendtime").executeUpdate();	
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		
	}
}
