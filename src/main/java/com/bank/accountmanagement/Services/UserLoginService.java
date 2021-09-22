package com.bank.accountmanagement.Services;

import com.bank.accountmanagement.Models.User;

public interface UserLoginService {

	public String verifyUserCredential(int userId, String password, int roleId);
	public User createNewUserCredentials();

}
