package com.bank.accountmanagement.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;

@Entity
public class Account {
	@SequenceGenerator(name="seq", initialValue=1000000000, allocationSize=1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq")
	@Column
	private String accountNumber;
	@ManyToOne
	private Customer customer;
	@Column
	private double currentBalance;
	@OneToMany
	private List<Transaction> transactions;
	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}


	public Account(String accountNumber, Customer customer, double currentBalance) {
		super();
		this.accountNumber = accountNumber;
		this.customer = customer;
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
	
	
}
