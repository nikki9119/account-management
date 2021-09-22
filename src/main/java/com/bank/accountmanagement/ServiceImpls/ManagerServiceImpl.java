package com.bank.accountmanagement.ServiceImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.accountmanagement.Models.Customer;
import com.bank.accountmanagement.Repositories.CustomerRepository;
import com.bank.accountmanagement.Services.ManagerService;


@Service
public class ManagerServiceImpl implements ManagerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer createNewCustomer(Customer customer) throws Exception{
		try {
			customer = customerRepository.save(customer);
		}catch(Exception e){
			throw e;
		}
		return customer;
	}
	public String verifyPanCard(String panCardNumber)
	{
		Customer customer = customerRepository.findCustomerByPanCard(panCardNumber);
		if(customer != null)
		{
			return "Account exists";
		}
		else 
		{
			return "Account doesn't exist";
		}
		
		
	}
}
