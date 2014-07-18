package com.hibernate.model;

import java.util.Date;

public class PublishBoard {
	private int id;
	private String username;
	private String content;
	private int pubtype;
	private int talknum;
	private int favor;
	private int nega;
	private int shared;
	private Date date;
	private int deleted;
	public int getPubtype() {
		return pubtype;
	}
	public void setPubtype(int pubtype) {
		this.pubtype = pubtype;
	}
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getTalknum() {
		return talknum;
	}
	public void setTalknum(int talknum) {
		this.talknum = talknum;
	}
	public int getFavor() {
		return favor;
	}
	public void setFavor(int favor) {
		this.favor = favor;
	}
	public int getNega() {
		return nega;
	}
	public void setNega(int nega) {
		this.nega = nega;
	}
	public int getShared() {
		return shared;
	}
	public void setShared(int shared) {
		this.shared = shared;
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
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	@Override
	public String toString() {
		return "Publish [id=" + id + ", content=" + content + ", talknum="
				+ talknum + ", like=" + favor + ", unlike=" + nega
				+ ", shared=" + shared + ", date=" + date + ", deleted=" + ", pubtype=" + pubtype + 
				+ deleted + "]";
	}
}
