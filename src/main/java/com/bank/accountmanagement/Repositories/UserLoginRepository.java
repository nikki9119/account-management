package com.bank.accountmanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.accountmanagement.Models.User;

@Repository
public interface UserLoginRepository extends JpaRepository<User, Integer> {

	User findUserByUserIdAndPassword(String userId, String password);
	User findUserByUserIdAndRoleRoleId(String userId, int roleId);
	User findUserByUserId(String userId);
	

}
