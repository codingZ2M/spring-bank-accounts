package com.codingz2m.bankaccounts.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.codingz2m.bankaccounts.dto.CardDto;
import com.codingz2m.bankaccounts.exception.CardNotFoundException;
import com.codingz2m.bankaccounts.exception.SavingsAccountNotFoundException;
import com.codingz2m.bankaccounts.service.CardService;
import com.codingz2m.bankaccounts.util.AppUtils;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardDto createCard(@RequestBody CardDto cardDto) throws SavingsAccountNotFoundException {
       return AppUtils.entityToDto(cardService.saveCard(cardDto));
    }

    
    @GetMapping("/{id}")
    public CardDto getCardById(@PathVariable Long id) throws CardNotFoundException {
        return AppUtils.entityToDto( cardService.getCardById(id));
    }
    
    @DeleteMapping("/{id}")
    public void deleteCardById(@PathVariable Long id) throws CardNotFoundException {
        cardService.deleteCard(id);
    }
}
