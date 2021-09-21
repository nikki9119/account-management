package com.bank.accountmanagement.ServiceImpls;

import org.springframework.beans.factory.annotation.Autowired;

import com.bank.accountmanagement.Models.Account;
import com.bank.accountmanagement.Models.Transaction;
import com.bank.accountmanagement.Repositories.TransactionRepo;
import com.bank.accountmanagement.Service.TransactionService;

public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepo transactionRepo;
	
	@Override
	public void Transfer(Account sender, Account reciever, double amount) {
		
		Transaction transaction = new Transaction();
		transaction.setCurrentBalance(sender.getCurrentBalance() - amount);
		transaction.setCurrentBalance(reciever.getCurrentBalance() + amount);
		transaction.setTransactionRefNum("T"+sender.getAccountNumber()+"-"+reciever.getAccountNumber()+transaction.getDateTime());
		transaction.setTransactionType("Debit");
		transaction.setSubType("Transfer");
		
		transactionRepo.save(transaction);
		

	}

}
