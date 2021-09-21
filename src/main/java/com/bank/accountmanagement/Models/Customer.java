package com.bank.accountmanagement.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Customer {
	@SequenceGenerator(name="seq", initialValue=100000, allocationSize=1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq")
	private int customerId;
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
	@OneToOne
	private User user;
	
	public Customer(int customerId, String panCard, long aadharNumber, String name, String postalAddress, String email,
			User user) {
		super();
		this.customerId = customerId;
		this.panCard = panCard;
		this.aadharNumber = aadharNumber;
		this.name = name;
		this.postalAddress = postalAddress;
		this.email = email;
		this.user = user;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
