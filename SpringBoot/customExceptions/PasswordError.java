package com.boot.customExceptions;

public class PasswordError extends Exception
{
	private static final long serialVersionUID = 1L;

	private String errorType;

	public PasswordError() 
	{
		super();
	}

	public PasswordError(String message, String errorType) 
	{
		super(message);
		this.errorType = errorType;
	}

	public String getErrorType() 
	{
		return errorType;
	}

}
