package com.bank.accountmanagement.Services;

import com.bank.accountmanagement.Models.User;

public interface UserLoginService {

	public String verifyUserCredential(String userId, String password, int roleId);
	public User createNewUserCredentials();
	public String updateUserCredential(String userId, String password);

}
