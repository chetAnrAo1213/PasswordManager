package com.boot.api.controller;


import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.ResourceAccessException;

import com.boot.api.fields.APODField;
import com.boot.api.fields.NewsField;
import com.boot.api.fields.PexelField;
import com.boot.api.models.ApiRecords;
import com.boot.api.service.AbusiveService;
import com.boot.api.service.ApodService;
import com.boot.api.service.NewsService;
import com.boot.api.service.PexelService;
import com.boot.api.service.TimeService;
import com.boot.customExceptions.ApiError;
import com.boot.service.InternetConnectivityService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api")
public class ApiController 
{
	
	
	@Autowired
	NewsService ns;
	
	@Autowired
	TimeService ts;
	
	@Autowired
	ApodService aps;
	
	@Autowired 
	PexelService pxs;
	
	@Autowired
	AbusiveService abs;
	
	@Autowired 
	InternetConnectivityService ics;
	int intialCount=1;
	String currentTime;
	String getNextTimeStamp;
	String lasttimeDB;
	

	@PostMapping("/apod")
	public String fetchAPOD(Model m,HttpSession ses,ApiRecords api) throws ApiError, UnknownHostException
	{
		boolean internetAccess = this.ics.validateInternetConnection();
		if(internetAccess)
		{	  
			try 
			{
				APODField picture = this.aps.fetchPictureDetails();
				String registerMail = (String)ses.getAttribute("RegisteredMail");
				if(picture!=null)
				{
					currentTime = this.ts.getCurrentTime();
					this.aps.updateRequestCallAndKey(registerMail, currentTime, api);
					m.addAttribute("ApodPicture", picture);
				}
				else
				{
					throw new ApiError("Api Failed to give response.","APOD_ERROR");
				}
			}
			catch (ResourceAccessException re) 
			{
				return "/error";
			}
			return "/Api/ApodIndex";
		}
		else
		{

			m.addAttribute("ErrorName", "NO_INTERNET_CONNECTION");
			m.addAttribute("Error", "Please Make sure you have stable Internet Connection. Try Again!");
			return "/error";
		}
	}
	
	@PostMapping("/news")
	public String fetchNews(@RequestParam("search") String searchKey, Model m,HttpSession ses,ApiRecords api) throws ApiError
	{   

		boolean internetAccess = this.ics.validateInternetConnection();
		if(internetAccess)
		{
			String a = this.abs.processChatMessage(searchKey);
			if(a.equals("true")) 
			{
				String registerMail = (String) ses.getAttribute("RegisteredMail");

				String checkTimeStamp =  this.ns.getLastTimeStamp(registerMail, api);

				if(checkTimeStamp.trim().equals("0")) //first Time calling api
				{
					List<NewsField> news= this.ns.threeRequestCalls(searchKey,intialCount,api,registerMail);
					if(news!=null)
					{
						intialCount++;

						if (news.size()>=1) 
						{
							m.addAttribute("article", news);
						}
						else
						{
							lasttimeDB = this.ns.getLastTimeStamp(registerMail, api);
							m.addAttribute("limit", "We're Sorry Our system uses external api to provide news for you."
									+ "thats why we are limiting your request.You made you last request call at "+lasttimeDB
									+". You can acess again after 12hrs i.e "+getNextTimeStamp
									+". The current time is "+currentTime);
						}
					}
					else
					{
						throw new ApiError("Api Failure","NEWS_ERROR");
					}
				}
				else
				{
					currentTime = this.ts.getCurrentTime();
					getNextTimeStamp = this.ns.getNext12hrTime(registerMail,api);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime currentDateTime = LocalDateTime.parse(currentTime, formatter);
					LocalDateTime nextDateTime = LocalDateTime.parse(getNextTimeStamp, formatter);
					lasttimeDB = this.ns.getLastTimeStamp(registerMail, api);

					if (currentDateTime.isBefore(nextDateTime)) 
					{
						m.addAttribute("limit", "We're Sorry Our system uses external api to provide news for you."
								+ "thats why we are limiting your request.You made you last request call at "+lasttimeDB
								+". You can acess again after 12hrs i.e "+getNextTimeStamp
								+". The current time is "+currentTime);
					}
					else if (currentDateTime.isAfter(nextDateTime) || currentDateTime.isEqual(nextDateTime)) 
					{
						List<NewsField> news= this.ns.threeRequestCalls(searchKey,intialCount,api,registerMail);
						if(news!=null) 
						{
							intialCount++;

							if (news.size()>=1) 
							{
								System.out.println(news);
								m.addAttribute("article", news);
							}
							else
							{
								System.out.println(news);
								m.addAttribute("limit", "No news avaliable under keyword: '"+searchKey+"'");
							}
						}
						else 
						{
							throw new ApiError("Api Failure","NEWS_ERROR");
						}
					} 	        

				}
			}
			else
			{

				m.addAttribute("notificationType", "danger");
				m.addAttribute("notificationMessage", "AI Security Filter Failure. We Have Detected Abusive Content In Your Search Query. Don't Use Abusive KeyWords.");
			}

			return "/Api/NewsIndex";
		}
		else 
		{
			m.addAttribute("ErrorName", "NO_INTERNET_CONNECTION");
			m.addAttribute("Error", "Please Make sure you have stable Internet Connection. Try Again!");
			return "/error";
		}
	}
	
	
	@PostMapping("/videos")
	public String getPexelVideos(@RequestParam("search") String searchKey,Model m,HttpSession ses,ApiRecords api) throws ApiError
	{
		boolean internetAccess = this.ics.validateInternetConnection();
		if(internetAccess)
		{
			String a = this.abs.processChatMessage(searchKey);	
			if(a.equals("true")) 
			{

				List<PexelField> videos = this.pxs.fetchVideoDetails(searchKey);
				String registerMail = (String)ses.getAttribute("RegisteredMail");
				if(videos !=null)
				{
					currentTime = this.ts.getCurrentTime();
					this.pxs.updateRequestCallAndKey(registerMail, searchKey+" "+currentTime, api);
					m.addAttribute("videos", videos);
				}
				else
				{
					throw new ApiError("Api failure","PEXEL_ERROR");
				}
			}
			else
			{
				m.addAttribute("notificationType", "danger");
				m.addAttribute("notificationMessage", "AI Security Filter Failure. We Have Detected Abusive Content In Your Search Query. Don't Use Abusive KeyWords.");
			}
			return "/Api/PexelVideos";
		}
		else 
		{
			m.addAttribute("ErrorName", "NO_INTERNET_CONNECTION");
			m.addAttribute("Error", "Please Make sure you have stable Internet Connection. Try Again!");
			return "/error";
		}
	}
	
}