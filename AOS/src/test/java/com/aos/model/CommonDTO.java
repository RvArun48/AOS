package com.aos.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CommonDTO {

	private static CommonDTO instance;
	private String userName;
	private String password;
	private BookTicketDTO bookTicketDTO;
	private LocalTime flightStartTime;
	private LocalTime flightEndTime;
	private List<PassengerDetailsDTO> allPassengerDTOList;

	private static List<String> fromAndToList = new ArrayList<>();
	private static List<String> baggageCheckinList = new ArrayList<>();
	private static List<String> baggageCabinList = new ArrayList<>();

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

	public static void setFromAndTo(String fromAndTo) {
		if (fromAndToList != null) {
			fromAndToList.add(fromAndTo);
		}

	}

	public static List<String> getFromAndTo() {
		return fromAndToList;
	}

	public static void clearFromAndToList() {
		if (fromAndToList != null) {
			fromAndToList.clear();
		}
	}

	public static void setCheckinBaggageData(String baggageData) {
		if (baggageCheckinList != null) {
			baggageCheckinList.add(baggageData);
		}

	}

	public static List<String> getCheckinBaggageData() {
		return baggageCheckinList;
	}

	public static void clearCheckinBaggageList() {
		if (baggageCheckinList != null) {
			baggageCheckinList.clear();
		}
	}

	public static void setCabinBaggageData(String baggageData) {
		if (baggageCabinList != null) {
			baggageCabinList.add(baggageData);
		}

	}

	public static List<String> getCabinBaggageData() {
		return baggageCabinList;
	}

	public static void clearCabinBaggageList() {
		if (baggageCabinList != null) {
			baggageCabinList.clear();
		}
	}

	public void setAllPassengerDTOList(List<PassengerDetailsDTO> allPassengerDTOList) {
		this.allPassengerDTOList = allPassengerDTOList;
	}

	public List<PassengerDetailsDTO> getAllPassengerDTOList() {
		return allPassengerDTOList;
	}

}
