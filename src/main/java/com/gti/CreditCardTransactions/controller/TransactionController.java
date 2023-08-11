package com.gti.CreditCardTransactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gti.CreditCardTransactions.models.Transaction;
import com.gti.CreditCardTransactions.models.TransactionFilter;
import com.gti.CreditCardTransactions.services.TransactionService;


@RestController
@RequestMapping("api/transactions")
public class TransactionController {

	
	
	
	 @Autowired
	 private TransactionService transactionService;
	 
	 
	 

	 
	 // getTransactions permet de répertorier les transactions avec filtrage et pagination
	   @GetMapping
	    public ResponseEntity<Page<Transaction>> getTransactions(
	            TransactionFilter filter,
	            @RequestParam(defaultValue = "0") int page, 
	            @RequestParam(defaultValue = "10") int size,
	            @RequestParam(defaultValue = "id") String sortBy) {

	        Pageable pageable = PageRequest.of(page, size,Sort.by(sortBy));
	        
	        Page<Transaction> result = transactionService.findAllTransactions(filter, pageable);
	        return ResponseEntity.ok(result);
	    }
	 
	 
}
