package com.bank.accountmanagement.Services;

import java.util.List;

import com.bank.accountmanagement.Models.Transaction;

public interface Transactionstatementservice {
	
	 
	List<Transaction> findTransactionList(long accountNumber);
	List<Transaction> listAll(long accountNumber, String date);
	List<Transaction> findTransactionListDate(long accountNumber,String date);
	
	
}
