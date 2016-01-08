package com.leonardoblanco.search.fuzzy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.leonardoblanco.search.fuzzy.entity.Customer;
import com.leonardoblanco.search.fuzzy.repository.CustomerRepository;

@SpringBootApplication
public class FuzzySearchApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FuzzySearchApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Customer c = new Customer();
		c.setName("Leonardo");
		
		customerRepository.save(c);
		
		c = new Customer();
		c.setName("Leandro");
		
		customerRepository.save(c);
		
		c = new Customer();
		c.setName("Leonildo");
		
		customerRepository.save(c);
		
		c = new Customer();
		c.setName("Acacio");
		
		customerRepository.save(c);
		
		c = new Customer();
		c.setName("Loelmo");
		
		customerRepository.save(c);
		
	}
}
