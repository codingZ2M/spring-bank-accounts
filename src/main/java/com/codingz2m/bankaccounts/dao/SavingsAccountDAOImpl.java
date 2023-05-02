package com.codingz2m.bankaccounts.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.codingz2m.bankaccounts.dto.SavingsAccountDto;
import com.codingz2m.bankaccounts.exception.SavingsAccountNotFoundException;
import com.codingz2m.bankaccounts.model.Card;
import com.codingz2m.bankaccounts.model.SavingsAccount;
import com.codingz2m.bankaccounts.repository.SavingsAccountRepository;
import com.codingz2m.bankaccounts.util.AppUtils;

@Component
public class SavingsAccountDAOImpl implements SavingsAccountDAO {

	 private final SavingsAccountRepository savingsAccountRepository;
	 
	public SavingsAccountDAOImpl(SavingsAccountRepository savingsAccountRepository) {
		this.savingsAccountRepository = savingsAccountRepository;
	}

	@Override
	public SavingsAccount save(SavingsAccountDto savingsAccountDto) {
		return savingsAccountRepository.save( AppUtils.dtoToEntity(savingsAccountDto) );
	}

	@Override
	public SavingsAccount getSavingsAccount(Long savingsAccountId) throws SavingsAccountNotFoundException {
		Optional<SavingsAccount> optionalSavingsAccount = savingsAccountRepository.findById(savingsAccountId);
		if ( !optionalSavingsAccount.isPresent() ) {
			throw new SavingsAccountNotFoundException("SavingsAccount Not Found for the ID: " + savingsAccountId);
		}
		else {
			return optionalSavingsAccount.get();
		}
	}
	
	@Override
	public List<SavingsAccount> getSavingsAccountByHolderName(String holderName)
			throws SavingsAccountNotFoundException {
		
		List<SavingsAccount> savingsAccounts = savingsAccountRepository.findByHolderNameIgnoreCase(holderName);
		if ( savingsAccounts.isEmpty() ) {
			throw new SavingsAccountNotFoundException("Savings Accounts Are Not Found for the Account Holder Names " + " starts with " + holderName);
		}
		else {
			return savingsAccounts;
		}
	}

	@Override
	public List<SavingsAccount> savingsAccountsByCurrentBalanceBetween(double minCurrentValue, double maxCurrentValue) throws SavingsAccountNotFoundException{
		List<SavingsAccount> savingsAccounts = savingsAccountRepository.findSavingsAccountsByCurrentBalanceBetween(minCurrentValue,  maxCurrentValue);
		if ( savingsAccounts.isEmpty() ) {
			throw new SavingsAccountNotFoundException("Savings Accounts Are Not Found for the given minimum & maximum values!");
		}
		else {
			return savingsAccounts;
		}
	}

	@Override
	public SavingsAccount update(SavingsAccountDto savingsAccountDto) throws SavingsAccountNotFoundException {
		SavingsAccount savingsAccount =  AppUtils.dtoToEntity(savingsAccountDto);
		Optional<SavingsAccount> optionalSavingsAccount  = savingsAccountRepository.findById(savingsAccount.getId());
		
		if ( !optionalSavingsAccount.isPresent() ) {
			throw new SavingsAccountNotFoundException("SavingsAccount ID: " + savingsAccount.getId() + " Not Found to Update");
		}
		else {
			SavingsAccount	existingSavingsAccount = optionalSavingsAccount.get();
			existingSavingsAccount.setHolderName(savingsAccount.getHolderName());
			existingSavingsAccount.setAccountType(savingsAccount.getAccountType());
			existingSavingsAccount.setAverageQuarterlyBalance(savingsAccount.getAverageQuarterlyBalance());
			existingSavingsAccount.setResidentAccountsDebitCardLimit(savingsAccount.getResidentAccountsDebitCardLimit());
			existingSavingsAccount.setCurrentBalance(savingsAccount.getCurrentBalance());
			existingSavingsAccount.setPhone(savingsAccount.getPhone());
			existingSavingsAccount.setEmail(savingsAccount.getEmail());
		 return savingsAccountRepository.save(existingSavingsAccount);
		}
	}

	@Override
	public List<SavingsAccount> search(String holderName, Double currentBalance) throws SavingsAccountNotFoundException{
		List<SavingsAccount> savingsAccounts = savingsAccountRepository.findByHolderNameOrCurrentBalance(holderName, currentBalance);
		if ( savingsAccounts.isEmpty() ) {
			throw new SavingsAccountNotFoundException("Savings Accounts Are Not Found for the given Holder Name OR Current Value!");
		}
		else {
			return savingsAccounts;
		}
	}

	@Override
	public List<SavingsAccount> getHigherCurrentBalanceSavingsAccounts() throws SavingsAccountNotFoundException {
		List<SavingsAccount> savingsAccounts = savingsAccountRepository.orderByCurrentBalanceDesc();
		if ( savingsAccounts.isEmpty() ) {
			throw new SavingsAccountNotFoundException("Savings Accounts Are Not Able Fetched Try Again!");
		}
		else {
			return savingsAccounts;
		}
	}

	@Override
	public Iterable<SavingsAccount> getAllSavingsAccounts() {
		return savingsAccountRepository.findAll();
	}

	@Override
	public void deleteSavingsAccount(Long savingsAccountId) throws SavingsAccountNotFoundException {
		Optional<SavingsAccount> optionalSavingsAccount = savingsAccountRepository.findById(savingsAccountId);
		if ( !optionalSavingsAccount.isPresent() ) {
			throw new SavingsAccountNotFoundException("SavingsAccount ID: " + savingsAccountId + " Not Found to Delete");
		}
		else {
		savingsAccountRepository.deleteById(savingsAccountId);
		}
	}

	@Override
	public Set<Card> findAllCardsBySavingsAccountId(Long savingsAccountId) throws SavingsAccountNotFoundException {
		SavingsAccount savingsAccount = getSavingsAccount(savingsAccountId);
				if ( savingsAccount == null ) {
					throw new SavingsAccountNotFoundException("SavingsAccount Not Found for the ID: " + savingsAccountId);
				}
				else {
					Set<Card> cards = savingsAccountRepository.findAllCardsBySavingsAccountId(savingsAccountId);
					return cards;
				}
	}

}
