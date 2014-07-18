package com.hibernate.model;

public class GoodKeyword {
	private int id;
	private int hotvalue;
	private String keyword;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHotvalue() {
		return this.hotvalue;
	}
	public void setHotvalue(int hotvalue) {
		this.hotvalue = hotvalue;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
