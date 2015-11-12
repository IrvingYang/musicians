package com.qushop.order.pojo;

import java.util.ArrayList;
import java.util.List;

import com.qushop.order.entity.Order_list;

public class OrderListResult {
	
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<Order_list> data=new ArrayList<Order_list>();
	
	public OrderListResult(int total, List<Order_list> rows) {
		this.recordsTotal = total;
		this.data = rows;
	}

	public List<Order_list> getRows() {
		return data;
	}
	
	public int getTotal() {
		return recordsTotal;
	}
	
	public void setData(List<Order_list> data) {
		this.data = data;
	}
	
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	
	public List<Order_list> getData() {
		return data;
	}
	
	public void setDraw(int draw) {
		this.draw = draw;
	}
	
	public int getDraw() {
		return draw;
	}
}
