package com.qushop.common.schedule;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;




@Component
public class CompProdGrade {

	@Resource
	SessionFactory mysessionfactory;
	
	
	@Scheduled(cron="0 0 23 * * ?")
	public void Work(){


		
		Session session = mysessionfactory.openSession();
		try {
			session.getTransaction().begin();
			List<Object[]> objlist = new ArrayList();

			objlist = session.createSQLQuery("select count(*) count, sum(rate) sum, productId from tb_productReview where  commentsDate  > curdate() - 365 group by productId").list();
			
			for(Object[] obj: objlist){
				long count = 0;
				long total = 0;
				int  grade = 0;
				String productid;
				count = (Long.parseLong(obj[0]+""));
				total = (Long.parseLong(obj[1]+""));
				grade = Integer.parseInt((total/count)+"");
				productid = obj[2]+"";				
				session.createSQLQuery("update tb_product set productGrade=? where productId = ?").setParameter(0, grade).setParameter(1, productid).executeUpdate();
			}
					
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		
	}
}
