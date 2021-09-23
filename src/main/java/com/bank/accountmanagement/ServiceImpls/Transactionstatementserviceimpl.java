package com.bank.accountmanagement.ServiceImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.accountmanagement.Models.Account;
import com.bank.accountmanagement.Models.Transaction;
import com.bank.accountmanagement.Repositories.AccountRepository;
import com.bank.accountmanagement.Repositories.TransactionRepository;
import com.bank.accountmanagement.Services.Transactionstatementservice;


@Service
public class Transactionstatementserviceimpl implements Transactionstatementservice{

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public List<Transaction> findTransactionList(long accountNumber) {
		
		Optional<Account> result= accountRepository.findById(accountNumber);
		List<Transaction> transactionlist;
		if(result.isPresent()) {
		Account res=result.get();
		transactionlist= res.getTransactions();
		transactionlist= transactionRepository.getTop5Transactions(res.getAccountNumber());
		return transactionlist;
		}
		
		return new ArrayList<Transaction>();
	}

	
	@Override
	public List<Transaction> findTransactionListDate(long accountNumber, String date) {
		Optional<Account> result= accountRepository.findById(accountNumber);
		List<Transaction> transactionlist;
		
		if(result.isPresent()) {
		Account res=result.get();
		transactionlist= res.getTransactions();
		try {
		transactionlist= transactionRepository.getTop10TransactionsByDate(res.getAccountNumber(),date);
		if(transactionlist.isEmpty())
		{
			throw new Exception();
		}
		}
		catch (Exception e) {
			String s="There is no transaction after this date"+ date;

			// TODO: handle exception
		}
		return transactionlist;
		}
		
		return new ArrayList<Transaction>();

	}

	public List<Transaction> listAll(long accountNumber, String date) {
        // TODO Auto-generated method stub
        Optional<Account> result= accountRepository.findById(accountNumber);
        List<Transaction> transactionlist;
        if(result.isPresent()) {
        Account res=result.get();
        transactionlist= res.getTransactions();
        transactionlist= transactionRepository.getAllTransactionsByDate(accountNumber, date);
        return transactionlist;
        }
        
        return new ArrayList<Transaction>();
    }
		

}
