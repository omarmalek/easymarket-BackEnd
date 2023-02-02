package com.smartweb.market.jwt.entity;

public class JwtResponse {
	

	private Long id;
	private String userName;
	private String phoneNumber;
	private String address;
	

	private String jwtToken;
	private String roleName;
	
	public JwtResponse() {
	}

	public JwtResponse(Long id,String userName, String phoneNumber, String address, String jwtToken) {
		super();
		this.id = id;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.jwtToken = jwtToken;
	}

	public JwtResponse(String userName,  String jwtToken,String roleName) {
		super();
		this.userName = userName;
		this.jwtToken = jwtToken;
		this.roleName = roleName;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	};

	

}
