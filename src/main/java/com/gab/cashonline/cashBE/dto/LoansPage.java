package com.gab.cashonline.cashBE.dto;

import java.io.Serializable;
import java.util.List;

import com.gab.cashonline.cashBE.domain.Loan;

public class LoansPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<Loan> items;
	PageInfo paging;
	
	public List<Loan> getItems() {
		return items;
	}
	public void setItems(List<Loan> items) {
		this.items = items;
	}
	public PageInfo getPaging() {
		return paging;
	}
	public void setPaging(PageInfo paging) {
		this.paging = paging;
	}

}
