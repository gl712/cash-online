package com.gab.cashonline.cashBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gab.cashonline.cashBE.domain.Loan;
import com.gab.cashonline.cashBE.dto.LoansPage;
import com.gab.cashonline.cashBE.dto.PageInfo;
import com.gab.cashonline.cashBE.repository.LoanRepository;

@Service
public class LoanService {

	@Autowired
	LoanRepository loanRepository;
	
	public LoansPage getLoans(int page, int size, Long userId) {
		
		page--;
		
		Pageable paging = PageRequest.of(page, size, Sort.by("id"));
		Page<Loan> loans;
		if(userId==null) {
			loans = loanRepository.findAll(paging);
		}else {
			loans = loanRepository.findByUserId(userId, paging);
		}
		LoansPage retVal = new LoansPage();
		retVal.setItems(loans.getContent());
		retVal.setPaging(new PageInfo(++page, size, loans.getTotalPages()));
		
		return retVal;
	}

}
