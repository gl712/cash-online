package com.gab.cashonline.cashBE.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gab.cashonline.cashBE.domain.Loan;

public interface LoanRepository extends PagingAndSortingRepository<Loan, Long>{
	public Page<Loan> findByUserId(long userId, Pageable pageable);
}
