package com.gab.cashonline.cashBE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gab.cashonline.cashBE.dto.LoansPage;
import com.gab.cashonline.cashBE.service.LoanService;

@RestController
@RequestMapping("/loans")
@CrossOrigin
public class LoanController {

	@Autowired
	LoanService loanService;
	
	@GetMapping("")
	public LoansPage retrieveLoans(@RequestParam(required = true) int page, @RequestParam(required = true) int size, @RequestParam(name = "user_id", required = false) Long userId) {

		LoansPage retVal = loanService.getLoans(page, size, userId);
		
		return retVal;
	}
}
