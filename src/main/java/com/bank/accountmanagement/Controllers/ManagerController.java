package com.bank.accountmanagement.Controllers;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bank.accountmanagement.Models.Customer;
import com.bank.accountmanagement.Models.User;
import com.bank.accountmanagement.Services.AccCreationEmailService;
import com.bank.accountmanagement.Services.ManagerService;
import com.bank.accountmanagement.Services.UserLoginService;

@RestController
public class ManagerController {
	@Autowired 
	ManagerService managerService;
	
	@Autowired
	UserLoginService userLoginService;
	@Autowired
	AccCreationEmailService accCreationEmailService;
	
	@PostMapping("api/manager/create-customer")
	public ResponseEntity<Object> createCustomer(@RequestParam(value="pan_number") String panCard, 
	@RequestParam(value="aadhar_number") long aadharNumber, @RequestParam(value="name") String name, 
	@RequestParam(value="postal_address") String postalAddress, @RequestParam(value="email") String email,
	@RequestParam(value="dob") String dob, @RequestParam(value="pan_img") MultipartFile panImg, 
	@RequestParam(value="aadhar_img") MultipartFile aadharImg){
		
		Customer newCust = new Customer();
		newCust.setName(name);
		newCust.setDobFromString(dob);
		newCust.setPanCard(panCard);
		newCust.setAadharNumber(aadharNumber);
		newCust.setPostalAddress(postalAddress);
		newCust.setEmail(email);
		User newUser = userLoginService.createNewUserCredentials();
		newCust.setUser(newUser);
		HashMap<String,String> resultSet = new HashMap<String,String>();
		try {
			newCust.setAadharImg(aadharImg.getBytes());
			newCust.setPanImg(panImg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			resultSet.put("message", e.getMessage());
			return new ResponseEntity<>(resultSet, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			newCust = managerService.createNewCustomer(newCust);
		}catch(Exception e) {
			e.printStackTrace();
			resultSet.put("message", e.getMessage());
			return new ResponseEntity<>(resultSet, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		resultSet.put("customer_id", Long.toString(newCust.getCustomerId()));
		String emailUserName = newUser.getUserId();
		String emailPassword = newUser.getPassword();
		String customerEmail = newCust.getEmail();
		accCreationEmailService.sendEmail(emailUserName,emailPassword,customerEmail);
		return new ResponseEntity<>(resultSet, HttpStatus.OK);
	}
	
	@GetMapping("/verify-pancard")
	public String verifyIfPanCardExists(@RequestParam(required = true) String panCardNumber) {
		return managerService.verifyPanCard(panCardNumber);
	}

}
