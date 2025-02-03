package com.boot.api.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.boot.api.Api;
import com.boot.api.models.AiModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AbusiveService 
{
	
	@Autowired
    AiModel aim;

	
	 public String processChatMessage(String searchKey) {
	        try {
	        	
	            String userMessage = searchKey ;
	            if (userMessage == null || userMessage.trim().isEmpty()) {
	                return "null";
	            }

	            String apiUrl = aim.hostUrl;

	            Map<String, Object> input = new HashMap<>();
	            input.put("model", aim.aiModel);

	            Map<String, String> systemMsg = new HashMap<>();
	            systemMsg.put("role", "system");
	            systemMsg.put("content", "You are an expert in abusive query rejection. Your job is to predict True/False for the given keyword. "
	            						+"Say 'true' if it is non-abusive or 'false' if it is abusive and 'false' for gibbersh also.");
	            
	            
	            Map<String, String> inputMsg = new HashMap<>();
	            inputMsg.put("role", "user");
	            inputMsg.put("content", userMessage);

	            
	            input.put("messages", new Object[]{systemMsg, inputMsg});

	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_JSON);
	            headers.setBearerAuth("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");

	            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(input, headers);

	            RestTemplate restTemplate = new RestTemplate();
	            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

	            ObjectMapper objectMapper = new ObjectMapper();
	            JsonNode rootNode = objectMapper.readTree(response.getBody());
	            String content = rootNode.path("choices").get(0).path("message").path("content").asText();

	            aim.setModelMessage(content);

	            return content.toLowerCase();
	        } catch (Exception e) {
	            System.err.println("Error: " + e.getMessage());
	            return "error";
	        }
	    }
}
