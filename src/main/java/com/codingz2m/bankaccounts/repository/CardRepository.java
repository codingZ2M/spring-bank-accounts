package com.codingz2m.bankaccounts.repository;

import org.springframework.data.repository.CrudRepository;

import com.codingz2m.bankaccounts.model.Card;

public interface CardRepository extends CrudRepository<Card, Long> {

}
