package com.gti.CreditCardTransactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gti.CreditCardTransactions.controller.TransactionController;
import com.gti.CreditCardTransactions.models.Transaction;
import com.gti.CreditCardTransactions.services.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc(addFilters = false)

public class TransactionTestController {

	
	@Autowired
    private MockMvc mockMvc;
	
	
	@MockBean
	private TransactionService transactionService;
	
	private List<Transaction> transactions;
	
	   @BeforeEach
	    public void setup() throws StreamReadException, DatabindException, IOException {
	      
		   transactions = Arrays.asList(
				    new Transaction(1L, 100.0, "Amazon", "approved", LocalDateTime.now()),
				    new Transaction(2L, 200.0, "Best Buy", "refused", LocalDateTime.now()),
				    new Transaction(3L, 300.0, "Best Buy", "refused", LocalDateTime.now())
				);
	    }
	
	
	
	@Test
	public void getTransactionTest() throws Exception {
		
		
		
		// Préparation des données mockées		
		Pageable mockPageable = PageRequest.of(0, 10);
		Page<Transaction> mockPage = new PageImpl<>(transactions, mockPageable, transactions.size());
		
		
		
        Mockito.when(transactionService.findAllTransactions(Mockito.any(), Mockito.any())).thenReturn(mockPage);

        // Execute & Verify
        mockMvc.perform(MockMvcRequestBuilders.get("/api/transactions")) // tester le endspoint
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists());
              

        // Cleanup: Reset mock behavior 
        Mockito.reset(transactionService);
		
		
	}
	
	
	
}
