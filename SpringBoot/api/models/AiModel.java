package com.boot.api.models;

import org.springframework.stereotype.Component;

@Component
public class AiModel 
{

	public final String hostUrl = "https://api.groq.com/openai/v1/chat/completions";
	public final String aiModel = "llama-3.1-8b-instant";
	public final String apiKey = "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";
	String userMessage;
	String modelMessage;

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getModelMessage() {
		return modelMessage;
	}

	public void setModelMessage(String modelMessage) {
		this.modelMessage = modelMessage;
	}

	public AiModel(String userMessage, String modelMessage) {
		super();
		this.userMessage = userMessage;
		this.modelMessage = modelMessage;
	}

	public AiModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
