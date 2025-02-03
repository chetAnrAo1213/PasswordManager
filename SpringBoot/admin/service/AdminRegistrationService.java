package com.boot.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.admin.model.AdminLoginDetails;
import com.boot.admin.model.AdminRegistrationDetails;
import com.boot.admin.repo.AdminLoginRepo;
import com.boot.admin.repo.AdminRegistrationRepo;

@Service
public class AdminRegistrationService 
{
	
	@Autowired
	AdminRegistrationRepo arp;
	
	@Autowired
	AdminLoginRepo alr;
	
	@Autowired
	BCryptPasswordEncoder pass;
	
	public AdminRegistrationDetails saveData(AdminRegistrationDetails ard,AdminLoginDetails ald)
	{
		
		String normal = ard.getPassword();
		String encrypted = pass.encode(normal);
		ard.setPassword(encrypted);
		
		ald.setEmail(ard.getMailId());
		ald.setPassword(encrypted);
		ald.setLastModifiedDate("");
		this.arp.save(ard);
		this.alr.save(ald);
		return ard;
	}


}
