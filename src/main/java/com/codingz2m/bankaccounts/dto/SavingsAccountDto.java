package com.codingz2m.bankaccounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingsAccountDto {

	private Long id;
	private String holderName;
	private String accountType;
	private double averageQuarterlyBalance;
	private double residentAccountsDebitCardLimit;
	private Double currentBalance;
	private String phone;
	private String email;
}
