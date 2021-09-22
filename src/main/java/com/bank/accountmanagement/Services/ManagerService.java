package com.bank.accountmanagement.Services;

import com.bank.accountmanagement.Models.Account;
import com.bank.accountmanagement.Models.Customer;

public interface ManagerService {
	Customer createNewCustomer(Customer customer) throws Exception;
	Account createNewAccount(int customerId) throws Exception;
	public String verifyPanCard(String panCardNumber);
}
