package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.QuestionsRepo;
import com.boot.dao.RegistrationRepo;
import com.boot.models.UserLoginDetails;
import com.boot.models.UserRegistrationDetails;

@Service
public class ResetPasswordService 
{
	@Autowired
	QuestionsRepo qpo;
	
	@Autowired
	RegistrationRepo rpo;
	
	UserRegistrationDetails userpersonals;
	
	public boolean validateForResetPassword(String emailId, UserRegistrationDetails ude)
	{
		String usermail = this.qpo.findEmail(emailId);
		
		if(usermail!=null && usermail.equals(emailId))
		{
			userpersonals =this.rpo.findCompelete(emailId);
			this.getPersonalData();
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public UserRegistrationDetails getPersonalData()
	{
		return userpersonals;
	}
	
	public int UpdatePasswordAndPin(String userpassword,String userpin, String usermail,UserLoginDetails uld)
	{
		int i = this.rpo.updatePasswordAndPin(userpassword,userpin,usermail);
		 
		 this.rpo.modifyPasswordAndPin(userpassword, userpin, usermail);
		return i;
	}
}
