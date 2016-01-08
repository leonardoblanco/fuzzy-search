package com.leonardoblanco.search.fuzzy.repository;

import org.springframework.data.repository.CrudRepository;

import com.leonardoblanco.search.fuzzy.entity.Customer;

/**
 * Default CRUD repository for entity Customer
 * @author Leonardo Blanco
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
