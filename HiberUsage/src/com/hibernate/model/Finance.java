package com.hibernate.model;

import java.util.Date;

public class Finance {
	private int id;
	private double finance0;
	private double finance1;
	private double finance2;
	private double finance3;
	private double finance4;
	private double finance5;
	private double finance6;
	private double finance7;
	private double finance8;
	private double finance9;
	private double finance10;
	private double finance11;
	private double finance12;
	private int fintype;
	private int newest;
	private Date date;
	
	public int getNewest() {
		return newest;
	}
	public void setNewest(int newest) {
		this.newest = newest;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getFinance0() {
		return finance0;
	}
	public void setFinance0(double finance0) {
		this.finance0 = finance0;
	}
	public double getFinance1() {
		return finance1;
	}
	public void setFinance1(double finance1) {
		this.finance1 = finance1;
	}
	public double getFinance2() {
		return finance2;
	}
	public void setFinance2(double finance2) {
		this.finance2 = finance2;
	}
	public double getFinance3() {
		return finance3;
	}
	public void setFinance3(double finance3) {
		this.finance3 = finance3;
	}
	public double getFinance4() {
		return finance4;
	}
	public void setFinance4(double finance4) {
		this.finance4 = finance4;
	}
	public double getFinance5() {
		return finance5;
	}
	public void setFinance5(double finance5) {
		this.finance5 = finance5;
	}
	public double getFinance6() {
		return finance6;
	}
	public void setFinance6(double finance6) {
		this.finance6 = finance6;
	}
	public double getFinance7() {
		return finance7;
	}
	public void setFinance7(double finance7) {
		this.finance7 = finance7;
	}
	public double getFinance8() {
		return finance8;
	}
	public void setFinance8(double finance8) {
		this.finance8 = finance8;
	}
	public double getFinance9() {
		return finance9;
	}
	public void setFinance9(double finance9) {
		this.finance9 = finance9;
	}
	public double getFinance10() {
		return finance10;
	}
	public void setFinance10(double finance10) {
		this.finance10 = finance10;
	}
	public double getFinance11() {
		return finance11;
	}
	public void setFinance11(double finance11) {
		this.finance11 = finance11;
	}
	public double getFinance12() {
		return finance12;
	}
	public void setFinance12(double finance12) {
		this.finance12 = finance12;
	}
	public int getFintype() {
		return fintype;
	}
	public void setFintype(int fintype) {
		this.fintype = fintype;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"finance0\":\"" + finance0
				+ "\", \"finance1\":\"" + finance1 + "\", \"finance2\":\""
				+ finance2 + "\", \"finance3\":\"" + finance3
				+ "\", \"finance4\":\"" + finance4 + "\", \"finance5\":\""
				+ finance5 + "\", \"finance6\":\"" + finance6
				+ "\", \"finance7\":\"" + finance7 + "\", \"finance8\":\""
				+ finance8 + "\", \"finance9\":\"" + finance9
				+ "\", \"finance10\":\"" + finance10 + "\", \"finance11\":\""
				+ finance11 + "\", \"finance12\":\"" + finance12
				+ "\", \"fintype\":\"" + fintype + "\", \"newest\":\"" + newest
				+ "\", \"date\":\"" + date + "\"}";
	}
}
