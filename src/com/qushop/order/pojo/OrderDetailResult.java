package com.qushop.order.pojo;

import java.util.ArrayList;
import java.util.List;


public class OrderDetailResult<T> {
	private List<T> data=new ArrayList<T>();
	
	public void setData(List<T> data) {
		this.data = data;
	}
	
	public List<T> getData() {
		return data;
	}

}
