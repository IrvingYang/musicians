package com.qushop.common.schedule;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qushop.common.entity.Click_log_statistic;
import com.qushop.common.util.CachedManager;
import com.qushop.common.util.Constants;
import com.qushop.prod.entity.Product_ext_hotProduct;
import com.qushop.prod.entity.Product_ext_shop;


@Component
public class ClickStatTask {

	@Resource
	SessionFactory mysessionfactory;
	
	
	@Scheduled(cron="0 0 23 * * ?")
	public void Work(){
		//先读取memcache的点击
		CachedManager current = CachedManager.getInstance();				
		Map<String,String> map = current.getAllValueOfMap();
		int count = 0;
		
		Session session = mysessionfactory.openSession();
		try {
			session.getTransaction().begin();
//			session.createSQLQuery("Delete from tb_product_ext_hotproduct where 1=1").executeUpdate();
			for (Iterator<String> iter = map.keySet().iterator();iter.hasNext();) {	
				
				String key = iter.next()+"";
				String value = map.get(key)+"";
				Click_log_statistic clicklog = new Click_log_statistic();
				clicklog.setClicktype("01");
				clicklog.setCount(Integer.valueOf(value));
				clicklog.setEntityid(key);
				clicklog.setStattime(new Date());
				session.save(clicklog);
				
				//满足热门条件
				List<Product_ext_shop> list = session.createQuery("from Product_ext_shop where productId=? and validflag<>1").setParameter(0, key).list();
				if(list!=null && list.size()>0){
					continue;
				}
				if(clicklog.getCount() > Constants.HOT_PROD_BOTTOM){
					
//					current.delete(key);
					Product_ext_hotProduct hotprod = new Product_ext_hotProduct();
					hotprod.setLastUpdateTime(new Date());
					hotprod.setProductId(clicklog.getEntityid());
					hotprod.setStaticTime(new Date());
					hotprod.setOperid(Constants.SHOP_SYS_OPER_ID);
					hotprod.setValidflag((short)1);
					session.save(hotprod);
				}
//				count++;
//				if(count > Constants.MAX_COMMIT_ITEM_NUMBER){
//					session.getTransaction().commit();
//					session.getTransaction().begin();
//				}
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			CachedManager.flushAll();
		}
		
	}
}
