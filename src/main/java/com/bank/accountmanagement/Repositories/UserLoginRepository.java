package com.bank.accountmanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.accountmanagement.Models.User;

@Repository
public interface UserLoginRepository extends JpaRepository<User, Integer> {

	User findUserByUserIdAndPassword(int userId, String password);
	User findUserByUserIdAndRoleRoleId(int userId, int roleId);

}
