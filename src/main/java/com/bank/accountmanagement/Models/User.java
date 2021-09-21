package com.bank.accountmanagement.Models;

public class User {
	private int UserId;
	private String Password;
	
	
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public User(int userId, String password) {
		super();
		UserId = userId;
		Password = password;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
