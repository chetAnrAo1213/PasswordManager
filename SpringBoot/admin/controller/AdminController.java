package com.boot.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.service.InternetConnectivityService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/PasswordManager/Admin")
public class AdminController 
{

	@Autowired
	InternetConnectivityService ics;
	
	@GetMapping("/")
	public String index()
	{
		return "Admin/index";
	}
	
	@GetMapping("/AdminRegister")
	public String AdminRegister()
	{
		return "Admin/SignUp";
	}
	
	@GetMapping("/AdminSignIn")
	public String AdminSignIn()
	{
		return "Admin/signIn";
	}
	
	@GetMapping("/AdminGet")
	public String get()
	{
		return "Admin/getpage";
	}
	
	@GetMapping("/AdminHome")
	public String home()
	{
		return "Admin/home";
	}
	
	@GetMapping("/DeleteUser")
	public String DeleteUser()
	{
		return "Admin/DeleteUserFromSystem";
	}
	
	@GetMapping("/UserReportAI")
	public String UserReportAI(Model m)
	{
		boolean internetAccess = this.ics.validateInternetConnection();

		if(internetAccess) 
		{
			return "Admin/UserReportWithAI";
		}
		else 
		{
			m.addAttribute("ErrorName", "NO_INTERNET_CONNECTION");
			m.addAttribute("Error", "Please Make sure you have stable Internet Connection. Try Again!");
			return "/error";
		}
		
	}
	
	@GetMapping("/logout")
	public String adminLogout(HttpSession ses)
	{
		ses.invalidate();
		return "redirect:/Admin/index";
	}
	
}
