package com.aos.model;

public class CommonDTO {

	private static CommonDTO instance;
	private String userName;
	private String password;

	// Private constructor to prevent instantiation
	private CommonDTO() {
	}

	// Public method to provide access to the singleton instance
	public static CommonDTO getInstance() {
		if (instance == null) {
			synchronized (CommonDTO.class) {
				if (instance == null) {
					instance = new CommonDTO();
				}
			}
		}
		return instance;
	}

	// Getter for userName
	public String getUserName() {
		return userName;
	}

	// Setter for userName
	public void setUserName(String userName) {
		this.userName = userName;
	}

	// Getter for password
	public String getPassword() {
		return password;
	}

	// Setter for password
	public void setPassword(String password) {
		this.password = password;
	}
}
