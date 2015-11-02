package com.qushop.order.pojo;

import java.util.ArrayList;
import java.util.List;

import com.qushop.order.entity.Order_list;

public class OrderListResult {
	
	private int total;
	private List<Order_list> rows=new ArrayList<Order_list>();
	
	public OrderListResult(int total, List<Order_list> rows) {
		this.total = total;
		this.rows = rows;
	}

	public List<Order_list> getRows() {
		return rows;
	}
	
	public int getTotal() {
		return total;
	}
}
