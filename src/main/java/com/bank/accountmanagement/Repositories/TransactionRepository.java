package com.bank.accountmanagement.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.accountmanagement.Models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,String> {
	
	@Query(value="SELECT * FROM (SELECT * FROM transaction INNER JOIN account_transactions ON transaction.transaction_id=account_transactions.transactions_transaction_id) as al WHERE al.account_account_number=? ORDER BY transaction_id DESC LIMIT 5;", nativeQuery = true)
	List<Transaction> getTop5Transactions(long accountNumber);
	
	@Query(value="SELECT * FROM (SELECT * FROM transaction INNER JOIN account_transactions ON transaction.transaction_id=account_transactions.transactions_transaction_id) as al WHERE al.account_account_number=? AND date_time>=?", nativeQuery = true)
    List<Transaction> getAllTransactionsByDate(long accountNumber, String date);

	@Query(value="SELECT * FROM (SELECT * FROM transaction INNER JOIN account_transactions ON transaction.transaction_id=account_transactions.transactions_transaction_id) as al WHERE al.account_account_number=? AND date_time>=?  ORDER BY transaction_id DESC LIMIT 10;", nativeQuery = true)
	List<Transaction> getTop10TransactionsByDate(long accountNumber,String date);
	
}
