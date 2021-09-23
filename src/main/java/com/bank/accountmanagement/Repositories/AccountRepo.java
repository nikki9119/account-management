package com.bank.accountmanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.accountmanagement.Models.Account;

public interface AccountRepo extends JpaRepository<Account, Long>{

}
