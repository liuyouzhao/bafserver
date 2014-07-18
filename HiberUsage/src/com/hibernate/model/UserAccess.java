package com.hibernate.model;

public class UserAccess {
	private int id;
	private String username;
	private String useraccess;
	private int deleted;
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUseraccess() {
		return useraccess;
	}
	public void setUseraccess(String useraccess) {
		this.useraccess = useraccess;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "UserAccess [id=" + id + ", username=" + username
				+ ", useraccess=" + useraccess + "]";
	}

}
