package com.boot.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.boot.api.Api;
import com.boot.api.fields.APODField;
import com.boot.api.models.ApiRecords;
import com.boot.api.repo.ApiCallsRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApodService 
{
	
	@Autowired
	ApiCallsRepo acr;
	
	public void updateRequestCallAndKey(String Registermail,String searchKey,ApiRecords api)
	{
		api = this.acr.findCompelete(Registermail);
		int apodCount = api.getApodRequestCalls();
		apodCount++;
		this.acr.updateApodCount(Registermail, apodCount, searchKey);
	}
	
	public APODField fetchPictureDetails()
	{
		RestTemplate template = new RestTemplate();
		String requestUrl = Api.NASA_URL+Api.NASA_API;
		
		APODField responsePicture = new APODField();
		
		try
		{
		   String response = template.getForObject(requestUrl,String.class);
		   ObjectMapper objectMapper = new ObjectMapper();
		   JsonNode rootNode = objectMapper.readTree(response);
		   
		    responsePicture.setTitle(rootNode.path("title").asText());
		    responsePicture.setExplanation(rootNode.path("explanation").asText());
		    responsePicture.setImage(rootNode.path("hdurl").asText());
		    responsePicture.setDate(rootNode.path("date").asText());
		    responsePicture.setUrl(rootNode.path("url").asText());
		    return responsePicture;
		}
		catch (Exception e) 
		{
			return new APODField();
		}
		
	}
}
