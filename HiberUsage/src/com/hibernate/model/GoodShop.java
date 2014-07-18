package com.hibernate.model;

import java.util.Set;

public class GoodShop {
	private int id;
	private String username;
	private int favor;
	private int nega;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "GoodShop [id=" + id + ", username=" + username + ", favor="
				+ favor + ", nega=" + nega + ", description=" + description
				+ "]";
	}
}
