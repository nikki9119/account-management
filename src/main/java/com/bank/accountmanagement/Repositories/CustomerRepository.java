package com.bank.accountmanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.accountmanagement.Models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Customer findCustomerByPanCard(String panCardNumber);

}
