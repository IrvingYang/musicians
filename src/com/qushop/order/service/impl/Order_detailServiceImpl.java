package com.qushop.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.order.entity.Order_detail;
import com.qushop.order.service.Order_detailService;
import com.qushop.user.entity.Oper;

@Service
public class Order_detailServiceImpl implements Order_detailService {

	@Resource
	CommonDao commonDao;
	
	@Override
	public List<Order_detail> getOrderdetailByMethod(int type, Oper oper,
			String... params) {
		List<Order_detail> detailsList = new ArrayList<Order_detail>();
		switch (type) {
		case 0:
			detailsList = commonDao.findByHql("from Order_detail where productId=? and orderType=?", params[0], Short.parseShort(params[1]));
			break;
		case 1:
			detailsList = commonDao.findByHql("from Order_detail where orderId=?", params[0]);
			break;
		case 2:
			detailsList = commonDao.findByHql("from Order_detail where orderId=? and validflag=1", params[0]);
			break;

		default:
			break;
		}
		
		return detailsList;
	}

	@Override
	public boolean deleteOrderDetail(String orderIds) {

		String dsql = "update tb_order_detail set validflag=0 where orderId in ("+orderIds+")";
		return commonDao.executeBySql(dsql);
	}

}
