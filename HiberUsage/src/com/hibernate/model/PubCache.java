package com.hibernate.model;

import java.util.Date;

public class PubCache {
	private int id;
	private String username;
	private String content;
	private Date date;
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		if(date == null) {
			this.date = new Date();
			return;
		}
		this.date = date;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "PubCache [id=" + id + ", username=" + username + ", content="
				+ content + ", date=" + date + "]";
	}
	
}
