package com.gti.CreditCardTransactions.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.gti.CreditCardTransactions.models.Transaction;
import com.gti.CreditCardTransactions.models.TransactionFilter;


public interface TransactionRepository {

	
	List<Transaction> findAllTransactions();
    Page<Transaction> findAll(TransactionFilter filter, Pageable pageable);
	
}
