package com.hibernate.model;

import java.util.Date;

public class Talk {
	private int id;
	private int pubid;
	private String username;
	private String talk;
	private int deleted;
	private Date date;
	public int getId() {
		return id;
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
	public int getPubid() {
		return pubid;
	}
	public void setPubid(int pubid) {
		this.pubid = pubid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTalk() {
		return talk;
	}
	public void setTalk(String talk) {
		this.talk = talk;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
}
