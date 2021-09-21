package com.bank.accountmanagement.ServiceImpls;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bank.accountmanagement.Models.Account;
import com.bank.accountmanagement.Models.Transaction;
import com.bank.accountmanagement.Repositories.AccountRepo;
import com.bank.accountmanagement.Repositories.TransactionRepo;
import com.bank.accountmanagement.Service.TransactionService;

@Component
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	AccountRepo accountRepo;
	@Autowired
	TransactionRepo transactionRepo;
	
	@Override
	public void Transfer(String senderId, String recieverId, double amount) {
		
		Account sender = accountRepo.getById(senderId) ;
		Account reciever = accountRepo.getById(recieverId);
		
		LocalDateTime timeStamp = LocalDateTime.now();
		
		Transaction senderTransaction = new Transaction();
		sender.setCurrentBalance(sender.getCurrentBalance() - amount);
		senderTransaction.setCurrentBalance(-amount);
		senderTransaction.setTransactionType("Debit");
		senderTransaction.setSubType("Transfer");
		senderTransaction.setDateTime(timeStamp);
		senderTransaction.setTransactionRefNum("T"+sender.getAccountNumber()+"-"+reciever.getAccountNumber()+senderTransaction.getDateTime());
		sender.getTransactions().add(senderTransaction);
		accountRepo.save(sender);
		//transactionRepo.save(senderTransaction);
	
		Transaction recieverTransaction = new Transaction();
		reciever.setCurrentBalance(reciever.getCurrentBalance() + amount);
		recieverTransaction.setCurrentBalance(amount);
		recieverTransaction.setTransactionType("Credit");
		recieverTransaction.setSubType("Transfer");
		recieverTransaction.setDateTime(timeStamp);
		recieverTransaction.setTransactionRefNum("T"+sender.getAccountNumber()+"-"+reciever.getAccountNumber()+recieverTransaction.getDateTime());
		reciever.getTransactions().add(recieverTransaction);
		accountRepo.save(reciever);		
		//transactionRepo.save(recieverTransaction);
	}

}
