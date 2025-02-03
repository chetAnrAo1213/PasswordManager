package com.boot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.QuestionsRepo;
import com.boot.models.UserRegistrationDetails;


@Service
public class QuestionService 
{

	@Autowired
	QuestionsRepo qpo;
	
	public boolean validateEmailAndQuestions(String email,String fq,String sq,String tq)
	{
		String usermail=this.qpo.findEmail(email);
		if(usermail!=null && usermail.equals(email))
		{
			UserRegistrationDetails ur = this.qpo.findAnswers(email);
			String a=(ur.urq.getFq());
			String b= (ur.urq.getSq());
			String c = (ur.urq.getTq());
			if(a.equals(fq) && b.equals(sq) && c.equals(tq))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public boolean validatePin(String email,String pin)
	{
		
		String useremail=this.qpo.findEmail(email);
		
		if(useremail!=null && useremail.equals(email))
		{
			String userpin = this.qpo.findPin(email);
			System.out.println(userpin);
			if(userpin!=null && userpin.equals(pin))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		
	}
}
