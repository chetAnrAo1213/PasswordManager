package com.boot.models;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name = "User_Registration_Details")
@ToString
public class UserRegistrationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "User_FirstName")
    private String fname;

    @Column(name = "User_LastName")
    private String lname;

    @Column(name = "User_MailId", unique = true, nullable = false)
    private String email;

    @Column(name = "User_Phone")
    private String phone;

    @Column(name = "User_Password")
    private String cpassword;

    @Column(name = "User_Pin")
    private String cpin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
	public UserRegistrationQuestions urq;
    
    @Column(name="Note_Count")
    private int noteCount=0;

   
   

    public UserRegistrationDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRegistrationDetails(int id, String fname, String lname, String email, String phone, String cpassword,
			String cpin, UserRegistrationQuestions urq, int noteCount) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
		this.cpassword = cpassword;
		this.cpin = cpin;
		this.urq = urq;
		this.noteCount = noteCount;
	}

	// Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public int getNoteCount() {
		return noteCount;
	}

	public void setNoteCount(int noteCount) {
		this.noteCount = noteCount;
	}

	public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public String getCpin() {
        return cpin;
    }

    public void setCpin(String cpin) {
        this.cpin = cpin;
    }

    public UserRegistrationQuestions getUrq() {
        return urq;
    }

    public void setUrq(UserRegistrationQuestions urq) {
        this.urq = urq;
    }

	@Override
	public String toString() {
		return "UserRegistrationDetails [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email
				+ ", phone=" + phone + ", cpassword=" + cpassword + ", cpin=" + cpin + ", urq=" + urq + "]";
	}
    
    
}
