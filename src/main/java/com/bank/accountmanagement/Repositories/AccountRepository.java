package com.bank.accountmanagement.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.accountmanagement.Models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Optional<Account> findById(long accountNumber);
	Optional<List<Account>> findByCustomerCustomerId(int customerId);
	
}
