package com.bank.accountmanagement.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.accountmanagement.Models.Account;
import com.bank.accountmanagement.Models.Transaction;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	
	Optional<Account> findById(String accountNumber);
	
}
