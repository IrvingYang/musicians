package com.qushop.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.order.service.ChartService;

@Service
public class CharServiceimpl implements ChartService {
	
	@Resource
	CommonDao commonDao;
	

	public List<Object> getThisMonthFirstTypeChart(String providerId,String beginTime,String endTime,String frequency) {
		
		String sql = 
				"SELECT "+
				"sum(detail.totalamt) money, type.typeName "+
				"FROM"+
				" tb_order_detail detail,"+
				" tb_order_list orders,"+
				" tb_productType type "+
				" WHERE "+
				"orders.providerId = ? "+
				" AND orders.orderId = detail.orderId "+
				" AND orders.createTime >= "+(beginTime==null || "".equals(beginTime)?"(select date_add(curdate(), interval - day(curdate()) + 1 day)) ":"'"+beginTime+"'")+
				" AND orders.createTime <= "+(endTime==null || "".equals(endTime)?"(select last_day(curdate())) ":"'"+endTime+"'")+
				" AND type.productTypeId = SUBSTR(detail.productId,1,2) "+
				" GROUP BY"+
				"	SUBSTR(detail.productId,1,2);";
		
		return commonDao.findBySql(sql, null, providerId);
		
	}
	
	public List<Object> getSalesAreaChart(String providerId,String beginTime,String endTime,String frequency){
		
		String sql = 
				"SELECT "+
				"sum(orders.totalamt) money, city.cityName "+
				"FROM"+
				" tb_useraddress address, "+
				" tb_order_list orders, "+
				" tb_city city "+
				" WHERE "+
				"orders.providerId = ? "+
				" AND orders.createTime >= "+(beginTime==null || "".equals(beginTime)?"(select date_add(curdate(), interval - day(curdate()) + 1 day)) ":"'"+beginTime+"'")+
				" AND orders.createTime <= "+(endTime==null || "".equals(endTime)?"(select last_day(curdate())) ":"'"+endTime+"'")+
				" AND orders.userAddressId = address.userAddressId "+
				" AND orders.userId = address.userId "+
				" AND address.cityId = city.cityId "+
				"GROUP BY "+
				"	city.cityId;";

		return commonDao.findBySql(sql, null, providerId);
	}

	public List<Object> getProductSalesCountOfLineChart(String providerId,String beginTime,String endTime,String frequency) {
		// TODO Auto-generated method stub
		
		String dateFormat = "'%Y-%m-%d'";
		
		if("year".equals(frequency)){
			dateFormat = "'%Y'";
		}else if("month".equals(frequency)){
			dateFormat = "'%Y-%m'";
		}else if("day".equals(frequency)){
			dateFormat = "'%Y-%m-%d'";
		}
		
		String sql = "SELECT "+
		"count(list.orderId) count, "+
		"DATE_FORMAT(list.createTime,"+dateFormat+") "+
		"FROM "+
		"	tb_order_list list "+
		" WHERE list.providerId = ? ";
		if(beginTime!=null && !"".equals(beginTime))
		{
			sql+=" AND list.createTime>='"+beginTime+"' ";
		}
		else if(beginTime!=null && !"".equals(beginTime)){
			sql+=" AND list.createTime<='"+endTime+"' ";
		}
		sql+="GROUP BY "+
		"	DATE_FORMAT(list.createTime,"+dateFormat+")";
		
		return commonDao.findBySql(sql, null, providerId);
	}

	public List<Object> getProductSalesMoneyOfLineChart(String providerId,String beginTime,String endTime,String frequency){
		
		String dateFormat = "'%Y-%m-%d'";
		
		if("year".equals(frequency)){
			dateFormat = "'%Y'";
		}else if("month".equals(frequency)){
			dateFormat = "'%Y-%m'";
		}else if("day".equals(frequency)){
			dateFormat = "'%Y-%m-%d'";
		}
		
		String sql = "SELECT "+
		" sum(detail.quantity) count,"+
		" DATE_FORMAT(list.createTime,"+dateFormat+") "+
		" FROM "+
		"	tb_order_detail detail, "+
		"	tb_order_list list, "+
		"	tb_product_ext_shop product "+
		" WHERE"+
		"	product.productId = detail.productId "+
		" AND list.orderId = detail.orderId "+
		" AND list.providerId = ? ";
		if(beginTime!=null && !"".equals(beginTime))
		{
			sql+=" AND list.createTime>='"+beginTime+"' ";
		}
		else if(beginTime!=null && !"".equals(beginTime)){
			sql+=" AND list.createTime<='"+endTime+"' ";
			
		}
		sql+=" GROUP BY "+
		"	DATE_FORMAT(list.createTime,"+dateFormat+") ";

		return commonDao.findBySql(sql, null, providerId);
	}
	
}
