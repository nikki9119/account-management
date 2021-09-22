package com.bank.accountmanagement.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.accountmanagement.Service.TransactionService;

@RestController
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;

//WITHDRAW
	@RequestMapping("api/getCurrentBalance/{id}")
	public Long getCurrentBalance(@PathVariable String id) {
		
		Long currentBalance = transactionService.getCurrentBalance(id);
	    return currentBalance; 
	}
	
	@RequestMapping("api/updateCurrentBalance/{id}")
	public Long updateCurrentBalance(@PathVariable String id, Long amount) {
		
		Long currentBalance = transactionService.DeductMoney(id,amount);
	    return currentBalance;
	}
	
//DEPOSIT
	@PostMapping("/deposit/{depositID}/{amount}")
	public void DepositMoney(@PathVariable String depositID,@PathVariable double amount)
	{
		transactionService.Deposit(depositID,amount);
	}
	
	
//TRANSFER
	@PostMapping(value = "/transfer",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.TEXT_PLAIN_VALUE)
	public String transferMoney(@RequestBody TransferData transferData)
	{	
		return transactionService.Transfer(transferData.getSenderId(),transferData.getAmount(),transferData.getRecieverId());
	}
	
	@GetMapping("/recent")
	public String checkHistory(String accountNum)
	{
		return transactionService.checkHistory(accountNum);
	}
	
	@GetMapping("/")
	public String demoPost(String data)
	{
		return "hello";
	}
	
//Transfer Data
	public static class TransferData{
		private String senderId;
		private double amount;
		private String recieverId;
		
		public TransferData() {
        }
		public TransferData(String senderId, double amount, String recieverId) {
			super();
			this.senderId = senderId;
			this.amount = amount;
			this.recieverId = recieverId;
		}
		public String getSenderId() {
			return senderId;
		}
		public void setSenderId(String senderId) {
			this.senderId = senderId;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getRecieverId() {
			return recieverId;
		}
		public void setRecieverId(String recieverId) {
			this.recieverId = recieverId;
		}
		@Override
		public String toString() {
			return "TransferData [senderId=" + senderId + ", amount=" + amount + ", recieverId=" + recieverId + "]";
		}
		
	}
	
}
