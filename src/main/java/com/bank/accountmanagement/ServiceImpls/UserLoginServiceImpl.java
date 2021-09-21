package com.bank.accountmanagement.ServiceImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.accountmanagement.Models.User;
import com.bank.accountmanagement.Repositories.UserLoginRepository;
import com.bank.accountmanagement.Services.UserLoginService;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	UserLoginRepository userLoginRepository;

	public String verifyUserCredential(int userId, String password, int roleId) {

		User user = userLoginRepository.findUserByUserIdAndPassword(userId,password);
		User user1= userLoginRepository.findUserByUserIdAndRoleRoleId(userId,roleId);
		if (user != null && user1!=null) {
			return "Valid User";
		} 
		else {
			return "Invalid User";
		}

	}

}