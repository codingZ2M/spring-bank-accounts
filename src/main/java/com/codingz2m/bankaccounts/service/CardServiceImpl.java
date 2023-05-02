package com.codingz2m.bankaccounts.service;


import org.springframework.stereotype.Service;

import com.codingz2m.bankaccounts.dao.CardDAO;
import com.codingz2m.bankaccounts.dto.CardDto;
import com.codingz2m.bankaccounts.exception.CardNotFoundException;
import com.codingz2m.bankaccounts.exception.SavingsAccountNotFoundException;
import com.codingz2m.bankaccounts.model.Card;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    private final CardDAO cardDAO;

    public CardServiceImpl(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    @Override
    public Card saveCard(CardDto cardDto) throws SavingsAccountNotFoundException {
   
       return cardDAO.saveCard(cardDto);
    }
    
    @Override
    public Card getCardById(Long id) throws CardNotFoundException{
    	return cardDAO.getCardById(id);
    }


    @Override
    public void deleteCard(Long id) throws CardNotFoundException{
        cardDAO.deleteCard(id);
    }
}


