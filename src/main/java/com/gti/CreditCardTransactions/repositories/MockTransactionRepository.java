package com.gti.CreditCardTransactions.repositories;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gti.CreditCardTransactions.models.Transaction;
import com.gti.CreditCardTransactions.models.TransactionFilter;
import jakarta.annotation.PostConstruct;

@Repository
public class MockTransactionRepository implements TransactionRepository {

	
	private List<Transaction> transactions;
	
	@Autowired
    private ObjectMapper objectMapper = new ObjectMapper();
	

	  @PostConstruct
	    public void init() throws Exception {
	        // Désérialiser le contenu du fichier en une List<Transaction>
		    ClassPathResource transactionsResource = new ClassPathResource("transactionsMock.json");
            transactions = objectMapper.readValue(transactionsResource.getFile(), new TypeReference<List<Transaction>>() {});
	    }
   
	@Override
	public List<Transaction> findAllTransactions() {
		return transactions;
	}

	 @Override
	    public Page<Transaction> findAll(TransactionFilter filter, Pageable pageable) {
	        
	        Stream<Transaction> filteredTransaction = transactions.stream();

	        // Appliquer le filtre par amount, merchant, status
	        if(filter.getAmount() != null) {
	        	filteredTransaction = filteredTransaction.filter(t -> t.getAmount().equals(filter.getAmount()));
	        }
	        if(filter.getMerchant() != null && !filter.getMerchant().isEmpty()) {
	        	filteredTransaction = filteredTransaction.filter(t -> t.getMerchant().equals(filter.getMerchant()));
	        }
	        if(filter.getStatus() != null && !filter.getStatus().isEmpty()) {
	        	filteredTransaction = filteredTransaction.filter(t -> t.getStatus().equals(filter.getStatus()));
	        }

	  
	        // Appliquer le tri par amount, merchant, status
	        List<Sort.Order> listorders = pageable.getSort().toList();
	        for(Sort.Order order : listorders) {
	            switch(order.getProperty()) {
	                case "amount":
	                	filteredTransaction = order.isAscending() ? 
	                                     filteredTransaction.sorted(Comparator.comparing(Transaction::getAmount)) : 
	                                     filteredTransaction.sorted(Comparator.comparing(Transaction::getAmount).reversed());
	                    break;
	                case "merchant":
	                    filteredTransaction = order.isAscending() ? 
	                                     filteredTransaction.sorted(Comparator.comparing(Transaction::getMerchant)) : 
	                                     filteredTransaction.sorted(Comparator.comparing(Transaction::getMerchant).reversed());
	                    break; 
	                    
	                
	                case "status":
	                    filteredTransaction = order.isAscending() ? 
	                                     filteredTransaction.sorted(Comparator.comparing(Transaction::getStatus)) : 
	                                     filteredTransaction.sorted(Comparator.comparing(Transaction::getStatus).reversed());
	                    break; 
	            }
	        }

	        
	        // Appliquer la pagination
	        List<Transaction> pagedTransactions = filteredTransaction
	            .skip(pageable.getOffset())
	            .limit(pageable.getPageSize())
	            .collect(Collectors.toList());

	        return new PageImpl<>(pagedTransactions, pageable, transactions.size());
	    }
}
