package com.qushop.prod.pojo;

import java.util.ArrayList;
import java.util.List;

import com.qushop.user.entity.UserAddress;

public class PagingListPojo {
	
	//whole list size
	private int total;
	
	//paging list
	private List rows = new ArrayList();

	public PagingListPojo(List alist,int total) {
		rows=alist;
		this.total=total;
	}
	
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public List getRows() {
		return rows;
	}

	public int getTotal() {
		return total;
	}
}
