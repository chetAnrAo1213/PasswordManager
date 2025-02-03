package com.boot.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.admin.service.ChartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("PasswordManager/Admin/Charts/")
public class ChartController 
{

	@Autowired
	ChartService chs;
	
	
	@GetMapping("/basicInfo")
	public String getBasicCountDisplay(HttpSession ses,Model m)
	{
	
		Map<String, Long> count = this.chs.getCountofAllEntites();
		ses.setAttribute("registrations", count.get("registrations"));
		ses.setAttribute("questions", count.get("questions"));
		ses.setAttribute("logins", count.get("logins"));
		ses.setAttribute("notes", count.get("notes"));
		ses.setAttribute("passwords", count.get("passwords"));
		ses.setAttribute("apiRequests", count.get("apiRequests"));
		
		return "redirect:/PasswordManager/Admin/AdminHome"; //homepage
	}
}
