package com.boot.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boot.dao.NotesRepo;
import com.boot.models.QuickNote;


@Service
public class NoteService 
{
	
	@Autowired
	NotesRepo nr;
	
	public QuickNote insertRecord(String title, String tags, String content, String registerMail, QuickNote q1, int registeredMailId) {
	    q1.setTitle(title); 
	    q1.setTags(tags);   
	    q1.setContent(content); 
	    q1.setRegisteredusermail(registerMail); 
	    q1.setRegisteredusermailid(registeredMailId); 
	    q1.setLastModifiedDate(""); 
	    q1.setNoteCount(1); 
	    return this.nr.save(q1); 
	}
	
	public QuickNote updateRecord(String title, String tags, String content, String registerMail, int registeredMailId) 
	{
		QuickNote q = this.nr.findNote(registerMail);
		
		q.setContent(content);
		q.setTitle(title);
		q.setTags(tags);
	    q.setLastModifiedDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+""); 
	    this.nr.updateNote(registeredMailId, q.getTitle(),q.getTags(),q.getContent(),q.getLastModifiedDate());
		return q;
	}
	
	public int saveOrUpdate(String title, String tags, String content, String registerMail, QuickNote q1, int registeredMailId)
	{
		
		Integer noteCount = this.nr.findNoteCount(registerMail);
		if(noteCount==0)
		{
			System.out.println("Note Count INS");
			this.nr.updateNoteCount(registerMail, 1);
			this.insertRecord(title, tags, content, registerMail, q1, registeredMailId);
		}
		else
		{
			System.out.println("Note Count Update");
			this.updateRecord(title, tags, content, registerMail, registeredMailId);
		}
		
		return noteCount;
	}

	public QuickNote getUserNote(String registermail)
	{
		QuickNote q1 = this.nr.findNote(registermail);
		return q1;
	}

}
