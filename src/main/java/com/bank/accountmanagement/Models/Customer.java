package com.bank.accountmanagement.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Customer {
	@SequenceGenerator(name="seq", initialValue=10000, allocationSize=1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq")
	private long customerId;
	@Column
	private String panCard;
	@Column
	private	long aadharNumber;
	@Column
	private String name;
	@Column
	private String postalAddress;
	@Column
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
