package com.codingz2m.bankaccounts.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingz2m.bankaccounts.dto.CardDto;
import com.codingz2m.bankaccounts.dto.SavingsAccountDto;
import com.codingz2m.bankaccounts.exception.SavingsAccountNotFoundException;
import com.codingz2m.bankaccounts.model.Card;
import com.codingz2m.bankaccounts.model.SavingsAccount;
import com.codingz2m.bankaccounts.service.SavingsAccountService;
import com.codingz2m.bankaccounts.util.AppUtils;


@RestController
@RequestMapping("/savingsaccount")
public class SavingsAccountController {
	
	 @Autowired
	 private SavingsAccountService savingsAccountService;
	 private static final Logger logger = LoggerFactory.getLogger(SavingsAccountController.class);
	 
	 
	   @GetMapping("/welcome")
	   public String welcome() {
		   return "Bank Accouns REST Service with Spring Security 3.0";
	   }
	   
	   
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	  @PostMapping
	   public SavingsAccountDto createSavingsAccount (@RequestBody SavingsAccountDto savingsAccountDto) {
		  SavingsAccount savingsAccount = savingsAccountService.save(savingsAccountDto);
		  return AppUtils.entityToDto(savingsAccount);
	   }
	  
	  
	   @GetMapping("/{id}")
	   public SavingsAccountDto getSavingsAccount(@PathVariable("id") Long savingsAccountId) throws SavingsAccountNotFoundException {
		   logger.info("getSavingsAccount() inside SavingsAccountController is executed");
		   
		   SavingsAccount savingsAccount =  savingsAccountService.getSavingsAccount(savingsAccountId);
		   return AppUtils.entityToDto(savingsAccount);
	   }
	   
	   //REST End Point: localhost:8081/savingsaccount/byHolderName?holderName=jhon
	   @GetMapping("/byHolderName")
	   public List<SavingsAccountDto> getSavingsAccountByHolderName(@RequestParam("holderName") String holderName) throws SavingsAccountNotFoundException {
		   
		   logger.info("getSavingsAccountByHolderName() inside SavingsAccountController is executed");
		   List<SavingsAccountDto> savingsAccountsDto= new ArrayList<SavingsAccountDto>();
		 
		    List<SavingsAccount> savingsAccounts = savingsAccountService.getSavingsAccountByHolderName(holderName);
		   Iterator<SavingsAccount> iterator = savingsAccounts.iterator(); 
		   
		   while(iterator.hasNext()) {
			   SavingsAccount savingsAccount = (SavingsAccount) iterator.next();
			  SavingsAccountDto savingsAccountDto = AppUtils.entityToDto(savingsAccount);
			  savingsAccountsDto.add(savingsAccountDto);
		   }
		   return savingsAccountsDto;
	   }
	   
	  
	   
	// REST End Point: localhost:8081/savingsaccount/currentBalanceBetween?minCurrentvalue=9000&maxCurrentvalue=14000
	   @GetMapping("/currentBalanceBetween")
	   public List<SavingsAccountDto> savingsAccountsByCurrentBalanceBetween(
			   						@RequestParam("minCurrentvalue") double minCurrentValue,
	   								@RequestParam("maxCurrentvalue") double maxCurrentValue) throws SavingsAccountNotFoundException {
		   
		   List<SavingsAccountDto> savingsAccountsDto= new ArrayList<SavingsAccountDto>();
		   List<SavingsAccount> savingsAccounts =  savingsAccountService.savingsAccountsByCurrentBalanceBetween(minCurrentValue, maxCurrentValue);
		   
		   Iterator<SavingsAccount> iterator = savingsAccounts.iterator(); 
		   
		   while(iterator.hasNext()) {
			   SavingsAccount savingsAccount = (SavingsAccount) iterator.next();
			  SavingsAccountDto savingsAccountDto = AppUtils.entityToDto(savingsAccount);
			  savingsAccountsDto.add(savingsAccountDto);
		   }
		   return savingsAccountsDto;
	   }
	 
	   
	   @PutMapping
	   public SavingsAccountDto updateSavingsAccount (@RequestBody SavingsAccountDto savingsAccountDto) throws SavingsAccountNotFoundException {
		  SavingsAccount savingsAccount = savingsAccountService.update(savingsAccountDto);
		  return AppUtils.entityToDto(savingsAccount);
	   }
	   
	   
	   // Pass these parameters through "Params" in Postman
	   @GetMapping("/search")
	   public List<SavingsAccountDto> searchSavingsAccount(
			   					@RequestParam(required=false) String holderName,
			   					@RequestParam(required=false) Double currentBalance) throws SavingsAccountNotFoundException{
		   
		   List<SavingsAccountDto> savingsAccountsDto= new ArrayList<SavingsAccountDto>();
		   
		   List<SavingsAccount> savingsAccounts = savingsAccountService.search(holderName, currentBalance);
		   
		   Iterator<SavingsAccount> iterator = savingsAccounts.iterator(); 
		   
		   while(iterator.hasNext()) {
			   SavingsAccount savingsAccount = (SavingsAccount) iterator.next();
			  SavingsAccountDto savingsAccountDto = AppUtils.entityToDto(savingsAccount);
			  savingsAccountsDto.add(savingsAccountDto);
		   }
		   return savingsAccountsDto; 
	   }
	   
   
	  
