package com.bilgeadam.hibernateexample.repository.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tblpost")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Lob
	private String content;

	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@ManyToOne(cascade = CascadeType.ALL)
	User user;

	public Post(String content, Date createdDate, User user) {
		super();
		this.content = content;
		this.createdDate = createdDate;
		this.user = user;
	}

	public Post(long id, String content, Date createdDate) {
		super();
		this.id = id;
		this.content = content;
		this.createdDate = createdDate;
	}

	public Post(String content, Date date) {
		super();
		this.content = content;
		this.createdDate = date;
	}

	public Post() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", createdDate=" + createdDate + "]";
	}

}
