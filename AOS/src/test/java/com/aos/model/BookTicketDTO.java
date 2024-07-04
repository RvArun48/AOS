package com.aos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookTicketDTO {

	@SerializedName("base_url")
	@Expose
	private String baseUrl;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@SerializedName("from")
	@Expose
	private String from;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	@SerializedName("to")
	@Expose
	private String to;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	@SerializedName("date")
	@Expose
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@SerializedName("relevant_keyword_from")
	@Expose
	private String relevantKeywordFrom;

	public String getRelevantKeywordFrom() {
		return relevantKeywordFrom;
	}

	public void setRelevantKeywordFrom(String relevantKeywordFrom) {
		this.relevantKeywordFrom = relevantKeywordFrom;
	}

	@SerializedName("relevant_keyword_to")
	@Expose
	private String relevantKeywordTo;

	public String getRelevantKeywordTo() {
		return relevantKeywordTo;
	}

	public void setRelevantKeywordTo(String relevantKeywordTo) {
		this.relevantKeywordTo = relevantKeywordTo;
	}

	@SerializedName("email")
	@Expose
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@SerializedName("adult_count")
	@Expose
	private int adultCount;

	public int getAdultCount() {
		return adultCount;
	}

	public void setAdultCount(int adultCount) {
		this.adultCount = adultCount;
	}

	@SerializedName("child_count")
	@Expose
	private int childCount;

	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

	@SerializedName("infant_count")
	@Expose
	private int infantCount;

	public int getInfantCount() {
		return infantCount;
	}

	public void setInfantCount(int infantCount) {
		this.infantCount = infantCount;
	}

	@SerializedName("mobile_no")
	@Expose
	private String mobileNo;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;

	}

	@SerializedName("country_mobile")
	@Expose
	private String countryMobile;

	public String getCountryMobile() {
		return countryMobile;
	}

	public void setCountryMobile(String countryMobile) {
		this.countryMobile = countryMobile;

	}

	@SerializedName("is_refundable")
	@Expose
	private boolean isRefundable;

	public boolean getIsRefundable() {
		return isRefundable;
	}
	
	
	@SerializedName("filter_airlines_select")
	@Expose
	private String filterAirlines;

	public String getFilterAirlines() {
		return filterAirlines;
	}

	public void setIsRefundable(String filterAirlines) {
		this.filterAirlines = filterAirlines;

	}

	@SerializedName("is_direct_flight")
	@Expose
	private boolean isDirectFlight;

	public boolean getIsDirectFlight() {
		return isDirectFlight;
	}

	public void setIsDirectFlight(boolean isDirectFlight) {
		this.isDirectFlight = isDirectFlight;

	}

//////////////////////////////////////////////

	@SerializedName("card_no")
	@Expose
	private String cardNo;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
//////////////////////////////////////////////

	@SerializedName("exp_Date")
	@Expose
	private String expDate;

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

//////////////////////////////////////////////

	@SerializedName("cvv")
	@Expose
	private String cvv;

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
//////////////////////////////////////////////

	@SerializedName("card_holder_name")
	@Expose
	private String cardHolderName;

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	
	@SerializedName("passenger_class")
	@Expose
	private String passengerClass;

	public String getPassengerClass() {
		return passengerClass;
	}

	public void setPassengerClass(String passengerClass) {
		this.passengerClass = passengerClass;
	}

}
