package com.bank.accountmanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.accountmanagement.Models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findById(int id);
}
