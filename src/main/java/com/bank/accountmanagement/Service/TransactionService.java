package com.bank.accountmanagement.Service;

import com.bank.accountmanagement.Models.Account;

public interface TransactionService {
	public void Transfer(Account sender, Account reciever, double amount);
}
