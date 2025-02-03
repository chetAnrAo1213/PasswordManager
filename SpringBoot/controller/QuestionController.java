package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.customExceptions.LoginError;
import com.boot.customExceptions.QuestionsError;
import com.boot.service.AuthService;
import com.boot.service.QuestionService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/AuthenticationQuestions")
public class QuestionController 
{

	@Autowired
	QuestionService qus;
	
	@Autowired
	AuthService aus;
	
	@GetMapping("/QuestionsValidation")
	public String QuestionsValidation()
	{
		return "/UserAuthentication/QuestionsValidation";
	}
	
	@GetMapping("/ForgotPinValidation")
	public String forgotPinPage()
	{
		return "/UserAuthentication/ForgotPinValidation";
	}
	
	@PostMapping("/validateUserFromQuestions")
	public String validateUserFromQuestions(@RequestParam("email") String emailid, 
											@RequestParam("fq") String first,
			                                @RequestParam("sq") String second, 
			                                @RequestParam("tq") String third,
			                                HttpSession ses) throws QuestionsError {
		boolean user = this.qus.validateEmailAndQuestions(emailid,first,second,third);
		
		if(user)
		{
			ses.setAttribute("RegisteredMail", emailid);
			int id= this.aus.getRegisteredId(emailid);
			ses.setAttribute("RegisteredMailId", id);
			return "redirect:/PasswordManager/home";
		}
		else
		{
			throw new QuestionsError("Question Error.","QUESTION_ERROR");
		}
	}
	
	@PostMapping("/validateUserFromPin")
	public String validateUserFromPin(@RequestParam("email") String emailid, @RequestParam("pin") String pins,HttpSession ses) throws LoginError
	{

		boolean user = this.qus.validatePin(emailid,pins);		
		if(user)
		{
			ses.setAttribute("RegisteredMail", emailid);
			int id= this.aus.getRegisteredId(emailid);
			ses.setAttribute("RegisteredMailId", id);
			return "redirect:/PasswordManager/home";
		}
		else
		{
			throw new LoginError("Mismatched Password","PIN_ERROR");
		}
	}
}
