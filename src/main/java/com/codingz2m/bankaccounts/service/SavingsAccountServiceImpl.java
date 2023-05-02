package com.codingz2m.bankaccounts.service;


import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingz2m.bankaccounts.dao.SavingsAccountDAO;
import com.codingz2m.bankaccounts.dto.SavingsAccountDto;
import com.codingz2m.bankaccounts.exception.SavingsAccountNotFoundException;
import com.codingz2m.bankaccounts.model.Card;
import com.codingz2m.bankaccounts.model.SavingsAccount;

@Service
@Transactional
public class SavingsAccountServiceImpl implements SavingsAccountService {

	private SavingsAccountDAO savingsAccountDAO;
	
	public SavingsAccountServiceImpl(SavingsAccountDAO savingsAccountDAO) {
		super();
		this.savingsAccountDAO = savingsAccountDAO;
	}

	@Override
	public SavingsAccount save(SavingsAccountDto savingsAccountDto) {
		return savingsAccountDAO.save(savingsAccountDto);
	}

	@Override
	public SavingsAccount getSavingsAccount(Long savingsAccountId) throws SavingsAccountNotFoundException {
		return savingsAccountDAO.getSavingsAccount(savingsAccountId);
	}
	
	@Override
	public List<SavingsAccount> getSavingsAccountByHolderName(String holderName) throws SavingsAccountNotFoundException {
		return savingsAccountDAO.getSavingsAccountByHolderName(holderName);
	}
	

	@Override
	public List<SavingsAccount> savingsAccountsByCurrentBalanceBetween(double minCurrentValue, double maxCurrentValue)throws SavingsAccountNotFoundException {
		return savingsAccountDAO.savingsAccountsByCurrentBalanceBetween(minCurrentValue, maxCurrentValue);
	}
	

	@Override
	public SavingsAccount update(SavingsAccountDto savingsAccountDto) throws SavingsAccountNotFoundException {
		return savingsAccountDAO.update(savingsAccountDto);
	}

	
	@Override
	public List<SavingsAccount> search(String holderName, Double currentBalance) throws SavingsAccountNotFoundException {	
		return savingsAccountDAO.search(holderName, currentBalance);
	}

	
	@Override
	public List<SavingsAccount> getHigherCurrentBalanceSavingsAccounts() throws SavingsAccountNotFoundException {
		return savingsAccountDAO.getHigherCurrentBalanceSavingsAccounts();
	}
		

	@Override
	public Iterable<SavingsAccount> getAllSavingsAccounts() {
		return savingsAccountDAO.getAllSavingsAccounts();
	}

	@Override
	public void deleteSavingsAccount(Long savingsAccountId) throws SavingsAccountNotFoundException {
		savingsAccountDAO.deleteSavingsAccount(savingsAccountId);
	}

	@Override
	public Set<Card> findAllCardsBySavingsAccountId(Long savingsAccountId) throws SavingsAccountNotFoundException {
		return savingsAccountDAO.findAllCardsBySavingsAccountId(savingsAccountId);
	}


}
