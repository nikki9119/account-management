package com.bank.accountmanagement.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.accountmanagement.Service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	TransactionService service;
	
	@PostMapping("/transfer")
	public void transferMoney(String senderId, double amount,String recieverId)
	{	
		service.Transfer(senderId, recieverId, amount);		
	}
	
	@RequestMapping("/")
	public void checking()
	{
		System.out.println("Succes");		
	}
}
