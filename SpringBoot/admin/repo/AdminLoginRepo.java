package com.boot.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.admin.model.AdminLoginDetails;

public interface AdminLoginRepo extends JpaRepository<AdminLoginDetails, Integer>
{
	public AdminLoginDetails findByEmail(String username);
}
