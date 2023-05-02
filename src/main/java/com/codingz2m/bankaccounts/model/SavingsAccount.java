package com.codingz2m.bankaccounts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="savingsaccount")

public class SavingsAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false, length=150)
	private String holderName;
	@Column(nullable=false, length=150)
	private String accountType;
	@Column(nullable=false, length=10)
	private double averageQuarterlyBalance;
	@Column(nullable=false, length=10)
	private double residentAccountsDebitCardLimit;
	@Column(nullable=false, length=20)
	private Double currentBalance;
	@Column(nullable=false, length=15)
	private String phone;
	@Column(nullable=false, length=150)
	private String email;
	
	  @OneToMany(mappedBy = "savingsAccount", cascade = CascadeType.ALL, orphanRemoval = true)
	    private Set<Card> cards = new HashSet<>();
    
}
