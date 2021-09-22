package com.bank.accountmanagement.ServiceImpls;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.accountmanagement.Models.User;
import com.bank.accountmanagement.Repositories.RoleRepository;
import com.bank.accountmanagement.Repositories.UserLoginRepository;
import com.bank.accountmanagement.Services.UserLoginService;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	UserLoginRepository userLoginRepository;
	
	CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();
	
	@Autowired
	private RoleRepository roleRepository;
	
	public User createNewUserCredentials() {
//		byte[] array = new byte[8];
		String userId = RandomStringUtils.randomAlphabetic(7);
		System.out.println(userId);
	    String password = RandomStringUtils.randomAlphabetic(8);
	    System.out.println(password);
	    User newUser = new User(userId,passwordEncoder.encode(password),roleRepository.findById(1));
		return newUser;
	}
	
	public String verifyUserCredential(String userId, String password, int roleId) {

		User user = userLoginRepository.findUserByUserIdAndPassword(userId, password);
		User user1 = userLoginRepository.findUserByUserIdAndRoleRoleId(userId, roleId);
		if (user != null && user1 != null) {
			return "Valid User";
		} else {
			return "Invalid User";
		}

	}
	public String updateUserCredential(String userId, String password) {
		User user1 = userLoginRepository.findUserByUserId(userId);
		if (user1 == null)
		{
			return "User not found";
		}
		else if(user1.isFirstTime()==true)
		{
			user1.setPassword(password);
			user1.setFirstTime(false);
			userLoginRepository.save(user1);
			return "Credentials updated";
		}
		else 
		{
			return "You have already updated your password";
		}
		
	}

}