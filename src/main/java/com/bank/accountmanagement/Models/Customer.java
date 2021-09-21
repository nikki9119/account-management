package com.bank.accountmanagement.Models;

public class Customer {
	private long customerId;
	private String panCard;
	private	long aadharNumber;
	private String name;
	private String postalAddress;
	private String email;
	public Customer(long customerId, String panCard, long aadharNumber, String name, String postalAddress,
			String email) {
		super();
		this.customerId = customerId;
		this.panCard = panCard;
		this.aadharNumber = aadharNumber;
		this.name = name;
		this.postalAddress = postalAddress;
		this.email = email;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getPanCard() {
		return panCard;
	}
	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}
	public long getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
