package com.MotherSon.CRM.payload.request;


//import jakarta.validation.constraints.NotBlank;

//import javax.validation.constraints.NotBlank;

public class LoginRequest {
	
  private String username;

	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
