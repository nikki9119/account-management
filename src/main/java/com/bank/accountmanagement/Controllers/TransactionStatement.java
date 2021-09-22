package com.bank.accountmanagement.Controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.accountmanagement.Models.Customer;
import com.bank.accountmanagement.Models.Transaction;
import com.bank.accountmanagement.Services.Transactionstatementservice;

@RestController
@RequestMapping("/transaction")
public class TransactionStatement {
	
	@Autowired
	private Transactionstatementservice transactionstatement;
	
	@GetMapping("api/transaction/get-transaction/{accountNumber}")
	public ResponseEntity<Object> gettransactionlist(@PathVariable String accountNumber){
		List<Transaction> transactionlist= transactionstatement.findTransactionList(accountNumber);
		if(transactionlist.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(transactionlist, HttpStatus.OK);
		
		
	}
	@GetMapping("api/transaction/get-transaction/{accountNumber}/{date}")
	public ResponseEntity<Object> gettransactionlist(@PathVariable String accountNumber,@PathVariable String date){
		List<Transaction> transactionlist= transactionstatement.findTransactionListDate(accountNumber,date);
		if(transactionlist.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(transactionlist, HttpStatus.OK);
		
		
	}
	
	
	

}
