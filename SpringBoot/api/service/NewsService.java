package com.boot.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.boot.api.Api;
import com.boot.api.fields.NewsField;
import com.boot.api.models.ApiRecords;
import com.boot.api.repo.ApiCallsRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NewsService 
{
	
	@Autowired
	ApiCallsRepo acr;
	
	@Autowired
	TimeService ts;

	public int checkIntitalCount(String registerMail,ApiRecords api)
	{
		api = this.acr.findCompelete(registerMail);
		System.out.println(api);
		int i = api.getNewsRequestCalls();
		return i;
	}
	
	
	int count=3;
	public List<NewsField> threeRequestCalls(String searchkey, int requestCount,ApiRecords api,String registerMail) 
	{
		if(requestCount<count)
		{
			return this.requestNews(searchkey);
		}
		else if(count==requestCount)
		{
			int i = this.checkIntitalCount(registerMail, api);
			String currentTime = this.ts.fetchCurrentTimeFromServer();
			this.acr.updateTimeStamp(registerMail, (i+3), currentTime);
			this.ts.updateNext12Hr(registerMail,currentTime);
			this.acr.updateSearchKey(registerMail, searchkey);
			return this.requestNews(searchkey);
		}
		else
		{
			return new ArrayList<>();
		}
	}

	
	public String getLastTimeStamp(String registerMail,ApiRecords api)
	{
		//System.out.println(registerMail);
		ApiRecords user = this.acr.findCompelete(registerMail);
		//System.out.println(user);
		String ans = user.getNewsTimeStamp();
		//System.out.println(ans);
		return ans;
	}
	public String getNext12hrTime(String registerMail,ApiRecords api) 
	{
		//System.out.println("12DB "+registerMail);
				ApiRecords user = this.acr.findCompelete(registerMail);
				//System.out.println("Servvice layer "+user);
				String ans = user.getNextTimeStamp();
				//System.out.println(ans);
				return ans;
	}
	
	
	
	public List<NewsField> requestNews(String searchQuery) 
	{
	    String responseUrl = Api.NEWS_URL + searchQuery;
	    RestTemplate restTemplate = new RestTemplate();
	    List<NewsField> newsList = new ArrayList<>();

	    try 
	    {
	        String response = restTemplate.getForObject(responseUrl, String.class);

	        ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode rootNode = objectMapper.readTree(response);
	        JsonNode results = rootNode.path("results");

	        if (results.isArray()) 
	        {
	        	for (JsonNode article : results) 
	        	{
	        	    NewsField newsField = new NewsField();
	        	    newsField.setTitle(article.path("title").asText());
	        	    newsField.setLink(article.path("link").asText());
	        	    newsField.setDescription(article.path("description").asText());
	        	    newsField.setPublishDate(article.path("pubDate").asText());
	        	    newsField.setPublishTZ(article.path("pubDateTZ").asText());
	        	    newsField.setSourcename(article.path("source_name").asText());
	        	    newsField.setSourceurl(article.path("source_url").asText());
	        	    newsField.setLanguage(article.path("language").asText());
	        	    
	        	    newsField.setKeywords(article.path("keywords").isArray()
	        	        ? new ObjectMapper().convertValue(article.path("keywords"), ArrayList.class)
	        	        : new ArrayList<>(List.of("No information available")));

	        	    newsField.setCreator(article.path("creator").isArray()
	        	        ? new ObjectMapper().convertValue(article.path("creator"), ArrayList.class)
	        	        : new ArrayList<>(List.of("No information available")));

	        	    newsField.setCountry(article.path("country").isArray()
	        	        ? new ObjectMapper().convertValue(article.path("country"), ArrayList.class)
	        	        : new ArrayList<>(List.of("No information available")));

	        	    newsField.setCategory(article.path("category").isArray()
	        	        ? new ObjectMapper().convertValue(article.path("category"), ArrayList.class)
	        	        : new ArrayList<>(List.of("No information available")));

	        	    newsList.add(newsField);
	        	}

	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ArrayList<>(); 
	    }

	    return newsList;
	}

	
}
