package com.bank.accountmanagement.ServiceImpls;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.accountmanagement.Models.Account;
import com.bank.accountmanagement.Models.Customer;
import com.bank.accountmanagement.Models.Transaction;
import com.bank.accountmanagement.Repositories.AccountRepository;
import com.bank.accountmanagement.Repositories.CustomerRepository;
import com.bank.accountmanagement.Services.ManagerService;


@Service
public class ManagerServiceImpl implements ManagerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public Customer createNewCustomer(Customer customer) throws Exception{
		try {
			customer = customerRepository.save(customer);
		}catch(Exception e){
			throw e;
		}
		return customer;
	}
	public ResponseEntity<Object> verifyPanCard(String panCardNumber)
	{
		Customer customer = customerRepository.findCustomerByPanCard(panCardNumber);
		HashMap<String,String> result = new HashMap<String, String>();
		if(customer != null)
		{
			result.put("message","Account exists");
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
		else 
		{
			result.put("message","Account doest not exists");
			return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	public Account createNewAccount(int customerId) throws Exception{
		Account account = new Account();
		System.out.println(customerId);
		Customer customer = customerRepository.findById(customerId).get();
		System.out.println(customer.getName());
		account.setCurrentBalance(0);
		account.setCustomer(customer);
		account.setDailyLimit(0);
		account.setTransactions(new ArrayList<Transaction>());
		System.out.println(account.getCurrentBalance());
		try {
			account = accountRepository.save(account);
		}catch(Exception e) {
			throw e;
		}
		return account;
	}
}
