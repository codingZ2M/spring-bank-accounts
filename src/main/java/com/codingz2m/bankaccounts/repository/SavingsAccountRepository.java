package com.codingz2m.bankaccounts.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codingz2m.bankaccounts.model.Card;
import com.codingz2m.bankaccounts.model.SavingsAccount;

@Repository
public interface SavingsAccountRepository extends CrudRepository<SavingsAccount, Long> {
	
	List<SavingsAccount> findByHolderNameIgnoreCase(String holderName);
	
	 @Query("SELECT sa FROM SavingsAccount sa WHERE sa.currentBalance BETWEEN :minCurrentValue AND :maxCurrentValue")
		List<SavingsAccount> findSavingsAccountsByCurrentBalanceBetween(@Param("minCurrentValue") double minCurrentValue, @Param("maxCurrentValue")double maxCurrentValue);
	 
	 
	    
	    List<SavingsAccount> findByHolderNameOrCurrentBalance(@Param("holderName") String holderName,
	    											@Param("currentBalance") double currentBalance);
	    
	    
	    @Query("SELECT sa FROM SavingsAccount sa ORDER BY sa.currentBalance DESC")
	    List<SavingsAccount> orderByCurrentBalanceDesc();
	    
	    @Query("SELECT c FROM Card c WHERE c.savingsAccount.id = :savingsAccountId")
	    Set<Card> findAllCardsBySavingsAccountId(@Param("savingsAccountId") Long savingsAccountId);
	}
