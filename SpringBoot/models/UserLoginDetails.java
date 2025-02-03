package com.boot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="User_Login_Credentials")
public class UserLoginDetails 
{	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(name="User_Email")
	String email;
	
	@Column(name="User_Password")
	String password;

	@Column(name="User_Pin")
	String pin;
	
	@Column(name="User_Role")
	final String role = "ROLE_USER";

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

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public UserLoginDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserLoginDetails(int id, String email, String password, String pin) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "UserLoginDetails [id=" + id + ", email=" + email + ", password=" + password + ", pin=" + pin + ", role="
				+ role + "]";
	}
	
	
	
}
