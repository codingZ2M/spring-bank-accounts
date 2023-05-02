package com.codingz2m.bankaccounts.dto;

import com.codingz2m.bankaccounts.model.SavingsAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

	private Long id;
    private Long cardNumber;
    private int cvv;
    private String expiryDate;
    private SavingsAccount savingsAccount;
}
