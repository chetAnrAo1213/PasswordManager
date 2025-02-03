package com.boot.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="Quick_Note")
public class QuickNote 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Note_Id")
	int id;
	
	@Column(name="Note_title")
	String title;
	
	@Column(name="Note_tags")
	String tags;
	
	@Lob
	@Column(name="Note_Content")
	String content;
	
	@Column(name = "Created_Date")
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss a")
	private String createDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

	
	@Column(name="Last_Modified")
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss a")
    private String lastModifiedDate;

	@Column(name = "Rgis_Id")
	int registeredusermailid;
	
	@Column(name = "Rgis_Mail")
	String registeredusermail;
	
	@Column(name = "Note_Count")
	int noteCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
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

	public int getNoteCount() {
		return noteCount;
	}

	public void setNoteCount(int noteCount) {
		this.noteCount = noteCount;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "QuickNote [id=" + id + ", title=" + title + ", tags=" + tags + ", content=" + content + ", createDate="
				+ createDate + ", lastModifiedDate=" + lastModifiedDate + ", registeredusermailid="
				+ registeredusermailid + ", registeredusermail=" + registeredusermail + ", noteCount=" + noteCount
				+ "]";
	}
	
	
}
