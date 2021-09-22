package com.bank.accountmanagement.Service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bank.accountmanagement.Models.Account;
import com.bank.accountmanagement.Models.Transaction;

@Component
public interface TransactionService {
	
	//Deposit
	public void Deposit(String depositID, double amount);
	
	//Transfer
	public String Transfer(String senderId, double amount, String recieverId);
	
	//Withdraw
	public Long getCurrentBalance(String accountNumber);
	public Long getAmount();
	public Long DeductMoney(String accountNumber, Long amountToWithdraw);
	public boolean checkLimit10000(String accountNumber, Long amountToWithdraw);
	
	//Email notification
	public void sendEmail(Account account, Transaction transaction); 
	
	//Recent Transactions
	public String checkHistory(String accountNum);
}
