package com.boot.api.fields;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class NewsField
{

	public String title;
	public String link;
	public ArrayList<String> keywords;
	public ArrayList<String> creator;
	public String description;
	public String publishDate;
	public String publishTZ;
	public String sourcename;
	public String sourceurl;
	public String language;
	public ArrayList<String> country;
	public ArrayList<String> category;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public ArrayList<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}
	public ArrayList<String> getCreator() {
		return creator;
	}
	public void setCreator(ArrayList<String> creator) {
		this.creator = creator;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getPublishTZ() {
		return publishTZ;
	}
	public void setPublishTZ(String publishTZ) {
		this.publishTZ = publishTZ;
	}
	public String getSourcename() {
		return sourcename;
	}
	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}
	public String getSourceurl() {
		return sourceurl;
	}
	public void setSourceurl(String sourceurl) {
		this.sourceurl = sourceurl;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public ArrayList<String> getCountry() {
		return country;
	}
	public void setCountry(ArrayList<String> country) {
		this.country = country;
	}
	public ArrayList<String> getCategory() {
		return category;
	}
	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}
	
	
	
}
