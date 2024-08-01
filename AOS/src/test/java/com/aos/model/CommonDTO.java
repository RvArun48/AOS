package com.aos.model;

import java.time.LocalTime;

public class CommonDTO {

	private static CommonDTO instance;
	private String userName;
	private String password;
	private BookTicketDTO bookTicketDTO;
	private LocalTime flightStartTime;
	private LocalTime flightEndTime;

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

	public void setBookTicketDTO(BookTicketDTO bookTicketDTO) {
		this.bookTicketDTO = bookTicketDTO;

	}

	public BookTicketDTO getBookTicketDTO() {
		return bookTicketDTO;
	}

	public void setFlightStartTime(LocalTime time) {
		this.flightStartTime = time;
	}

	public LocalTime getFlightStartTime() {
		return flightStartTime;
	}
	
	public void setFlightEndTime(LocalTime time) {
		this.flightEndTime = time;
	}

	public LocalTime getFlightEndTime() {
		return flightEndTime;
	}

}
