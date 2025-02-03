package com.boot.admin.model;


import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Admin_Login_Details")
@DynamicUpdate
public class AdminLoginDetails 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Admin_Id")
	int id;
	
	@Column(name="Admin_Mail")
	String email;
	
	@Column(name="Admin_Password")
	String password;
	
	@Column(name="User_Role")
	final String role="ROLE_ADMIN";
	
	@Column(name="Last_login_time")
	@LastModifiedDate
	String lastModifiedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getRole() {
		return role;
	}

	public AdminLoginDetails(int id, String email, String password, String lastModifiedDate) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.lastModifiedDate = lastModifiedDate;
	}

	public AdminLoginDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
