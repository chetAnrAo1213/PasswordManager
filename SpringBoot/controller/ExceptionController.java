package com.boot.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.thymeleaf.exceptions.TemplateInputException;

import com.boot.customExceptions.ApiError;
import com.boot.customExceptions.LoginError;
import com.boot.customExceptions.PasswordError;
import com.boot.customExceptions.QuestionsError;
import com.boot.customExceptions.RegistrationError;



@ControllerAdvice
public class ExceptionController 
{
	
	@ExceptionHandler(Exception.class)
    public String handleUnknownErrrors(Exception ex, Model m) 
    {
    	m.addAttribute("Error", "Unkown Error has been occured. Please Try Again.");	   
        return "/error";
    }

	@ExceptionHandler(LoginError.class)
    public String handleLoginError(LoginError logError, Model m) 
	{
        String type = logError.getErrorType();
        
        if(type.equals("PASSWORD_ERROR"))
        {
        	m.addAttribute("ErrorName", type);
            m.addAttribute("Error", "Password is invalid! You must've typed the wrong password. Please try again.");
        } 
        else if(type.equals("PIN_ERROR"))
        {
        	m.addAttribute("ErrorName", type);
            m.addAttribute("Error", "PIN is invalid! You must've typed the wrong PIN. Please try again.");
        } 
        else 
        {
     
            m.addAttribute("Error", "An unknown login error has occurred. Please try again.");
        }
        
        return "/error";
    }
	
	@ExceptionHandler(RegistrationError.class)
    public String handleRegistrationError(RegistrationError regError, Model m) 
	{
        String type = regError.getErrorType();
        
        if(type.equals("REGISTRATION_ERROR"))
        {
        	m.addAttribute("ErrorName", type);
            m.addAttribute("Error", "We Could Not Create An Account In Our System. Please try again.");
        } 
        else if(type.equals("UPDATE_PASSWORD_PIN_ERROR"))
        {
        	m.addAttribute("ErrorName", type);
            m.addAttribute("Error", "We Could Not Update Your Account In Our System. Please try again.");
        } 
        else 
        {
        	
            m.addAttribute("Error", "An unknown registration error has occurred. Please try again.");
        }
        
        return "/error";
    }
	
	@ExceptionHandler(QuestionsError.class)
    public String handleQuestionError(QuestionsError quesError, Model m) 
	{
        String type = quesError.getErrorType();
        
        if(type.equals("QUESTION_ERROR"))
        {
        	m.addAttribute("ErrorName", type);
            m.addAttribute("Error", "Answers are invalid! You must've typed the wrong Answer. Please try again.");
        } 
        else 
        {
            m.addAttribute("Error", "An unknown Question Answering error has occurred. Please try again.");
        }
        
        return "/error";
    }
	
	@ExceptionHandler(ApiError.class)
    public String handleApiError(ApiError apiError, Model m) 
	{
        String type = apiError.getErrorType();
        
        if(type.equals("APOD_ERROR"))
        {
        	m.addAttribute("ErrorName", type);
            m.addAttribute("Error", "NASA failed To Give An Response. We're Sorry! Do Understand that we're just a platform for you to connect with this API.");
        } 
        else if(type.equals("PEXEL_ERROR"))
        {
        	m.addAttribute("ErrorName", type);
            m.addAttribute("Error", "Pexels failed To Give An Response.  We're Sorry! Do Understand that we're just a platform for you to connect with this API.");
        }
        else if(type.equals("NEWS_ERROR"))
        {
        	m.addAttribute("ErrorName", type);
            m.addAttribute("Error", "NewsData.io failed To Give An Response. We're Sorry! Do Understand that we're just a platform for you to connect with this API!");
        }
        else 
        {
            m.addAttribute("Error", "An Unkown error has occurred. Please try again.");
        }
        
        return "/error";
    }
	
	@ExceptionHandler(ResourceAccessException.class)
	public String handleApiResourceError(Model m) 
	{
		m.addAttribute("ErrorName", "RESOURCE_NOT_FOUND");
		m.addAttribute("Error", "Failed to fetch from the Requested Resource. Please Make sure you have stable Internet Connection. Try Again!");   
		return "/error";
	}
	
	@ExceptionHandler(PasswordError.class)
	public String handlePasswordError(PasswordError psd,Model m) 
	{
		String type = psd.getErrorType();
		
        if(type.equals("ADD_PASSWORD_ERROR"))
        {
        	m.addAttribute("ErrorName", type);
            m.addAttribute("Error", "We're Extremely Sorry. There was an Error while adding your password. Try Again!");
        } 
        else if(type.equals("UPDATE_PASSWORD_ERROR"))
        {
        	m.addAttribute("ErrorName", type);
            m.addAttribute("Error", "We're Extremely Sorry. There was an Error while updating your password. Try Again!");
        }
        else if(type.equals("VIEW_PASSWORD_ERROR"))
        {
        	m.addAttribute("ErrorName", type);
            m.addAttribute("Error", "We're Extremely Sorry. There was an Error while fetching your password. Try Again!");
        }
        else if(type.equals("DELETE_PASSWORD_ERROR"))
        {
        	m.addAttribute("ErrorName", type);
            m.addAttribute("Error", "We're Extremely Sorry. There was an Error while deleting your password. Try Again!");
        }
        else if(type.equals("DECRYPT_PASSWORD_ERROR"))
        {
        	m.addAttribute("ErrorName", type);
            m.addAttribute("Error", "We're Extremely Sorry. There was an Error while decrypting your password. Try Again!");
        }
        
        else 
        {
            m.addAttribute("Error", "An Unkown error has occurred. Please try again.");
        }
        
        return "/error";
	}
	
	@ExceptionHandler(TemplateInputException.class)
	public String handleFrontEndError(Model m)
	{
        	m.addAttribute("ErrorName", "RenderingError");
            m.addAttribute("Error", "There was an Error while rendering your request. Try Again!"); 
		return "/error";
	}
}
