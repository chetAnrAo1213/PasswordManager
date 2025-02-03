package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.PasswordRepo;
import com.boot.models.UserPasswords;

@Service
public class UpdatePasswordService {

    @Autowired
    private PasswordRepo pro;
    
    @Autowired
    AddPasswordService aps;
    
	String encryptedPassword,decryptedPassword;

	public int updateById(UserPasswords ups, int id) 
	{
		String normal=ups.getPassword();
		String pass = this.aps.encryptPassword(normal);
		ups.setPassword(pass);
		return pro.updateAllById(id, ups.getTitle(), ups.getAbout(), ups.getTags(), ups.getCreateddate(), 
				ups.getPhone(), ups.getPin(), ups.getUsername(), ups.getMailid(), ups.getPassword());
	}
	
}
