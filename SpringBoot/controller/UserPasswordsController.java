package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.customExceptions.LoginError;
import com.boot.customExceptions.PasswordError;
import com.boot.models.UserPasswords;
import com.boot.service.AddPasswordService;
import com.boot.service.DeletePasswordService;
import com.boot.service.GetPasswordService;
import com.boot.service.UpdatePasswordService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/UserPasswords")
public class UserPasswordsController 
{

	@Autowired
	AddPasswordService aps;
	
	@Autowired
	GetPasswordService gps;
	
	@Autowired
	UpdatePasswordService us;
	
	@Autowired
	DeletePasswordService dps;
	
	@GetMapping("/AddPassword")
	public String AddPassword()
	{
		return new String("/UserPasswords/AddPassword");
	}
	
	@GetMapping("/UpdatePassword")
	public String UpdatePassword()
	{
		return new String("/UserPasswords/UpdatePassword");
	}
	
	@GetMapping("/DeletePassword")
	public String deletePassword()
	{
		return new String("/UserPasswords/DeletePassword");
	}
	
	@GetMapping("/GetPasswords")
	public String GetPasswords()
	{
		return new String("/UserPasswords/GetPasswords");
	}
	
	
	@GetMapping("/DecryptionPassword")
	public String DecryptionPassword()
	{
		return new String("/UserPasswords/DecryptionPassword");
	}
	
	
	
	@PostMapping("/checkPinForAddPasswords")
	public String pinForAddPasswords(@RequestParam("addpin") String pin,HttpSession ses) throws LoginError
	{
		String registeredMail = (String)ses.getAttribute("RegisteredMail");
		boolean addpincheck = this.aps.pinForAddPasswords(pin,registeredMail);
		if(addpincheck)
		{
			return "/UserPasswords/AddPassword";
		}
		else
		{
			throw new LoginError("Pin Mismatched","PIN_ERROR");
		}
	}
	 
	@PostMapping("/checkPinForUpdatePasswords")
	public String checkPinForUpdatePasswords(@RequestParam("updatepin") String pin,HttpSession ses) throws LoginError
	{
		String registeredMail = (String)ses.getAttribute("RegisteredMail");
		boolean addpincheck = this.aps.pinForAddPasswords(pin,registeredMail);
		if(addpincheck)
		{
			return "/UserPasswords/UpdatePassword";
		}
		else
		{
			throw new LoginError("Pin Mismatched","PIN_ERROR");
		}
	}
	
	@PostMapping("/checkPinFordeletePasswords")
	public String checkPinFordeletePasswords(@RequestParam("deletepin") String pin,HttpSession ses) throws LoginError
	{
		String registeredMail = (String)ses.getAttribute("RegisteredMail");
		boolean addpincheck = this.aps.pinForAddPasswords(pin,registeredMail);
		if(addpincheck)
		{
			return "/UserPasswords/DeletePassword";
		}
		else
		{
			throw new LoginError("Pin Mismatched","PIN_ERROR");
		}
	}
	
	
	@PostMapping("/checkPinForGetPasswords")
	public String checkPinForGetPasswords(@RequestParam("getpin") String pin,HttpSession ses,Model m) throws LoginError,PasswordError
	{
		String registeredMail = (String)ses.getAttribute("RegisteredMail");
		
		boolean addpincheck = this.aps.pinForAddPasswords(pin,registeredMail);
		if(addpincheck)
		{
			List<UserPasswords> ups =gps.getPasswordData(registeredMail);
			if(ups!=null) {
			  m.addAttribute("UserStoredPasswords", ups);
			  m.addAttribute("notificationType", "success");
		      m.addAttribute("notificationMessage", "Passwords Fetched successfully.");
			
			return "/UserPasswords/GetPasswords";
			}
			else
			{
				throw new PasswordError("View fail","VIEW_PASSWORD_ERROR");
			}
		}
		else
		{
			throw new LoginError("Pin Mismatched","PIN_ERROR");

		}
	}
	
	
	
	@PostMapping("/decryptingPassword")
	public String applyingDecryption(@RequestParam("encryptedCode") String password,Model m) throws PasswordError
	{
		String decryption = this.gps.applyDecryption(password);
		if(!decryption.isBlank())
		{
			  m.addAttribute("decryptionValue", decryption);
			  m.addAttribute("notificationType", "success");
		      m.addAttribute("notificationMessage", "Decryption Appiled successfully.");
		}
		else
		{
			throw new PasswordError("Decryption Error","DECRYPT_PASSWORD_ERROR");
		}
		  
		  return "/UserPasswords/decryptionPassword";
	}
	
//__________________________ Performs CRUD Operations on Passwords Table___________________________	
	@PostMapping("/AddPasswordsFromUser")
	public String addPasswords(@ModelAttribute UserPasswords ups, Model m, HttpSession session) throws PasswordError{
	    try 
	    {
	        String registeredMail = (String) session.getAttribute("RegisteredMail");
	        int registeredMailId = (Integer) session.getAttribute("RegisteredMailId");
	        ups.setRegisteredusermail(registeredMail);
	        ups.setRegisteredusermailid(registeredMailId);
	        UserPasswords add =  this.aps.insertUserPasswords(ups);
	        if(add!=null)
	        {
	        m.addAttribute("notificationType", "success");
	        m.addAttribute("notificationMessage", "Password added successfully.");
	        }
	        else
	        {
	        	throw new PasswordError("Add Error","ADD_PASSWORD_ERROR");
	        }
	    } 
	    catch (Exception e) 
	    {
	        m.addAttribute("notificationType", "error");
	        m.addAttribute("notificationMessage", "Failed to add password. Please try again.");
	    }
	    return "UserPasswords/AddPassword"; // Return the same view
	}


	
	
	
	@PostMapping("/UpdatePasswordById")
	public String updateUserPassword(@ModelAttribute UserPasswords ups, Model m, HttpSession ses) throws PasswordError{
	    try 
	    {
	        String registeredMail = (String) ses.getAttribute("RegisteredMail");
	        int registeredMailId = (Integer) ses.getAttribute("RegisteredMailId");
	        ups.setRegisteredusermail(registeredMail);
	        ups.setRegisteredusermailid(registeredMailId);

	        int id = ups.getId();
	        int update = this.us.updateById(ups, id);
	        if(update!=0) 
	        {
	        	m.addAttribute("notificationType", "success");
	        	m.addAttribute("notificationMessage", "Password updated successfully.");
	        }
	        else
	        {
	        	throw new PasswordError("updation fail","UPDATE_PASSWORD_ERROR");
	        }
	    } 
	    catch (Exception e)
	    {    
	        m.addAttribute("message", "Failed to update the password record.");
	        m.addAttribute("alertType", "danger");
	    }
	    return "/UserPasswords/UpdatePassword"; 
	}

	
	@PostMapping("/deleteRecord")
	public String postMethodName(@RequestParam("deleteId") int id, Model model) throws PasswordError {
	    try 
	    {
	        this.dps.deleteRecordById(id);
	        model.addAttribute("notificationType", "success");
	        model.addAttribute("notificationMessage", "Password deleted successfully.");
	    }
	    catch (Exception e) 
	    {
	    	
	        model.addAttribute("message", "Failed to delete the record. Please check the ID.");
	        model.addAttribute("alertType", "danger"); 
	        throw new PasswordError("Delete Fail","DELETE_PASSWORD_ERROR");
	    }
	    return "/UserPasswords/DeletePassword"; // Return the same view
	}

	
}
