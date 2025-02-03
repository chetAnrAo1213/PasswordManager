package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.PasswordRepo;

@Service
public class DeletePasswordService 
{
	@Autowired
	PasswordRepo pro;
	
	public void deleteRecordById(int id)
	{
		this.pro.deleteById(id);
	}

}
