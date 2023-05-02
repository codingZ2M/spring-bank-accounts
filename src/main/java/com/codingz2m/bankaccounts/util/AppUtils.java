package com.codingz2m.bankaccounts.util;

import org.springframework.beans.BeanUtils;

import com.codingz2m.bankaccounts.dto.CardDto;
import com.codingz2m.bankaccounts.dto.SavingsAccountDto;
import com.codingz2m.bankaccounts.model.Card;
import com.codingz2m.bankaccounts.model.SavingsAccount;


public class AppUtils {
	
	public static SavingsAccountDto entityToDto(SavingsAccount savingsAccount) {
		SavingsAccountDto savingsAccountDto = new  SavingsAccountDto();
		BeanUtils.copyProperties(savingsAccount, savingsAccountDto);
		return savingsAccountDto;
	}
	
	public static SavingsAccount dtoToEntity(SavingsAccountDto savingsAccountDto) {
		SavingsAccount savingsAccount = new  SavingsAccount();
		BeanUtils.copyProperties(savingsAccountDto, savingsAccount);
		return savingsAccount;
	}
	
	
	public static CardDto entityToDto(Card card) {
		CardDto cardDto = new  CardDto();
		BeanUtils.copyProperties(card, cardDto);
		return cardDto;
	}
	
	public static Card dtoToEntity(CardDto cardDto) {
		Card card = new  Card();
		BeanUtils.copyProperties(cardDto, card);
		return card;
	}
	
}
