package com.leonardoblanco.search.fuzzy.repository;

import org.springframework.data.repository.CrudRepository;

import com.leonardoblanco.search.fuzzy.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
