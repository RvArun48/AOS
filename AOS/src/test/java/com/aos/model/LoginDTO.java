package com.aos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDTO {

	

	@SerializedName("signin_email")
	@Expose
	private String signinEmail;

	public String getSigninEmail() {
		return signinEmail;
	}

	public void setSigninEmail(String signinEmail) {
		this.signinEmail = signinEmail;
	}
	
	@SerializedName("signin_password")
	@Expose
	private String signinPassword;

	public String getSigninPassword() {
		return signinPassword;
	}

	public void setSigninPassword(String signinPassword) {
		this.signinPassword = signinPassword;
	}
	


	
	
}