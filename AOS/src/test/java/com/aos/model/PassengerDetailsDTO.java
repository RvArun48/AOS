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

	public String getIqamaExpiryMonthSrff() {
		return iqamaExpiryMonthSrff;
	}

	public void setIqamaExpiryMonthSrff(String iqamaExpiryMonthSrff) {
		this.iqamaExpiryMonthSrff = iqamaExpiryMonthSrff;
		
	}
	
	@SerializedName("iqama_expiry_year_srff")
	@Expose
	private String iqamaExpiryYearSrff;

	public String getIqamaExpiryYearSrff() {
		return iqamaExpiryYearSrff;
	}

	public void setIqamaExpiryYearSrff(String iqamaExpiryYearSrff) {
		this.iqamaExpiryYearSrff = iqamaExpiryYearSrff;
		
	}
	@SerializedName("nationality_expiry_month_gcc")
	@Expose
	private String nationalityExpiryMonthGcc;

	public String getNationalityExpiryMonthGcc() {
		return nationalityExpiryMonthGcc;
	}

	public void setNationalityExpiryMonthGcc(String nationalityExpiryMonthGcc) {
		this.nationalityExpiryMonthGcc = nationalityExpiryMonthGcc;
		
	}
	@SerializedName("nationality_expiry_year_gcc")
	@Expose
	private String nationalityExpiryYearGcc;

	public String getNationalityExpiryYearGcc() {
		return nationalityExpiryYearGcc;
	}

	public void setNationalityExpiryYearGcc(String nationalityExpiryYearGcc) {
		this.nationalityExpiryYearGcc = nationalityExpiryYearGcc;
		
	}
	
	@SerializedName("form_identity")
	@Expose
	private String formIdentity;

	public String getFormIdentity() {
		return formIdentity;
	}

	public void setFormIdentity(String formIdentity) {
		this.formIdentity = formIdentity;
		
	}
	
	@SerializedName("visa_number")
	@Expose
	private String visaNumber;

	public String getVisaNumber() {
		return visaNumber;
	}

	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
		
	}
	
	@SerializedName("place_of_birth_city")
	@Expose
	private String placeOfBirthCity;

	public String getPlaceOfBirthCity() {
		return placeOfBirthCity;
	}

	public void setPlaceOfBirthCity(String placeOfBirthCity) {
		this.placeOfBirthCity = placeOfBirthCity;
		
	}
	@SerializedName("place_of_birth_country")
	@Expose
	private String placeOfBirthCountry;

	public String getplaceOfBirthCountry() {
		return placeOfBirthCountry;
	}

	public void setplaceOfBirthCountry(String placeOfBirthCountry) {
		this.placeOfBirthCountry = placeOfBirthCountry;
		
	}

	@SerializedName("visa_issued_city")
	@Expose
	private String visaIssuedCity;

	public String getVisaIssuedCity() {
		return visaIssuedCity;
	}

	public void setVisaIssuedCity(String visaIssuedCity) {
		this.visaIssuedCity = visaIssuedCity;
		
	}
	@SerializedName("issued_country")
	@Expose
	private String issuedCountry;

	public String getIssuedCountry() {
		return issuedCountry;
	}

	public void setIssuedCountry(String issuedCountry) {
		this.issuedCountry = issuedCountry;
		
	}
	@SerializedName("visa_issued_day")
	@Expose
	private String visaIssuedDay;

	public String getVisaIssuedDay() {
		return visaIssuedDay;
	}

	public void setVisaIssuedDay(String visaIssuedDay) {
		this.visaIssuedDay = visaIssuedDay;
		
	}
	@SerializedName("visa_issued_month")
	@Expose
	private String visaIssuedMonth;

	public String getVisaIssuedMonth() {
		return visaIssuedMonth;
	}

	public void setVisaIssuedMonth(String visaIssuedMonth) {
		this.visaIssuedMonth = visaIssuedMonth;
		
	}
	
	@SerializedName("visa_issued_year")
	@Expose
	private String visaIssuedYear;

	public String getVisaIssuedYear() {
		return visaIssuedYear;
	}

	public void setVisaIssuedYear(String visaIssuedYear) {
		this.visaIssuedYear = visaIssuedYear;
		
	}
	@SerializedName("visa_expiry_day")
	@Expose
	private String visaExpiryDay;

	public String getVisaExpiryDay() {
		return visaExpiryDay;
	}

	public void setVisaExpiryDay(String visaExpiryDay) {
		this.visaExpiryDay = visaExpiryDay;
		
	}
	@SerializedName("visa_expiry_month")
	@Expose
	private String visaExpiryMonth;

	public String getVisaExpiryMonth() {
		return visaExpiryMonth;
	}

	public void setVisaExpiryMonth(String visaExpiryMonth) {
		this.visaExpiryMonth = visaExpiryMonth;
		
	}
	
	@SerializedName("visa_expiry_year")
	@Expose
	private String visaExpiryYear;

	public String getVisaExpiryYear() {
		return visaExpiryYear;
	}

	public void setVisaExpiryYear(String visaExpiryYear) {
		this.visaExpiryYear = visaExpiryYear;
		
	}
	
	@SerializedName("visa_required_country")
	@Expose
	private String visaRequiredCountry;

	public String getVisaRequiredCountry() {
		return visaRequiredCountry;
	}

	public void setVisaRequiredCountry(String visaRequiredCountry) {
		this.visaRequiredCountry = visaRequiredCountry;
		
	}
	
	
}