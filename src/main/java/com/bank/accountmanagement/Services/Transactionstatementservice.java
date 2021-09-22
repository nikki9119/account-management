package com.bank.accountmanagement.Services;

import java.time.LocalDateTime;
import java.util.List;

import com.bank.accountmanagement.Models.Customer;
import com.bank.accountmanagement.Models.Transaction;

public interface Transactionstatementservice {
	
	 
	List<Transaction> findTransactionList(String accountNumber);
	
	List<Transaction> findTransactionListDate(String accountNumber,String date);
	
	
}
