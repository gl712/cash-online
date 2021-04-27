package com.gab.cashonline.cashBE.dto;

import java.io.Serializable;

public class PageInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int page;
	private int size;
	private long total;
	
	public PageInfo(int page, int size, long total) {
		this.page = page;
		this.size = size;
		this.total = total;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
}
