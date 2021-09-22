package com.bank.accountmanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.accountmanagement.Models.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer>{

}
