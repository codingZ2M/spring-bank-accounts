package com.codingz2m.bankaccounts.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "card")
public class Card {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long cardNumber;
    
    @Column(nullable = false)
    private Integer cvv;
    
    @Column(nullable = false)
    private String expiryDate;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "savingsaccount_id", nullable = false)
    private SavingsAccount savingsAccount;

    /* A many-to-one relationship with SavingsAccount, which represents the account 
       to which the card is linked.
     */

}
