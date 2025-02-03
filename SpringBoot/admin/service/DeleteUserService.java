package com.boot.admin.service;

import org.springframework.stereotype.Service;
import com.boot.api.repo.ApiCallsRepo;
import com.boot.dao.LoginRepo;
import com.boot.dao.NotesRepo;
import com.boot.dao.PasswordRepo;
import com.boot.dao.QuestionsRepo;
import com.boot.dao.RegistrationRepo;

import com.boot.models.UserRegistrationDetails;

@Service
public class DeleteUserService 
{
	ApiCallsRepo apicalls;
	LoginRepo logins;
	NotesRepo notes;
	PasswordRepo passwords;
	QuestionsRepo questions;
	RegistrationRepo reistrations;
	
	public DeleteUserService(ApiCallsRepo apicalls, LoginRepo logins, NotesRepo notes, PasswordRepo passwords, QuestionsRepo questions, RegistrationRepo reistrations) 
	{
		this.apicalls = apicalls;
		this.logins = logins;
		this.notes = notes;
		this.passwords = passwords;
		this.questions = questions;
		this.reistrations = reistrations;
	}
	
	
	public int deleteUserFromTables(String mailId)
	{
		UserRegistrationDetails registrationDetails = this.reistrations.findCompelete(mailId);

		int userRegisId = registrationDetails.getId();
		if(userRegisId!=0) 
		{
		 this.reistrations.deleteById(userRegisId);
		 this.questions.deleteById(userRegisId);
		 this.logins.deleteById(userRegisId);
		 this.notes.deleteNoteThroughRegisterId(userRegisId);
		 this.apicalls.deleteApiRecordsByRegisterId(userRegisId);
		 this.passwords.deleteUserPasswordsByRegisterId(userRegisId);
		   return 1;
		}
		else
		{
			return 0;
		}
		
	}
}
