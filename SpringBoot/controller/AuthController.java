package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.api.models.ApiRecords;
import com.boot.customExceptions.LoginError;
import com.boot.customExceptions.RegistrationError;
import com.boot.models.QuickNote;
import com.boot.models.UserLoginDetails;
import com.boot.models.UserRegistrationDetails;
import com.boot.models.UserRegistrationQuestions;
import com.boot.service.AuthService;
import com.boot.service.LoginService;
import com.boot.service.RegistrationService;
import com.boot.service.ResetPasswordService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Authentication")
public class AuthController {

	@Autowired
	AuthService aus;

	@Autowired
	RegistrationService regs;

	@Autowired
	LoginService lgs;

	@Autowired
	ResetPasswordService rps;

	@GetMapping("/verifyEmail")
	public String emailVerification()
	{
		return "UserAuthentication/EmailValidation";
	}
	
	@GetMapping("/ResetPassword")
	public String ResetPassword()
	{
		return new String("UserAuthentication/ResetPassword");
	}
	
	@GetMapping("/ForgotQuestions")
	public String ForgotQuestions()
	{
		return new String("UserAuthentication/ForgotQuestions");
	}

	@PostMapping("/emailDBCheck")
	public String validateEmail(@RequestParam("email") String mailId, @ModelAttribute UserRegistrationDetails ude,Model m) 
	{
		boolean res = aus.validateEmail(mailId);
		m.addAttribute("MailId", mailId);
		if (res) 
		{
			return "redirect:/PasswordManager/SignUp";
		}
		else 
		{
			m.addAttribute("userAlreadyRegistrated", "Email is already registered in our system. Try Login");
			return "UserAuthentication/EmailValidation";
		}
	}

	@PostMapping("/saveUserDetails")
	public String saveUserDetails(@ModelAttribute UserRegistrationDetails ude, Model m, HttpSession session) 
	{
		session.setAttribute("UserRegistrationDetails", ude);
		session.setAttribute("email", ude.getEmail());
		m.addAttribute("UserRegistrationDetails", ude);
		return "UserAuthentication/ForgotQuestions";
	}

	@PostMapping("/postUserCompleteData")
	public String postUserCompleteData(Model m,RedirectAttributes ra, @ModelAttribute UserRegistrationQuestions urq, HttpSession session,UserLoginDetails uld,ApiRecords api,QuickNote qn) throws RegistrationError
	{
		UserRegistrationDetails ude = (UserRegistrationDetails) session.getAttribute("UserRegistrationDetails");
		ude.setUrq(urq);
		if (!(this.aus.validateEmail(ude.getEmail()))) 
		{
			m.addAttribute("userAlreadyRegistrated", "Email is already registered in our system. Try Login");
			return "UserAuthentication/EmailValidation";
		}
		else 
		{
			boolean post = this.regs.insertUserData(ude, uld,api,qn);
			if(post) 
			{
				
				 ra.addFlashAttribute("notificationType", "success");
			     ra.addFlashAttribute("notificationMessage", "Account registration completed successfully. Try Login!");
				return "redirect:/PasswordManager/signIn";
			}
			else 
			{
				throw new RegistrationError("Registration Error", "REGISTRATION_ERROR");
			}
		}
	}

	@PostMapping("/authenticateUserByPassword")
	public String authenticateUserByPassword(@RequestParam("email") String mailid, @RequestParam("password") String passwords, Model m, HttpSession ses) throws LoginError 
	{
		boolean validate = this.lgs.authenticatePassword(mailid, passwords);
		ses.setAttribute("LoginMail", mailid);
		if(!validate) 
		{
	        throw new LoginError("Mismatched Password Credentials","PASSWORD_ERROR");
		}
		else 
		{
			return "UserAuthentication/Pin";
		}
	}

	@PostMapping("/authenticateUserByPin")
	public String authenticateUserByPin(@RequestParam("pin") String pins, HttpSession ses,Model m) throws LoginError 
	{
		String loginMail = (String) ses.getAttribute("LoginMail");
		if (loginMail == null) 
		{
			throw new LoginError("Mismatched Pin Credentials","PIN_ERROR");
		} 
		else 
		{
			boolean ans = lgs.authenticatePin(loginMail, pins);
			if(ans) 
			{
				ses.setAttribute("RegisteredMail", loginMail);
				int id= this.aus.getRegisteredId(loginMail);
				ses.setAttribute("RegisteredMailId", id);
				m.addAttribute("RegisteredMail", loginMail);
				return "redirect:/PasswordManager/home";
			}
			else 
			{
				throw new LoginError("Mismatched Pin Credentials","PIN_ERROR");
			}
		}
	}

	@PostMapping("/resetPasswordForUser")
	public String resetPasswordForUser(@RequestParam("email") String emailid, Model m, UserRegistrationDetails ude,HttpSession ses) 
	{
		boolean validEmail = this.rps.validateForResetPassword(emailid, ude);
		if (validEmail) {
			ude = this.rps.getPersonalData();
			m.addAttribute("resetMail", ude.getEmail());
			m.addAttribute("resetFname", ude.getFname());
			m.addAttribute("resetLname", ude.getLname());
			m.addAttribute("resetPhone", ude.getPhone());
			ses.setAttribute("resetMailIdSes", emailid);
			return "UserAuthentication/UpdatePasswordAndPin";
		} else {
			m.addAttribute("ResetMesseage", "Email is not registered in our system. Please check here once & we'll redirect you to SignUp page.");
			return "UserAuthentication/EmailValidation";
		}
	}

	@PostMapping("/updatePasswordAndPinForUser")
	public String updatePasswordAndPinForUser(Model m, @RequestParam("Cpassword") String password, 
			@RequestParam("cpin") String pin, HttpSession ses,UserLoginDetails uld,RedirectAttributes ra) throws RegistrationError  
	{
		String useremail = (String) ses.getAttribute("resetMailIdSes");
		int i = this.rps.UpdatePasswordAndPin(password, pin, useremail,uld);

		if (i == 0) 
		{
			m.addAttribute("alertMessage", "Failed to update password and PIN. Please try again.");
			m.addAttribute("alertType", "alert-danger"); 
			throw new RegistrationError("Registration Update Password Pin Error", "UPDATE_PASSWORD_PIN_ERROR");
		}
		else 
		{
			ra.addFlashAttribute("notificationType", "success");
		     ra.addFlashAttribute("notificationMessage", "Passwords & Pin are successfully updated for "+useremail+" . Try Login!");
			return "redirect:/PasswordManager/signIn";
		}
	}

}
