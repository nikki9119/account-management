package com.bank.accountmanagement.Services;

import com.bank.accountmanagement.Models.Customer;
import com.bank.accountmanagement.Models.Transaction;

public interface Transactionstatementservice {
	
	 
	public Customer getCustomerId(Long id);
	public Transaction geTransactionId(int transactionId);

	
}
