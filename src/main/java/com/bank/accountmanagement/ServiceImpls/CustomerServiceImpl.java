package com.bank.accountmanagement.ServiceImpls;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.accountmanagement.Models.Account;
import com.bank.accountmanagement.Repositories.AccountRepository;
import com.bank.accountmanagement.Services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	AccountRepository accountRepository;
	
	public Set<HashMap<String,String>> getAccountDetailsByCustomerId(int customerId){
		Set<HashMap<String,String>> accounts = new HashSet<HashMap<String,String>>();
		Optional<List<Account>> accs = accountRepository.findByCustomerCustomerId(customerId);
		if(accs.isPresent()) {
			for(Account acc: accs.get()) {
				HashMap<String, String> accAndBalance = new HashMap<String, String>();
				accAndBalance.put("account_number", Long.toString(acc.getAccountNumber()));
				accAndBalance.put("current_balance", Double.toString(acc.getCurrentBalance()));
				accounts.add(accAndBalance);
			}
		}
		return accounts;
	}
}
