package com.boot.admin.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.admin.service.DeleteUserService;

@Controller
@RequestMapping("/PasswordManager/Admin/DeleteUser/")
public class DeleteUserController 
{
	@Autowired 
	DeleteUserService dus;
	
	@PostMapping("/deleteByUserMailId")
	public String deleteUser(@RequestParam("usrDelete") String userMail,Model m)
	{
		int i = this.dus.deleteUserFromTables(userMail);
		if(i!=0)
		{
			 m.addAttribute("notificationType", "success");
		        m.addAttribute("notificationMessage", "User Deleted Successfully! Please verify from the list.");
			return "/Admin/DeleteUserFromSystem";	
		}
		else
		{
			return "/error";
		}
		
		
	}
}
