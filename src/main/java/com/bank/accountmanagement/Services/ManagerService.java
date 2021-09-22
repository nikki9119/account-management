package com.bank.accountmanagement.Services;

import com.bank.accountmanagement.Models.Customer;

public interface ManagerService {
	Customer createNewCustomer(Customer customer) throws Exception;

	public String verifyPanCard(String panCardNumber);
}
