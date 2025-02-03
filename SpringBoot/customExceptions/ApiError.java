package com.boot.customExceptions;

public class ApiError extends RuntimeException 
{

	private static final long serialVersionUID = 1L;
	private String errorType;

	public ApiError() 
	{
		super();
	}

	public ApiError(String message, String errorType) 
	{
		super(message);
		this.errorType = errorType;
	}

	public String getErrorType() 
	{
		return errorType;
	}
}
