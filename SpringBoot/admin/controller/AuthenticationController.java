package com.boot.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.admin.model.AdminLoginDetails;
import com.boot.admin.model.AdminRegistrationDetails;
import com.boot.admin.service.AdminRegistrationService;
import com.boot.api.service.TimeService;
import com.boot.config.sec.AdminDetailsService;
import com.boot.customExceptions.LoginError;
import com.boot.customExceptions.RegistrationError;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/PasswordManager/Admin/Authentication")
public class AuthenticationController 
{
	
	@Autowired
	TimeService ts;
	
	@Autowired
	AdminRegistrationService ars;        
	
	@Autowired
	BCryptPasswordEncoder passEnc;
	
	@PostMapping("/saveAdminInfo")
	public String saveAdminInfo(RedirectAttributes ra,@RequestParam("Afname")String fname,
										@RequestParam("Alname")String lname,
										@RequestParam("Aemail")String email,
										@RequestParam("Aphone")String phone,
										@RequestParam("ACpassword")String pass ,
										AdminRegistrationDetails ard,HttpSession ses,
										AdminLoginDetails ald) throws RegistrationError{
		
		String time = this.ts.fetchCurrentTimeFromServer();
		ard.setMailId(email);
		ard.setPassword(pass);
		ard.setPhone(phone);
		ard.setCreateDate(time);
		ard.setName(fname+lname);
		AdminRegistrationDetails adr= this.ars.saveData(ard,ald);

		if(adr!=null)
		{
			 ra.addFlashAttribute("notificationType", "success");
		     ra.addFlashAttribute("notificationMessage", "Account registration completed successfully. Try Login!");
		  return "redirect:/PasswordManager/Admin/AdminSignIn";
		}
		else
		{
			throw new RegistrationError("Registration Error", "REGISTRATION_ERROR");
		}
	}
	
	
	@Autowired
	AdminDetailsService loginCredentials;
	
	@PostMapping("/AdminLogin")
	public String authenticateAdmin( @RequestParam("email") String username, @RequestParam("password") String password, HttpSession session) throws LoginError 
	{
	    UserDetails login = this.loginCredentials.loadUserByUsername(username);
	    System.out.println(login.getUsername() + ":" + login.getPassword());
	    System.out.println(username + "::" + password);

	    if (login.getUsername().equals(username) && passEnc.matches(password, login.getPassword())) 
	    {
	        session.setAttribute("loginmail", login.getUsername()); 
	        return "redirect:/PasswordManager/Admin/Charts/basicInfo";
	    }
	    else 
	    {
	    	throw new LoginError("Mismatched Pin Credentials","PIN_ERROR");
	    }
	}

}
