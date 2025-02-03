package com.boot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name="User_Passwords")
@ToString
public class UserPasswords 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Psd_Id")
	int id;
	
	@Column(name="Psd_title")
	String title;
	
	@Column(name="Psd_about")
	String about;
	
	@Column(name="Psd_tags")
	String tags;
	
	@Column(name="Psd_date")
	String createddate;

	@Column(name="Psd_phone")
	String phone;
	
	@Column(name="Psd_pin")
	String pin;
	
	@Column(name="Psd_username")
	String username;
	
	@Column(name="Psd_mailid")
	String mailid;
	
	@Column(name="Psd_pass")
	String password;

	@Column(name = "Rgis_Id")
	int registeredusermailid;
	
	@Column(name = "Rgis_Mail")
	String registeredusermail;
	
	public UserPasswords() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRegisteredusermailid() {
		return registeredusermailid;
	}

	public void setRegisteredusermailid(int registeredusermailid) {
		this.registeredusermailid = registeredusermailid;
	}

	public String getRegisteredusermail() {
		return registeredusermail;
	}

	public void setRegisteredusermail(String registeredusermail) {
		this.registeredusermail = registeredusermail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMailid() {
		return mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	

	public void setPassword(String password)
	{
			this.password = password;
		
	}
	
	public String getPassword() 
	{
		
		return password;
	}

	public UserPasswords(int id, int registeredusermailid, String registeredusermail, String title, String about,
			String tags, String createddate, String phone, String pin, String username, String mailid,
			String password) {
		super();
		this.id = id;
		this.registeredusermailid = registeredusermailid;
		this.registeredusermail = registeredusermail;
		this.title = title;
		this.about = about;
		this.tags = tags;
		this.createddate = createddate;
		this.phone = phone;
		this.pin = pin;
		this.username = username;
		this.mailid = mailid;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserPasswords [id=" + id + ", title=" + title + ", about=" + about + ", tags=" + tags + ", createddate="
				+ createddate + ", phone=" + phone + ", pin=" + pin + ", username=" + username + ", mailid=" + mailid
				+ ", password(Encrypted Version)=" + password + ", registeredusermailid=" + registeredusermailid + ", registeredusermail="
				+ registeredusermail + "]";
	}

	
}
