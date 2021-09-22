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
	
	public User createNewUserCredentials() {
//		byte[] array = new byte[8];
		String userId = RandomStringUtils.randomAlphabetic(7);
		System.out.println(userId);
	    String password = RandomStringUtils.randomAlphabetic(8);
	    System.out.println(password);
	    User newUser = new User(userId,passwordEncoder.encode(password),roleRepository.findById(1));
		return newUser;
	}

}