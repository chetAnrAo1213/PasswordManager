package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.LoginRepo;
import com.boot.models.UserLoginDetails;

@Service
public class LoginService 
{

	@Autowired
	LoginRepo lpo;
	
	public void insertUser()
	{
		this.lpo.save(null);
	}
	
	public boolean authenticatePassword(String mailId, String Password)
	{
		UserLoginDetails user = this.lpo.findByMail(mailId,Password);
		if(user==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean authenticatePin(String mailId, String Password)
	{
		String userpin = this.lpo.findPasswordAndPin(mailId);
		
			if(userpin.equals(Password))
			{
				return true;	
			}
			else
			{
				return false;
			}
	}
}
