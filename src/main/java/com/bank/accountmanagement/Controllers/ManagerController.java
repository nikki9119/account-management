package com.bank.accountmanagement.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.bank.accountmanagement.Services.ManagerService;

@RestController
public class ManagerController {
	@Autowired 
	ManagerService managerService;
	

}
