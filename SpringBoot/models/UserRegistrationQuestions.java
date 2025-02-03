package com.boot.models;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name = "User_Registration_Questions")
@ToString
public class UserRegistrationQuestions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Users_FirstQuestion")
    private String fq;

    @Column(name = "Users_SecondQuestion")
    private String sq;

    @Column(name = "Users_ThirdQuestion")
    private String tq;

 

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFq() {
        return fq;
    }

    public void setFq(String fq) {
        this.fq = fq;
    }

    public String getSq() {
        return sq;
    }

    public void setSq(String sq) {
        this.sq = sq;
    }

    public String getTq() {
        return tq;
    }

    public void setTq(String tq) {
        this.tq = tq;
    }

    

	public UserRegistrationQuestions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRegistrationQuestions(int id, String fq, String sq, String tq) {
		super();
		this.id = id;
		this.fq = fq;
		this.sq = sq;
		this.tq = tq;
	
	}

	@Override
	public String toString() {
		return "UserRegistrationQuestions [id=" + id + ", fq=" + fq + ", sq=" + sq + ", tq=" + tq 
				+ "]";
	}
    
    
}