	   // Obtaining Higher Current Balance Savings Accounts in Descending Order
	   @GetMapping("/orderByCurrentBalance")
	   public List<SavingsAccountDto> getHigherCurrentValueSavingsAccount() throws SavingsAccountNotFoundException {
		   
		   List<SavingsAccountDto> savingsAccountsDto= new ArrayList<SavingsAccountDto>();
		   
		   List<SavingsAccount> savingsAccounts = savingsAccountService.getHigherCurrentBalanceSavingsAccounts();
		   Iterator<SavingsAccount> iterator = savingsAccounts.iterator(); 
		   
		   while(iterator.hasNext()) {
			   SavingsAccount savingsAccount = (SavingsAccount) iterator.next();
			  SavingsAccountDto savingsAccountDto = AppUtils.entityToDto(savingsAccount);
			  savingsAccountsDto.add(savingsAccountDto);
		   }
		   return savingsAccountsDto; 
	   }
	   
	   
	   @GetMapping
	   public List<SavingsAccountDto> getAllSavingsAccounts() {
		   logger.info("getAllSavingsAccounts() inside SavingsAccountController is executed");
		   List<SavingsAccountDto> savingsAccountsDto= new ArrayList<SavingsAccountDto>();
		 
		  Iterable<SavingsAccount> savingsAccounts = savingsAccountService.getAllSavingsAccounts();
		   Iterator<SavingsAccount> iterator = savingsAccounts.iterator(); 
		   
		   while(iterator.hasNext()) {
			   SavingsAccount savingsAccount = (SavingsAccount) iterator.next();
			  SavingsAccountDto savingsAccountDto = AppUtils.entityToDto(savingsAccount);
			  savingsAccountsDto.add(savingsAccountDto);
		   }
		   return savingsAccountsDto;
	   }
	   
	   

	   
	   @DeleteMapping("/{id}")
	   public void deleteSavingsAccount(@PathVariable("id") Long savingsAccountId) throws SavingsAccountNotFoundException {
		   logger.info("deleteSavingsAccount() inside SavingsAccountController is executed");
		   savingsAccountService.deleteSavingsAccount(savingsAccountId);
	   }
	   
	   
	   @GetMapping("/cards/{id}")
	   public Set<CardDto> findAllCardsBySavingsAccountId(@PathVariable("id") Long savingsAccountId) throws SavingsAccountNotFoundException {
		  
		   Set<CardDto> cardDtos= new HashSet<CardDto>();
		 
		   Set<Card> cards = savingsAccountService.findAllCardsBySavingsAccountId(savingsAccountId);
		  
		  Iterator<Card> iterator = cards.iterator(); 
		   
		   while(iterator.hasNext()) {
			   Card card = (Card) iterator.next();
			  CardDto cardDto = AppUtils.entityToDto(card);
			  cardDtos.add(cardDto);
		   }
		   return cardDtos;
	   }
	   
	   
}
