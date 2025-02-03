package com.boot.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.admin.service.UserSpecificService;

@Controller
@RequestMapping("/PasswordManager/Admin/UserSpecifics")
public class UserSpecificController 
{
	
	@Autowired
	UserSpecificService uss;
	
	@GetMapping("/getLists")
	public String getUsersList(Model m)
	{
		List<String> a= this.uss.getRegisterMailIds();
		m.addAttribute("users", a);
		
		return "Admin/UserSpecificDetails";
	}
	
	@PostMapping("/getUserData")
	public String getUserData(@RequestParam("usermail") String mailId,Model m)
	{
		Map<String,Object> user = this.uss.getSpecificUserData(mailId);
		
		m.addAttribute("Registration", user.get("Registration"));
		m.addAttribute("Questions", user.get("Questions"));
		m.addAttribute("Note", user.get("Note"));
		m.addAttribute("Api", user.get("Api"));
		m.addAttribute("userStoredPasswords", user.get("userStoredPasswords"));

		
		return "Admin/UserDataOfSpecificEmail";
	}
}
