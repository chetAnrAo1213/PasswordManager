package com.boot.config.sec;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.boot.admin.model.AdminLoginDetails;

public class AdminDetails implements UserDetails{

	AdminLoginDetails ald;
	
	public AdminDetails(AdminLoginDetails ald) {
		this.ald = ald;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		
		return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
	}

	@Override
	public String getPassword() 
	{
		return ald.getPassword();
	}

	@Override
	public String getUsername() 
	{
		return ald.getEmail();
	}

}
