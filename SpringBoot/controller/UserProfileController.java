package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.api.models.ApiRecords;
import com.boot.models.QuickNote;
import com.boot.models.UserRegistrationDetails;
import com.boot.models.UserRegistrationQuestions;
import com.boot.service.ProfileService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/PasswordManager")
public class UserProfileController 
{
	
	@Autowired 
	ProfileService ps;
	
	@GetMapping("/Profile")
	public String displayUserBasicDetails(HttpSession ses,Model m)
	{
		String registerMail = (String) ses.getAttribute("RegisteredMail");
		System.out.println(registerMail);
		UserRegistrationDetails userDetails = this.ps.getUserRegistrationDetails(registerMail);
		QuickNote userNote = this.ps.getUserNoteDetails(registerMail);
		System.out.println(userNote);
		if(userDetails !=null)
		{
			m.addAttribute("RegistrationId", userDetails.getId());
			m.addAttribute("FirstName", userDetails.getFname());
			m.addAttribute("LastName", userDetails.getLname());
			m.addAttribute("Email", userDetails.getEmail());
			m.addAttribute("Phone", userDetails.getPhone());
			m.addAttribute("Password", userDetails.getCpassword());
			m.addAttribute("Pin", userDetails.getCpin());

			m.addAttribute("FQ", userDetails.getUrq().getFq());
			m.addAttribute("SQ", userDetails.getUrq().getSq());
			m.addAttribute("TQ", userDetails.getUrq().getTq());

			if(userNote!=null) 
			{
				m.addAttribute("noteId", userNote.getId());
				m.addAttribute("noteCR", userNote.getCreateDate());
				m.addAttribute("noteTI", userNote.getTitle());
				m.addAttribute("noteTA", userNote.getTags());
				m.addAttribute("noteLD", userNote.getLastModifiedDate());
				m.addAttribute("noteCO", userNote.getContent());
			}
			else
			{
				m.addAttribute("note", "No Note Found");
			}

		}



		return "/UserProfile/Index";
	}
	
	@GetMapping("/ApiUsage")
	public String ApiUsage(HttpSession ses,Model m)
	{
		String registerMail = (String) ses.getAttribute("RegisteredMail");
		ApiRecords api =  this.ps.getApiDetailsOfUser(registerMail);
		if(api!=null) 
		{
			//news
		   m.addAttribute("NewsTimeCurrent", api.getNewsTimeStamp());
		   m.addAttribute("NewsTimeNext", api.getNextTimeStamp());
		   m.addAttribute("NewsKey", api.getSearchKey());
		   m.addAttribute("NewsTimeRequests", api.getNewsRequestCalls());
		   
		   //apod
		   
		   m.addAttribute("apodKey", api.getApodSearchKey());
		   m.addAttribute("apodCalls", api.getApodRequestCalls());
		   
		   
		   m.addAttribute("pexelKey", api.getPexelSearchKey());
		   m.addAttribute("pexelCalls", api.getPexelRequestCalls());
		   m.addAttribute("RegID", api.getRegisterMail());
		   m.addAttribute("RegName", api.getRegisterMailId());
		}
		else
		{
			m.addAttribute("api", "error"); 
		}
		return "/UserProfile/ApiUsage";
	}
}
