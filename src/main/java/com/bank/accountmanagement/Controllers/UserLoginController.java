package com.bank.accountmanagement.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.accountmanagement.Services.AccCreationEmailService;
import com.bank.accountmanagement.Services.UserLoginService;

@RestController
public class UserLoginController {
	
	@Autowired
	UserLoginService userLoginService;
	@Autowired
	AccCreationEmailService accCreationEmailService;
	
	
	@GetMapping("/user-login")
	public String verifyUserCredential(@RequestParam(required = true) String userId, @RequestParam(required = true) String password, @RequestParam (required = true)int roleId )
	{
		return userLoginService.verifyUserCredential(userId, password, roleId);
	}
	
	@PutMapping("/update-user-credentials")
	public String updateUserCredential(@RequestParam(required = true) String userId,@RequestParam(required = true) String password)
	{
		return userLoginService.updateUserCredential(userId, password);
	}
	
	@GetMapping("/email")
	public void sendMail(@RequestParam(required = true) String emailUserName,@RequestParam(required = true) String emailPassword, @RequestParam(required = true) String customerEmail)
	{
		accCreationEmailService.sendEmail(emailUserName,emailPassword,customerEmail);
	}
	
}
