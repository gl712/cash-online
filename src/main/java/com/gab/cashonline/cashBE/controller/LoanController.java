package com.gab.cashonline.cashBE.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	LoanService loanService;
	
	@GetMapping("")
	public ResponseEntity<LoansPage> retrieveLoans(@RequestParam(required = true) int page, @RequestParam(required = true) int size, @RequestParam(name = "user_id", required = false) Long userId) {

		try {
			LoansPage loansPage = loanService.getLoans(page, size, userId);
			return ResponseEntity.ok().body(loansPage);
		} catch (Exception e) {
			logger.error(String.format("Exception trying to retrieve loans. "
					+ "page: %d, size: %d, userId: %s", page, size, userId), e);
			return ResponseEntity.badRequest().build();
		}
	}
}
