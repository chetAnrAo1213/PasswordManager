package com.boot.admin.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.boot.api.repo.ApiCallsRepo;
import com.boot.dao.LoginRepo;
import com.boot.dao.NotesRepo;
import com.boot.dao.PasswordRepo;
import com.boot.dao.QuestionsRepo;
import com.boot.dao.RegistrationRepo;

@Service
public class ChartService 
{

	ApiCallsRepo apicalls;
	LoginRepo logins;
	NotesRepo notes;
	PasswordRepo passwords;
	QuestionsRepo questions;
	RegistrationRepo reistrations;
	
	public ChartService(ApiCallsRepo apicalls, LoginRepo logins, NotesRepo notes, PasswordRepo passwords, QuestionsRepo questions, RegistrationRepo reistrations) 
	{
		this.apicalls = apicalls;
		this.logins = logins;
		this.notes = notes;
		this.passwords = passwords;
		this.questions = questions;
		this.reistrations = reistrations;
	}

	public Map<String,Long> getCountofAllEntites()
	{
		HashMap<String, Long> counts = new LinkedHashMap<>();
		long apiRequestCount = apicalls.count(); //also use countby()
		long loginCount     = logins.count();
		long notesCount = notes.count();
		long passwordsCount = passwords.count();
		long questionsCount = questions.count();
		long registrationsCount = reistrations.count();
		
		counts.put("registrations", registrationsCount);
		counts.put("questions", questionsCount);
		counts.put("logins", loginCount);
		counts.put("notes", notesCount);
		counts.put("passwords", passwordsCount);
		counts.put("apiRequests", apiRequestCount);
		System.out.println(counts.entrySet());
		return counts;
	}
	
}
