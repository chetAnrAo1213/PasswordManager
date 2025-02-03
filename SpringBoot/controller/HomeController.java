package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.service.InternetConnectivityService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/PasswordManager")
public class HomeController 
{
	
	@Autowired
	InternetConnectivityService ics;
	
	@GetMapping("/Module")
	public String getModule()
	{
		return "PasswordManager";
	}
	@GetMapping("/")
	public String index()
	{
		return ("index");
	}
	
	@GetMapping("/home")
	public String home()
	{
		return ("home");
	}
	
	
	@GetMapping("/signIn")
	public String signIn()
	{
		return ("signIn");
	}
	
	
	
	@GetMapping("/SignUp")
	public String SignUp()
	{
		return ("SignUp");
	}
	
	@GetMapping("/ForgotPassword")
	public String ForgotPassword()
	{
		return new String("/UserAuthentication/ForgotPassword");
	}
	
	@GetMapping("/ChatBot")
	public String ChatBot(Model m)
	{

		boolean internetAccess = this.ics.validateInternetConnection();

		if(internetAccess) 
		{
			return new String("ChatBot");
		}
		else 
		{
			m.addAttribute("ErrorName", "NO_INTERNET_CONNECTION");
			m.addAttribute("Error", "Please Make sure you have stable Internet Connection. Try Again!");
			return "/error";
		}
	}
	
	@GetMapping("/Note")
	public String noteIndex() {
		return new String("QuickNote/Note_Index");
	}

	
	@GetMapping("/News")
	public String NewsIndex(Model m) 
	{
		boolean internetAccess = this.ics.validateInternetConnection();
		
		if(internetAccess) 
		{
			return "Api/NewsIndex";
		}
		else 
		{
			m.addAttribute("ErrorName", "NO_INTERNET_CONNECTION");
            m.addAttribute("Error", "Please Make sure you have stable Internet Connection. Try Again!");
			return "/error";
		}

	}

	
	@GetMapping("/NASAAPOD")
	public String APODIndex(Model m) 
	{
		boolean internetAccess = this.ics.validateInternetConnection();
		
		if(internetAccess) 
		{
			return new String("Api/ApodIndex");
		}
		else 
		{
			m.addAttribute("ErrorName", "NO_INTERNET_CONNECTION");
            m.addAttribute("Error", "Please Make sure you have stable Internet Connection. Try Again!");
			return "/error";
		}
		
	}
	
	@GetMapping("/PexelVideo")
	public String PexelIndex(Model m) 
	{
		boolean internetAccess = this.ics.validateInternetConnection();
		
		if(internetAccess) 
		{
			return new String("Api/PexelVideos");
		}
		else 
		{
			m.addAttribute("ErrorName", "NO_INTERNET_CONNECTION");
            m.addAttribute("Error", "Please Make sure you have stable Internet Connection. Try Again!");
			return "/error";
		}
		
	}
	
	
	
	@GetMapping("/logout")
	public String ConfirmPin(HttpSession ses)
	{
		ses.invalidate();
		return "redirect:/PasswordManager/";
	}
	
}
