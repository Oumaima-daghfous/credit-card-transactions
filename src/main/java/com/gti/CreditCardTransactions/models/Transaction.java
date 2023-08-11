package com.gti.CreditCardTransactions.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private Long id;

    private Double amount;

    private String merchant;

    private String status;

    private LocalDateTime date;
}
