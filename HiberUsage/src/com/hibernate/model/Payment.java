package com.hibernate.model;

import java.util.Date;

public class Payment {
	private int id;
	private int busid;
	private int price;
	private String uuid;
	private Date date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBusid() {
		return busid;
	}
	public void setBusid(int busid) {
		this.busid = busid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	@Override
	public String toString() {
		return "Payment [id=" + id + ", busid=" + busid + ", price=" + price
				+ ", uuid=" + uuid + ", date=" + date + "]";
	}
	
}
