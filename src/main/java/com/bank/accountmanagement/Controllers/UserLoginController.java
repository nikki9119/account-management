package com.bank.accountmanagement.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.bank.accountmanagement.Services.UserLoginService;

@RestController
public class UserLoginController {
	
	@Autowired
	UserLoginService userLoginService;
	@GetMapping("/{userId}/{password}/{roleId}")
	public String verifyUserCredential(@PathVariable int userId, @PathVariable String password, @PathVariable int roleId )
	{
		return userLoginService.verifyUserCredential(userId, password, roleId);
	}
	
	
	
}
