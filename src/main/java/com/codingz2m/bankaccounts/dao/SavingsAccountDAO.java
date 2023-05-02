package com.codingz2m.bankaccounts.dao;

import java.util.List;
import java.util.Set;

import com.codingz2m.bankaccounts.dto.SavingsAccountDto;
import com.codingz2m.bankaccounts.exception.SavingsAccountNotFoundException;
import com.codingz2m.bankaccounts.model.Card;
import com.codingz2m.bankaccounts.model.SavingsAccount;

public interface SavingsAccountDAO {
	
	SavingsAccount save(SavingsAccountDto savingsAccountDto);
	
	SavingsAccount getSavingsAccount(Long savingsAccountId) throws SavingsAccountNotFoundException;
	
	List<SavingsAccount> getSavingsAccountByHolderName(String holderName) throws SavingsAccountNotFoundException;
	
	List<SavingsAccount> savingsAccountsByCurrentBalanceBetween(double minCurrentValue, double maxCurrentValue)throws SavingsAccountNotFoundException;
	
	SavingsAccount update(SavingsAccountDto savingsAccountDto) throws SavingsAccountNotFoundException;
	
	List<SavingsAccount> search(String holderName, Double currentBalance) throws SavingsAccountNotFoundException;
	
	List<SavingsAccount> getHigherCurrentBalanceSavingsAccounts()  throws SavingsAccountNotFoundException;;
	
	Iterable<SavingsAccount> getAllSavingsAccounts();
	
	void deleteSavingsAccount(Long savingsAccountId) throws SavingsAccountNotFoundException;
	
	public Set<Card> findAllCardsBySavingsAccountId(Long savingsAccountId)throws SavingsAccountNotFoundException;
}
