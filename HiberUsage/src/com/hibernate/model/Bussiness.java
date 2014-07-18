package com.hibernate.model;

import java.util.Date;

public class Bussiness {
	private int id;
	private String uuid;
	private String buyername;
	private String sellername;
	private String goodkey;
	private Date date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getBuyername() {
		return buyername;
	}
	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	public String getGoodkey() {
		return goodkey;
	}
	public void setGoodkey(String goodkey) {
		this.goodkey = goodkey;
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
}
