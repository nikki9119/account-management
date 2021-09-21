package com.bank.accountmanagement.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Account {
	@SequenceGenerator(name="seq", initialValue=1000000000, allocationSize=1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq")
	@Column
	private String accountNumber;
	@Column
	private int customerId;
	@Column
	private double currentBalance;
	
	
	public Account(String accountNumber, int customerId, double currentBalance) {
		super();
		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.currentBalance = currentBalance;
	}


	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public double getCurrentBalance() {
		return currentBalance;
	}


	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	
	
}
