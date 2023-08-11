package com.gti.CreditCardTransactions.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gti.CreditCardTransactions.models.Transaction;
import com.gti.CreditCardTransactions.models.TransactionFilter;
import com.gti.CreditCardTransactions.repositories.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	
	
    @Autowired 
    private TransactionRepository transactionRepo;

	@Override
	public Page<Transaction> findAllTransactions(TransactionFilter filter, Pageable pageable) {
		
		  // Vérification du filtre pour des valeurs non autorisées
	    if (filter.getAmount() != null && filter.getAmount() < 0) {
	        throw new IllegalArgumentException("Amount cannot be negative");
	    }
	    
		return transactionRepo.findAll(filter, pageable);
	}

	
	
	// findAll permet d afficher la liste des transactions sans filtre
	@Override
	public List<Transaction> findAll() {
	
		return transactionRepo.findAllTransactions();
	}
    
    
    
    
}
