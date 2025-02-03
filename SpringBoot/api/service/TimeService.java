package com.boot.api.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.boot.api.Api;
import com.boot.api.repo.ApiCallsRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TimeService 
{
	
	@Autowired
	ApiCallsRepo acr;
	
	public  String getCurrentTime()
	{
		try 
		{
			return fetchCurrentTimeFromServer();	
		}
		catch (Exception e) 
		{
			return "failed to get current time";
		}
		
	}
	
	
	public String updateNext12Hr(String registerMail,String cuurentTime)
	{
		String userLastTimeStamp = cuurentTime;
		
	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		

        LocalDateTime dateTime = LocalDateTime.parse(userLastTimeStamp, formatter);
      
        LocalDateTime userNext12hrTime = dateTime.plusHours(12);
        
        String updatedDateString = userNext12hrTime.format(formatter);
        
        
        this.acr.updateNext12hrTimeStamp(registerMail, updatedDateString);
		
		return userLastTimeStamp;
	}

	public  String fetchCurrentTimeFromServer()
	{
		try 
		{
		    String serverURL = Api.timeZoneUrlForValidatingRequests;
		    RestTemplate temp = new RestTemplate();
		    String response = temp.getForObject(serverURL, String.class);
		    
		    ObjectMapper objectMapper = new ObjectMapper();
		    JsonNode rootNode = objectMapper.readTree(response);
		    
		    String dateTime = rootNode.path("date_time").asText();
		    
		    System.out.println("Extracted date_time: " + dateTime);
		    return dateTime; 
		} 
		catch (Exception e) 
		{
		    e.printStackTrace();
		    return "failed to get time from server";
		}

     }
}
