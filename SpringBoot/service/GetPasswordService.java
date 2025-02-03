package com.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.dao.PasswordRepo;
import com.boot.dao.RegistrationRepo;
import com.boot.encryption.UserDetailsEncryption;
import com.boot.models.UserPasswords;

@Service
public class GetPasswordService 
{
	@Autowired
	PasswordRepo pro;
	
	@Autowired
	RegistrationRepo rpo;
	
	UserDetailsEncryption uds = new UserDetailsEncryption();
	String noramlPass,encpass;
	
	public List<UserPasswords> getPasswordData(String usermailId)
	{
		List<UserPasswords> ups= this.pro.findUserPasswords(usermailId);
		return ups;
	}
	

	
	public String applyDecryption(String password)
	{
		try 
		{
			noramlPass = uds.decrypt(password);
			return noramlPass;
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
			return password;
		}
	}
}
