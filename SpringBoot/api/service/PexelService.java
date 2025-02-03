package com.boot.api.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.boot.api.Api;
import com.boot.api.fields.PexelField;
import com.boot.api.models.ApiRecords;
import com.boot.api.repo.ApiCallsRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PexelService 
{
	
	@Autowired
	ApiCallsRepo acr;
	
	public void updateRequestCallAndKey(String Registermail,String searchKey,ApiRecords api)
	{
		ApiRecords api1 = this.acr.findCompelete(Registermail);
		int pexelCount = api1.getPexelRequestCalls();
		pexelCount++;
		this.acr.updatePexelCount(Registermail, pexelCount,searchKey);
	}
	
	public List<PexelField> fetchVideoDetails(String searchKey) 
	{
        RestTemplate template = new RestTemplate();
        String requestUrl =  Api.PEXEL_URL+searchKey;

        List<PexelField> requestVideos = new ArrayList<>();
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", Api.PEXEL_API); 

        try 
        {
            // Send GET request with Authorization header
            ResponseEntity<String> response = template.exchange(requestUrl, HttpMethod.GET, new HttpEntity<>(headers), String.class);

            // Parse the JSON response
            String responseBody = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);

            
            JsonNode videosArray = rootNode.path("videos");
            if (videosArray.isArray()) 
            {
                for (JsonNode videoNode : videosArray) 
                {
                    PexelField videos = new PexelField();

                   
                    JsonNode userNode = videoNode.path("user");
                    videos.setPexelUserId(userNode.path("id").asText());
                    videos.setPexelUsername(userNode.path("name").asText());
                    videos.setPexelUserurl(userNode.path("url").asText());                  
                    videos.setPexelVideoid(videoNode.path("id").asText());
                    videos.setPexelVideoquality(videoNode.path("video_quality").asText());

                    
                    JsonNode videoSource = videoNode.path("video_files");
                    if (videoSource.isArray() && videoSource.size() > 0) 
                    {
                        JsonNode firstFileNode = videoSource.get(0);
                        videos.setPexelVideofiletype(firstFileNode.path("file_type").asText());
                        videos.setPexelVideolink(firstFileNode.path("link").asText());
                    }

                    
                    requestVideos.add(videos);
                }
            }
        } catch (Exception e) 
        {
            e.printStackTrace(); 
        } 
        return requestVideos; 
    }

}
