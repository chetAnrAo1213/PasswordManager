package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.api.models.ApiRecords;
import com.boot.api.repo.ApiCallsRepo;
import com.boot.dao.NotesRepo;
import com.boot.dao.QuestionsRepo;
import com.boot.dao.RegistrationRepo;
import com.boot.models.QuickNote;
import com.boot.models.UserRegistrationDetails;
import com.boot.models.UserRegistrationQuestions;

@Service
public class ProfileService 
{
	
	@Autowired
	RegistrationRepo rp;
	
	@Autowired
	QuestionsRepo qrp;
	
	@Autowired
	NoteService ns;
	
	@Autowired
	ApiCallsRepo acr;
	
	public UserRegistrationDetails getUserRegistrationDetails(String registrationMail)
	{
		return this.rp.findCompelete(registrationMail);
	}
	
	public UserRegistrationQuestions getUserSecurityDetails(String registrationMail)
	{
		UserRegistrationDetails ude = this.qrp.findAnswers(registrationMail);
		UserRegistrationQuestions ans = ude.getUrq();
		return ans;
	}
	
	public QuickNote getUserNoteDetails(String registrationMail)
	{
		return this.ns.getUserNote(registrationMail);
	}
	 
	public ApiRecords getApiDetailsOfUser(String registrationMail)
	{
		return this.acr.findCompelete(registrationMail);
	}
}
