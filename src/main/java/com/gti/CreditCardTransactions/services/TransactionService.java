package com.gti.CreditCardTransactions.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gti.CreditCardTransactions.models.Transaction;
import com.gti.CreditCardTransactions.models.TransactionFilter;



public interface TransactionService {

    public List<Transaction> findAll();
	
    public Page<Transaction> findAllTransactions(TransactionFilter filter, Pageable pageable);
}
