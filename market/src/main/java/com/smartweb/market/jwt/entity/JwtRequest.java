package com.smartweb.market.jwt.entity;

public class JwtRequest {

    private String userName;
    private String userPassword;
    
    
    // default constructor - need for JSON Parsing  //i don't know if we need this, _omar-Do.
  	public JwtRequest()
  	{
  		
  	}
  	 //Constructor - i don't now if we need this either.
    public JwtRequest(String userName, String userPassword) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
  
}
