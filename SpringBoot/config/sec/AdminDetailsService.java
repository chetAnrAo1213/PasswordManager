package com.boot.config.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.boot.admin.model.AdminLoginDetails;
import com.boot.admin.repo.AdminLoginRepo;

@Component
public class AdminDetailsService implements UserDetailsService{

	
	@Autowired
	AdminLoginRepo arl;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		AdminLoginDetails admin = arl.findByEmail(username);
		if(admin==null)
		{
			System.out.println("No Username Found");
			throw new UsernameNotFoundException(username);
		}
		else
		{
			return new AdminDetails(admin);
		}
	}

}
