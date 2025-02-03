package com.boot.admin.model;

import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Admin_Registration_Details")
public class AdminRegistrationDetails 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Admin_Registration_Id")
    int id;
    
    @Column(name="Admin_Registration_Name")
    String name;
    
    @Column(name="Admin_Registration_Password")
    String password;
    
    @Column(name="Admin_Registration_MailId",unique = true,nullable = false)
    String mailId;
    
    @Column(name="Admin_Registration_Phone")
    String phone;
     
    @Column(name="Created_Date")
	@CreatedDate
	String createDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public AdminRegistrationDetails(int id, String name, String password, String mailId, String phone,
			String createDate) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.mailId = mailId;
		this.phone = phone;
		this.createDate = createDate;
	}

	public AdminRegistrationDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AdminRegistrationDetails [id=" + id + ", name=" + name + ", password=" + password + ", mailId=" + mailId
				+ ", phone=" + phone + ", createDate=" + createDate + "]";
	}
	
	
}
