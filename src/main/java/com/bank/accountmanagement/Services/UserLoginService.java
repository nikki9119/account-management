package com.bank.accountmanagement.Services;

public interface UserLoginService {

	public String verifyUserCredential(int userId, String password, int roleId);
	

}
