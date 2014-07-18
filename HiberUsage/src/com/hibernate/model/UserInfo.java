package com.hibernate.model;

public class UserInfo {
	private int id;
	private String username;
	private String province;
	private String city;
	private String address;
	private String email;
	private String phone;
	private String avander;
	private int sex;
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getAvander() {
		return avander;
	}
	public void setAvander(String avander) {
		this.avander = avander;
	}
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
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", username=" + username + ", province="
				+ province + ", city=" + city + ", address=" + address
				+ ", email=" + email + ", phone=" + phone + ", sex=" + sex
				+ "]";
	}

}