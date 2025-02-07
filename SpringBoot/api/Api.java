package com.boot.api;

public class Api 
{
	
	public static final String NEWS_API = "****";
	public static final String NEWS_URL = "https://newsdata.io/api/1/latest?apikey="+NEWS_API+"&q=";
	public static final String TIME_API = "****";
	public final String TIME_URL_ASIA ="https://api.ipgeolocation.io/timezone?apiKey=3fe1b01fede848bdba551e0193c5691f&lat=17.385044&long=78.486671";
	public static final String IND_LAT = "17.385044";
	public static final String IND_LON ="78.486671";
	public static final String timeZoneUrlForValidatingRequests ="https://api.ipgeolocation.io/timezone?apiKey="+TIME_API+"&lat="+IND_LAT+"+&long="+IND_LON;
	
	public static final String NASA_URL = "https://api.nasa.gov/planetary/apod?api_key=";
	public static final String NASA_API = "****";
	
	public static final String PEXEL_URL = "https://api.pexels.com/v1/videos/search?query=";
	public static final String PEXEL_API = "****";
	
	public static final String GroqAbuse_API = "****";
	public static final String Groq_URL = "https://api.groq.com/openai/v1/chat/completions";
	
	public static final String Groq_Admin_UserAnalysis_Key1 = "****";
	public static final String Groq_Admin_UserAnalysis_Key2 = "****";
}
 
