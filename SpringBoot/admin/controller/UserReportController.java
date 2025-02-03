package com.boot.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.admin.service.UserSpecificService;
import com.boot.service.InternetConnectivityService;

@Controller
@RequestMapping("/PasswordManager/Admin/UserReport/")
public class UserReportController 
{

	@Autowired
	UserSpecificService uss;
	@Autowired
	InternetConnectivityService ics;
	
	@PostMapping("/fetchAiReportForUser")
	public String userAiReport(@RequestParam("usermail") String mail, @RequestParam("ai") String aimodel, Model m) 
	{
		boolean internetAccess = this.ics.validateInternetConnection();

		if(internetAccess) 
		{
			String aiReport = this.uss.getUserAnalysisFromAI(mail, aimodel);
		    
		    String formattedResponse = aiReport.replace("**", "<h6>").replace("*", "</h6>");
		    formattedResponse = formattedResponse.replace("\n- ", "<li>") 
		                                         .replace("\n", "<br>") 
		                                         .replace(":", ":<br>"); 
		    formattedResponse = formattedResponse.replaceFirst("<li>", "<ul><li>") + "</ul>";

		    m.addAttribute("AiAnalysisReport", formattedResponse);
		    return "Admin/UserReportWithAI";
		}
		else 
		{
			m.addAttribute("ErrorName", "NO_INTERNET_CONNECTION");
			m.addAttribute("Error", "Please Make sure you have stable Internet Connection. Try Again!");
			return "/error";
		}
	    
	}

}
