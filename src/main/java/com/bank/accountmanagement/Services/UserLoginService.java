package com.bank.accountmanagement.Services;

import org.springframework.http.ResponseEntity;

import com.bank.accountmanagement.Models.User;

public interface UserLoginService {

	public ResponseEntity<Object> verifyUserCredential(String userId, String password, int roleId);
	public User createNewUserCredentials();
	public ResponseEntity<Object> updateUserCredential(String userId, String password);

}
