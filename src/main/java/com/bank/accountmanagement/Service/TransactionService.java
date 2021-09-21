package com.bank.accountmanagement.Service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bank.accountmanagement.Models.Account;

@Component
public interface TransactionService {
	public void Transfer(String senderId, String recieverId, double amount);
}
