package com.codingz2m.bankaccounts.dao;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.codingz2m.bankaccounts.dto.CardDto;
import com.codingz2m.bankaccounts.exception.CardNotFoundException;
import com.codingz2m.bankaccounts.exception.SavingsAccountNotFoundException;
import com.codingz2m.bankaccounts.model.Card;
import com.codingz2m.bankaccounts.model.SavingsAccount;
import com.codingz2m.bankaccounts.repository.CardRepository;
import com.codingz2m.bankaccounts.service.SavingsAccountService;
import com.codingz2m.bankaccounts.util.AppUtils;

@Component
public class CardDAOImpl implements CardDAO{

	 private final CardRepository cardRepository;
	 private final SavingsAccountService savingsAccountService;

	public CardDAOImpl(CardRepository cardRepository, SavingsAccountService savingsAccountService) {
		super();
		this.cardRepository = cardRepository;
		this.savingsAccountService = savingsAccountService;
	}

	@Override
	public Card saveCard(CardDto cardDto) throws SavingsAccountNotFoundException {
	    // link the card with its savings account
		Card card = AppUtils.dtoToEntity(cardDto);
        SavingsAccount savingsAccount = savingsAccountService.getSavingsAccount(card.getSavingsAccount().getId());
        card.setSavingsAccount(savingsAccount);
        return cardRepository.save(card);
	}
	
	@Override
	public Card getCardById(Long id) throws CardNotFoundException {
		  Optional<Card> optional = cardRepository.findById(id);
		  
		  if ( !optional.isPresent() ) {
				throw new CardNotFoundException("Card Not Found for the ID: " + id);
			}
			else {
				return optional.get();
			}
	}

	@Override
	public void deleteCard(Long id) throws CardNotFoundException {
		Card card =  getCardById(id);
		 if (card== null) {
			 throw new CardNotFoundException("Card Not Found for the ID: " + id);
	        }
			else {
			cardRepository.deleteById(id);
			}
	}

}
