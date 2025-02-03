package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boot.dao.PasswordRepo;
import com.boot.dao.RegistrationRepo;
import com.boot.encryption.UserDetailsEncryption;
import com.boot.models.UserPasswords;
import com.boot.models.UserRegistrationDetails;

@Service
public class AddPasswordService 
{

	@Autowired
	PasswordRepo pro;
	
	@Autowired
	RegistrationRepo rpo;
	
	UserDetailsEncryption uds = new UserDetailsEncryption();
	String encryptedPassword,decryptedPassword;
	
	public boolean pinForAddPasswords(String loginpin,String registeredMail) 
	{
		
		UserRegistrationDetails ude = this.rpo.findCompelete(registeredMail);
		String userPin = ude.getCpin();
		
		if(userPin!=null && userPin.equals(loginpin))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public UserPasswords insertUserPasswords(UserPasswords ups)
	{
		String normalPassword = ups.getPassword();
		String encryption = encryptPassword(normalPassword);
		ups.setPassword(encryption);
		
		String decryption = decryptPassword(encryption);
		
		System.out.println(encryption+":"+decryption);
		
		 this.pro.save(ups);
		return ups;
	}
	
	public String encryptPassword(String normPass)
	{
		
		try 
		{
			encryptedPassword = uds.encrypt(normPass);
			return encryptedPassword;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return normPass;
		}
	}
	
	public String decryptPassword(String encpass)
	{
		encryptedPassword=encpass;
		try
		{
			decryptedPassword=uds.decrypt(encryptedPassword);
			return decryptedPassword;
		}
		catch (Exception e) 
		{
			return encryptedPassword;
		}
	}
}
