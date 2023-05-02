package com.codingz2m.bankaccounts.dao;

import com.codingz2m.bankaccounts.dto.CardDto;
import com.codingz2m.bankaccounts.exception.CardNotFoundException;
import com.codingz2m.bankaccounts.exception.SavingsAccountNotFoundException;
import com.codingz2m.bankaccounts.model.Card;

public interface CardDAO {
	
	Card saveCard(CardDto cardDto) throws SavingsAccountNotFoundException ;
	Card getCardById(Long id) throws CardNotFoundException;
    void deleteCard(Long id) throws CardNotFoundException;
}
