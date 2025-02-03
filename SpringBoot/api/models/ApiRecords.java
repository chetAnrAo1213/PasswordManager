package com.boot.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="API_Requests_User")
public class ApiRecords 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(name="newsLast")
	String newsTimeStamp;
	
	@Column(name="newsNext")
	String nextTimeStamp;

	@Column(name="newsReq")
	int newsRequestCalls=0;
	
	@Column(name="Reg_Mail")
	String registerMail;
	
	@Column(name="Reg_Id")
	int registerMailId;
	
	@Column(name="ApodReq")
	int apodRequestCalls=0;
	
	@Column(name="PexelReq")
	int pexelRequestCalls=0;
	
	@Column(name="SearchNews")
	String searchKey;
	
	@Column(name="SearchApod")
	String apodSearchKey;
	
	@Column(name="SearchPexel")
	String pexelSearchKey;

	
	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNewsTimeStamp() {
		return newsTimeStamp;
	}

	public void setNewsTimeStamp(String newsTimeStamp) {
		this.newsTimeStamp = newsTimeStamp;
	}

	public String getNextTimeStamp() {
		return nextTimeStamp;
	}

	public void setNextTimeStamp(String nextTimeStamp) {
		this.nextTimeStamp = nextTimeStamp;
	}

	public int getNewsRequestCalls() {
		return newsRequestCalls;
	}

	public void setNewsRequestCalls(int newsRequestCalls) {
		this.newsRequestCalls = newsRequestCalls;
	}

	public String getRegisterMail() {
		return registerMail;
	}

	public void setRegisterMail(String registerMail) {
		this.registerMail = registerMail;
	}

	public int getRegisterMailId() {
		return registerMailId;
	}

	public void setRegisterMailId(int registerMailId) {
		this.registerMailId = registerMailId;
	}

	public int getApodRequestCalls() {
		return apodRequestCalls;
	}

	public void setApodRequestCalls(int apodRequestCalls) {
		this.apodRequestCalls = apodRequestCalls;
	}

	public int getPexelRequestCalls() {
		return pexelRequestCalls;
	}

	public void setPexelRequestCalls(int pexelRequestCalls) {
		this.pexelRequestCalls = pexelRequestCalls;
	}

	public String getApodSearchKey() {
		return apodSearchKey;
	}

	public void setApodSearchKey(String apodSearchKey) {
		this.apodSearchKey = apodSearchKey;
	}

	public String getPexelSearchKey() {
		return pexelSearchKey;
	}

	public void setPexelSearchKey(String pexelSearchKey) {
		this.pexelSearchKey = pexelSearchKey;
	}

	@Override
	public String toString() {
		return "ApiRecords [id=" + id + ", newsTimeStamp=" + newsTimeStamp + ", nextTimeStamp=" + nextTimeStamp
				+ ", newsRequestCalls=" + newsRequestCalls + ", registerMail=" + registerMail + ", registerMailId="
				+ registerMailId + ", apodRequestCalls=" + apodRequestCalls + ", pexelRequestCalls=" + pexelRequestCalls
				+ ", searchKey=" + searchKey + ", apodSearchKey=" + apodSearchKey + ", pexelSearchKey=" + pexelSearchKey
				+ "]";
	}

	
	
}
