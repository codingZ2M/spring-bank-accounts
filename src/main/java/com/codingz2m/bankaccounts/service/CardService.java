package com.codingz2m.bankaccounts.service;

import com.codingz2m.bankaccounts.dto.CardDto;
import com.codingz2m.bankaccounts.exception.CardNotFoundException;
import com.codingz2m.bankaccounts.exception.SavingsAccountNotFoundException;
import com.codingz2m.bankaccounts.model.Card;

public interface CardService {

	 Card saveCard(CardDto cardDto) throws SavingsAccountNotFoundException ;
	 Card getCardById(Long id) throws CardNotFoundException;
	 void deleteCard(Long id) throws CardNotFoundException;
}
