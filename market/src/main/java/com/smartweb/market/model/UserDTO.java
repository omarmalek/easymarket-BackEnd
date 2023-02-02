package com.smartweb.market.model;

public class UserDTO {
	private String phoneNumber;
	private String userName;
	private String password;
	private String address;
	
	public UserDTO() {
		
	}
	public UserDTO(String phoneNumber, String userName, String password, String address) {
		super();
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.password = password;
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
