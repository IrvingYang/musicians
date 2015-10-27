package com.qushop.common.util;

public class PagePojo {

	private int pageno;
	private int pagesize;//2
	private int totalpage;
	private int totalcount;//1
	private int nextpage;
	private int prepage;
	private int currentpage;
	private String searchKey;
	
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	
	public String getSearchKey() {
		return searchKey;
	}
	
	
	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
		this.totalpage = (int)Math.ceil((double)this.totalcount/(double)this.pagesize);
	}
	public int getNextpage() {
		return nextpage;
	}
	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}
	public int getPrepage() {
		return prepage;
	}
	public void setPrepage(int prepage) {
		this.prepage = prepage;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	
}
