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
	

	@SerializedName("document_type")
	@Expose
	private String documentType;

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
		
	}
	
	@SerializedName("nationality_id_gcc")
	@Expose
	private String nationalityIdGcc;

	public String getNationalityIdGcc() {
		return nationalityIdGcc;
	}

	public void setNationalityIdGcc(String nationalityIdGcc) {
		this.nationalityIdGcc = nationalityIdGcc;
		
	}
	
	@SerializedName("issuing_country_gcc")
	@Expose
	private String issuingCountryGcc;

	public String getIssuingCountryGcc() {
		return issuingCountryGcc;
	}

	public void setIssuingCountryGcc(String issuingCountryGcc) {
		this.issuingCountryGcc = issuingCountryGcc;
		
	}
	
	@SerializedName("nationality_gcc")
	@Expose
	private String nationalityGcc;

	public String getNationalityGcc() {
		return nationalityGcc;
	}

	public void setNationalityGcc(String nationalityGcc) {
		this.nationalityGcc = nationalityGcc;
		
	}
	@SerializedName("nationality_expiry_date_gcc")
	@Expose
	private String nationalityExpiryDateGcc;

	public String getNationalityExpiryDateGcc() {
		return nationalityExpiryDateGcc;
	}

	public void setNationalityExpiryDateGcc(String nationalityExpiryDateGcc) {
		this.nationalityExpiryDateGcc = nationalityExpiryDateGcc;
		
	}
	
	@SerializedName("iqama_id")
	@Expose
	private String iqamaId;

	public String getIqamaId() {
		return iqamaId;
	}

	public void setIqamaId(String iqamaId) {
		this.iqamaId = iqamaId;
		
	}
	
	@SerializedName("nationality_srff")
	@Expose
	private String nationalitySrff;

	public String getNationalitySrff() {
		return nationalitySrff;
	}

	public void setNationalitySrff(String nationalitySrff) {
		this.nationalitySrff = nationalitySrff;
		
	}
	
	@SerializedName("iqama_expiry_date_srff")
	@Expose
	private String iqamaExpiryDateSrff;

	public String getIqamaExpiryDateSrff() {
		return iqamaExpiryDateSrff;
	}

	public void setIqamaExpiryDateSrff(String iqamaExpiryDateSrff) {
		this.iqamaExpiryDateSrff = iqamaExpiryDateSrff;
		
	}
	
	@SerializedName("local_document_no")
	@Expose
	private String localDocumentNo;

	public String getLocalDocumentNo() {
		return localDocumentNo;
	}

	public void setLocalDocumentNo(String localDocumentNo) {
		this.localDocumentNo = localDocumentNo;
		
	}
	@SerializedName("iqama_expiry_month_srff")
	@Expose
	private String iqamaExpiryMonthSrff;

	public String getiqamaExpiryMonthSrff() {
		return iqamaExpiryMonthSrff;
	}

	public void setiqamaExpiryMonthSrff(String iqamaExpiryMonthSrff) {
		this.iqamaExpiryMonthSrff = iqamaExpiryMonthSrff;
		
	}
	
	@SerializedName("iqama_expiry_year_srff")
	@Expose
	private String iqamaExpiryYearSrff;

	public String getiqamaExpiryYearSrff() {
		return iqamaExpiryYearSrff;
	}

	public void setiqamaExpiryYearSrff(String iqamaExpiryYearSrff) {
		this.iqamaExpiryYearSrff = iqamaExpiryYearSrff;
		
	}
	@SerializedName("nationality_expiry_month_gcc")
	@Expose
	private String nationalityExpiryMonthGcc;

	public String getnationalityExpiryMonthGcc() {
		return nationalityExpiryMonthGcc;
	}

	public void setnationalityExpiryMonthGcc(String nationalityExpiryMonthGcc) {
		this.nationalityExpiryMonthGcc = nationalityExpiryMonthGcc;
		
	}
	@SerializedName("nationality_expiry_year_gcc")
	@Expose
	private String nationalityExpiryYearGcc;

	public String getnationalityExpiryYearGcc() {
		return nationalityExpiryYearGcc;
	}

	public void setnationalityExpiryYearGcc(String nationalityExpiryYearGcc) {
		this.nationalityExpiryYearGcc = nationalityExpiryYearGcc;
		
	}
	


	
	
}