package com.bank.accountmanagement.ServiceImpls;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.accountmanagement.Models.User;
import com.bank.accountmanagement.Repositories.RoleRepository;
import com.bank.accountmanagement.Repositories.UserLoginRepository;
import com.bank.accountmanagement.Services.UserLoginService;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	UserLoginRepository userLoginRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
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
		int userId = (int) Math.random();
		byte[] array = new byte[8];
	    String password = new String(array, Charset.forName("UTF-8"));
	    User newUser = new User(userId, passwordEncoder.encode(password),roleRepository.findById(1));
		return newUser;
	}

}