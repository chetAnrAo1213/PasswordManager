package com.boot.customExceptions;


public class QuestionsError extends Exception
{
	
	private static final long serialVersionUID = 1L;
	
	private String errorType;

	public QuestionsError() 
	{
		super();
	}


	public QuestionsError(String message,String errorType) 
	{
		super(message);
		this.errorType=errorType;
	}


	public String getErrorType() 
	{
		return errorType;
	}
	
	
}
