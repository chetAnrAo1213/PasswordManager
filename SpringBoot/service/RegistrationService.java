package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.api.models.ApiRecords;
import com.boot.api.repo.ApiCallsRepo;
import com.boot.dao.LoginRepo;
import com.boot.dao.RegistrationRepo;
import com.boot.models.QuickNote;
import com.boot.models.UserLoginDetails;
import com.boot.models.UserRegistrationDetails;

@Service
public class RegistrationService 
{
	@Autowired
	RegistrationRepo rpo;
	
	@Autowired
	LoginRepo lpo;
	
	@Autowired
	ApiCallsRepo acr;
	

	public boolean insertUserData(UserRegistrationDetails ude,UserLoginDetails ud,ApiRecords api,QuickNote qn)
	{
		UserRegistrationDetails user = this.rpo.save(ude);
		String mail =user.getEmail();
		String password = user.getCpassword();
		String pin = user.getCpin();
		
		ud.setEmail(mail);
		ud.setPassword(password);
		ud.setPin(pin);
		UserLoginDetails userLogin =  this.lpo.save(ud);
		
		api.setRegisterMail(mail);
		api.setNewsTimeStamp("0");
		api.setNextTimeStamp("0");
		api.setNewsRequestCalls(0);
		api.setApodRequestCalls(0);
		api.setPexelRequestCalls(0);
		api.setApodSearchKey("none");
		api.setPexelSearchKey("none");
		api.setRegisterMailId(ude.getId());
		
		qn.setContent(" ");
		qn.setCreateDate(" ");
		qn.setLastModifiedDate(" ");
		qn.setTags(" ");
		qn.setTitle(" ");
		qn.setRegisteredusermailid(ude.getId());
		qn.setRegisteredusermail(mail);
		qn.setNoteCount(1);
		this.acr.save(api);
		
		if(user==null && userLogin ==null)
			return false;
		else
			return true;
		
	}
}
