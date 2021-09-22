package com.bank.accountmanagement.Models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Transaction {
	@Id
	@GeneratedValue
	int transactionId;
	String transactionRefNum;
	LocalDateTime dateTime;
	String transactionType;
	String subType;
	double transactionAmount;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
		dateTime = LocalDateTime.now();
	}
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionRefNum() {
		return transactionRefNum;
	}
	public void setTransactionRefNum(String transactionRefNum) {
		this.transactionRefNum = transactionRefNum;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionRefNum=" + transactionRefNum
				+ ", dateTime=" + dateTime + ", transactionType=" + transactionType + ", subType=" + subType
				+ ", transactionAmount=" + transactionAmount + "]";
	}	
	
}
