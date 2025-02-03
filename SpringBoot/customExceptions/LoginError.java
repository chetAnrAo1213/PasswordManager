package com.boot.customExceptions;

public class LoginError extends Exception
{
	private static final long serialVersionUID = 1L;

	private String errorType;
	
	public LoginError() 
	{
		super();
	}

	public LoginError(String message,String errorType) 
	{
	 	super(message);
	    this.errorType=errorType;	
	}

	public String getErrorType() 
	{
		return errorType;
	}

}
