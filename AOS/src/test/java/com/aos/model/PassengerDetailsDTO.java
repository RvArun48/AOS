package com.aos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PassengerDetailsDTO {

	@SerializedName("title")
	@Expose
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	@SerializedName("first_name")
	@Expose
	private String firstName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	//////////////////////////////////////////////
	
	@SerializedName("last_name")
	@Expose
	private String lastName;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	//////////////////////////////////////////////
	
	@SerializedName("dob_date")
	@Expose
	private String dobDate;

	public String getDobDate() {
		return dobDate;
	}

	public void setDobDate(String dobDate) {
		this.dobDate = dobDate;
	}
	//////////////////////////////////////////////
	
	@SerializedName("dob_month")
	@Expose
	private String dobMonth;

	public String getDobMonth() {
		return dobMonth;
	}

	public void setDobMonth(String dobMonth) {
		this.dobMonth = dobMonth;
	}
	//////////////////////////////////////////////
	
	@SerializedName("dob_year")
	@Expose
	private String dobYear;

	public String getDobYear() {
		return dobYear;
	}

	public void setDobYear(String dobYear) {
		this.dobYear = dobYear;
	}
	//////////////////////////////////////////////
	
	@SerializedName("passport_no")
	@Expose
	private String passportNo;

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	//////////////////////////////////////////////
	
	@SerializedName("issuing_country")
	@Expose
	private String issuingCountry;

	public String getIssuingCountry() {
		return issuingCountry;
	}

	public void setIssuingCountry(String issuingCountry) {
		this.issuingCountry = issuingCountry;
	}
	//////////////////////////////////////////////
	
	
	@SerializedName("nationality")
	@Expose
	private String nationality;

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	//////////////////////////////////////////////
	
	
	@SerializedName("pid_day")
	@Expose
	private String pidDay;

	public String getPidDay() {
		return pidDay;
	}

	public void setPidDay(String pidDay) {
		this.pidDay = pidDay;
	}
	//////////////////////////////////////////////
	
	@SerializedName("pid_month")
	@Expose
	private String pidMonth;

	public String getPidMonth() {
		return pidMonth;
	}

	public void setPidMonth(String pidMonth) {
		this.pidMonth = pidMonth;
	}
	//////////////////////////////////////////////
	
	@SerializedName("pid_year")
	@Expose
	private String pidYear;

	public String getPidYear() {
		return pidYear;
	}

	public void setPidYear(String pidYear) {
		this.pidYear = pidYear;
	}
	//////////////////////////////////////////////
	

	@SerializedName("ped_day")
	@Expose
	private String pedDay;

	public String getPedDay() {
		return pedDay;
	}

	public void setPedDay(String pedDay) {
		this.pedDay = pedDay;
	}
	//////////////////////////////////////////////
	
	@SerializedName("ped_month")
	@Expose
	private String pedMonth;

	public String getPedMonth() {
		return pedMonth;
	}

	public void setPedMonth(String pedMonth) {
		this.pedMonth = pedMonth;
	}
	//////////////////////////////////////////////
	
	@SerializedName("ped_year")
	@Expose
	private String pedYear;

	public String getPedYear() {
		return pedYear;
	}

	public void setPedYear(String pedYear) {
		this.pedYear = pedYear;
	}
	//////////////////////////////////////////////
	

	@SerializedName("meal_preference")
	@Expose
	private String mealPreference;

	public String getMealPreference() {
		return mealPreference;
	}

	public void setMealPreference(String mealPreference) {
		this.mealPreference = mealPreference;
	}
	//////////////////////////////////////////////

	@SerializedName("seat_preference")
	@Expose
	private String seatPreference;

	public String getSeatPreference() {
		return seatPreference;
	}

	public void setSeatPreference(String seatPreference) {
		this.seatPreference = seatPreference;
	}
	//////////////////////////////////////////////
	

	@SerializedName("special_requst")
	@Expose
	private String specialRequst;

	public String getSpecialRequst() {
		return specialRequst;
	}

	public void setSpecialRequst(String specialRequst) {
		this.specialRequst = specialRequst;
	}
	//////////////////////////////////////////////
	
	@SerializedName("airline")
	@Expose
	private String airline;

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}
	//////////////////////////////////////////////
	
	@SerializedName("frequent_flying_number")
	@Expose
	private String frequentFlyingNumber;

	public String getFrequentFlyingNumber() {
		return frequentFlyingNumber;
	}

	public void setFrequentFlyingNumber(String frequentFlyingNumber) {
		this.frequentFlyingNumber = frequentFlyingNumber;
	}
	


	
	
}