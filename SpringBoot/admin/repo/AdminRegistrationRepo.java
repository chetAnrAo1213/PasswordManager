package com.boot.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.admin.model.AdminLoginDetails;
import com.boot.admin.model.AdminRegistrationDetails;

@Repository
public interface AdminRegistrationRepo extends JpaRepository<AdminRegistrationDetails, Integer>{

}
