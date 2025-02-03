package com.boot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.models.QuickNote;
import com.boot.service.NoteService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/QuickNote")
public class NoteController 
{

	
		//TODO: also implement UI for update and delete in the view page
		//TODO: give an suggestion that in 1 note you should give crud operations.
		//TODO: 
		//TODO: use query hints and auditing as well.
	@Autowired
	NoteService ns;
	
	@PostMapping("/addNote")
	public String addNote(@RequestParam("NoteTitle") String title,
	                      @RequestParam("Notetags") String tags,
	                      @RequestParam("NoteContent") String content,
	                      HttpSession ses, QuickNote qn,Model m) 
	{
	    String registeredMail = (String) ses.getAttribute("RegisteredMail");
	    int registeredMailId = (Integer) ses.getAttribute("RegisteredMailId");
	    System.out.println("RegisteredMail: " + registeredMail + " :: RegisteredMailId: " + registeredMailId);
	    
	    this.ns.saveOrUpdate(title, tags, content, registeredMail, qn, registeredMailId);
	    m.addAttribute("notificationType", "success");
        m.addAttribute("notificationMessage", "Note added/updated successfully.");
	    return "/QuickNote/Note_Index";
	}
	
	@PostMapping("/getNote")
	public String displayNote(HttpSession ses,Model m) 
	{
	    String registeredMail = (String) ses.getAttribute("RegisteredMail");
	    QuickNote qn = this.ns.getUserNote(registeredMail);
	    m.addAttribute("NoteUser", qn.getRegisteredusermail());
	    m.addAttribute("NoteTitle", qn.getTitle());
	    m.addAttribute("NoteTags", qn.getTags());
	    m.addAttribute("NoteContent", qn.getContent());
	    return "/QuickNote/Note_Index";
	}
}
