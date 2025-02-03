package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boot.dao.RegistrationRepo;
import com.boot.models.UserRegistrationDetails;

@Service
public class AuthService 
{
	@Autowired 
	RegistrationRepo aut;
		
	public boolean validateEmail(String mail) 
	{
	    String ans = aut.findEmail(mail);
	    
	    if(ans==null) 
	    {
	        return true;
	    }
	    else 
	    {
	        return false;
	    }
	}
	
	public int getRegisteredId(String registeredMail)
	{
		UserRegistrationDetails ude= this.aut.findCompelete(registeredMail);
		 int i= ude.getId();
		 return i;
	}
}
