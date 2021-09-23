package com.bank.accountmanagement.Models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


@Entity
public class Account {
	@SequenceGenerator(name="seq1", initialValue=1000000000, allocationSize=1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq1")
	@Column
	private long accountNumber;
	@ManyToOne
	private Customer customer;
	@Column
	private double currentBalance;
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Transaction> transactions;
	@Column
	private double dailyLimit;

	public double getDailyLimit() {
		return dailyLimit;
	}


	public void setDailyLimit(double dailyLimit) {
		this.dailyLimit = dailyLimit;
	}


	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}


	public Account(long accountNumber, Customer customer, double currentBalance) {
		super();
		this.accountNumber = accountNumber;
		this.customer = customer;
		this.currentBalance = currentBalance;
		this.dailyLimit = 0;
	}


	public Account() {
		super();
		//transactions = new List<Transaction>();
		// TODO Auto-generated constructor stub
	}


	public long getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public double getCurrentBalance() {
		return currentBalance;
	}


	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}


	public List<Transaction> getTransactions() {
		return transactions;
	}
}
