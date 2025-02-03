package com.boot.admin.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.boot.api.Api;
import com.boot.api.models.ApiRecords;
import com.boot.api.repo.ApiCallsRepo;
import com.boot.dao.LoginRepo;
import com.boot.dao.NotesRepo;
import com.boot.dao.PasswordRepo;
import com.boot.dao.QuestionsRepo;
import com.boot.dao.RegistrationRepo;
import com.boot.models.QuickNote;
import com.boot.models.UserPasswords;
import com.boot.models.UserRegistrationDetails;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class UserSpecificService 
{
	
	ApiCallsRepo apicalls;
	LoginRepo logins;
	NotesRepo notes;
	PasswordRepo passwords;
	QuestionsRepo questions;
	RegistrationRepo reistrations;
	
	public UserSpecificService(ApiCallsRepo apicalls, LoginRepo logins, NotesRepo notes, PasswordRepo passwords, QuestionsRepo questions, RegistrationRepo reistrations) 
	{
		this.apicalls = apicalls;
		this.logins = logins;
		this.notes = notes;
		this.passwords = passwords;
		this.questions = questions;
		this.reistrations = reistrations;
	}

	
	public List<String> getRegisterMailIds()
	{
		List<String> users = this.reistrations.findAllEmails();
		return users;
	}
	
	Map<String,Object> userData = new LinkedHashMap<String,Object>();
	public Map<String,Object> getSpecificUserData(String mailId)
	{
		UserRegistrationDetails registrationDetails = this.reistrations.findCompelete(mailId);
		List<String> questionsDetails = this.reistrations.findEmailQuestions(mailId);
		QuickNote noteDetails = this.notes.findNote(mailId);
		ApiRecords apidetails = this.apicalls.findCompelete(mailId);
		List<UserPasswords> userStoredPasswords = this.passwords.findUserPasswords(mailId);
		userData.put("Registration", registrationDetails);
		userData.put("Questions", questionsDetails);
		userData.put("Note", noteDetails);
		userData.put("Api", apidetails);
		userData.put("userStoredPasswords", userStoredPasswords);
		
		return userData;
	}
	
	public String getUserAnalysisFromAI(String mailId,String aimodel)
	{
		UserRegistrationDetails registrationDetails = this.reistrations.findCompelete(mailId);
		QuickNote noteDetails = this.notes.findNote(mailId);
		ApiRecords apidetails = this.apicalls.findCompelete(mailId);
		List<UserPasswords> userStoredPasswords = this.passwords.findUserPasswords(mailId);
		String data = (registrationDetails+"\n"+noteDetails+"\n"+apidetails+"\n"+userStoredPasswords);
		String report = this.fetchAiReport(data, aimodel);
		return report;
	}
	
	public String fetchAiReport(String data,String aimodel)
	{
		String key; 
		try 
		{
        	
            String userMessage = data;
            if (userMessage == null || userMessage.trim().isEmpty()) {
                return "null";
            }

            String apiUrl = Api.Groq_URL;
            
            Map<String, Object> input = new HashMap<>();
            input.put("model", aimodel);

            if(aimodel.equals("llama-3.3-70b-versatile"))
            {
            	key=Api.Groq_Admin_UserAnalysis_Key1;
            }
            else
            {
            	key=Api.Groq_Admin_UserAnalysis_Key2;
            }
            Map<String, String> systemMsg = new HashMap<>();
            systemMsg.put("role", "system");
            systemMsg.put("content", "you are an expert in User Database Record Anaylsis. Analyse the given User Data and generate a report."
            						 + " Also check whether the user has given spam/fraud data or not.");
            
            
            Map<String, String> inputMsg = new HashMap<>();
            inputMsg.put("role", "user");
            inputMsg.put("content", userMessage);

            
            input.put("messages", new Object[]{systemMsg, inputMsg});

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(key);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(input, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            String content = rootNode.path("choices").get(0).path("message").path("content").asText();

            return content.toLowerCase();
        } 
		catch (Exception e) 
		{
            System.err.println("Error: " + e.getMessage());
            return "error";
        }
	}
}
