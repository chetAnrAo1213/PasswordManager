package com.boot.customExceptions;

public class RegistrationError extends Exception
{
	private static final long serialVersionUID = 1L;
	
	private String errorType;

	public RegistrationError() 
	{
		super();
	}

	public RegistrationError(String message, String errorType) 
	{
		super(message);
		this.errorType=errorType;
	}

	public String getErrorType() 
	{
		return errorType;
	}
	
}
