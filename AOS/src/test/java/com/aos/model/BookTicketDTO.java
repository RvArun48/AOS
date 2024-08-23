package com.aos.model;

import org.openqa.selenium.WebElement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookTicketDTO {

	public static final String getpaymentGateway = null;
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

	
	
	@SerializedName("filter_airlines_select")
	@Expose
	private String filterAirlines;

	public String getFilterAirlines() {
		return filterAirlines;
	}

	public void setIsRefundable(String filterAirlines) {
		this.filterAirlines = filterAirlines;

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
	
	@SerializedName("trip_select")
	@Expose
	private String tripSelect;

	public String getTripSelect() {
		return tripSelect;
	}

	public void setTripSelect(String tripSelect) {
		this.tripSelect = tripSelect;
	}
	
	@SerializedName("baggage_only")
	@Expose
	private boolean baggageOnly;

	public boolean getBaggageOnly() {
		return baggageOnly;
	}

	public void setBaggageOnly(boolean baggageOnly) {
		this.baggageOnly = baggageOnly;

	}
	
	@SerializedName("is_refundable")
	@Expose
	private boolean isRefundable;

	public boolean getIsRefundable() {
		return isRefundable;
	}

	public void setIsRefundable(boolean isRefundable) {
		this.isRefundable = isRefundable;

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
	
	@SerializedName("preferred_airlines")
	@Expose
	private String preferredAirlines;

	public String getPreferredAirlines() {
		return preferredAirlines;
	}

	public void setPreferredAirlines(String preferredAirlines) {
		this.preferredAirlines = preferredAirlines;
	}
	
	
	@SerializedName("currency")
	@Expose
	private String currency;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@SerializedName("language")
	@Expose
	private String language;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	@SerializedName("multi_city_count")
	@Expose
	private int multiCityCount;

	public int getMultiCityCount() {
		return multiCityCount;
	}

	public void setMultiCityCount(int multiCityCount) {
		this.multiCityCount = multiCityCount;
	}
	/////////////////////////////////////////////////////
	@SerializedName("from_two")
	@Expose
	private String fromTwo;

	public String getFromTwo() {
		return fromTwo;
	}

	public void setFromTwo(String fromTwo) {
		this.fromTwo = fromTwo;
	}
	/////////////////////////////////////////////////////
	@SerializedName("to_two")
	@Expose
	private String toTwo;

	public String getToTwo() {
		return toTwo;
	}

	public void setToTwo(String toTwo) {
		this.toTwo = toTwo;
	}
	/////////////////////////////////////////////////////////

	@SerializedName("date_two")
	@Expose
	private String dateTwo;

	public String getDateTwo() {
		return dateTwo;
	}

	public void setDateTwo(String dateTwo) {
		this.toTwo = dateTwo;
	}
	////////////////////////////////////////////////////////////////////////
	@SerializedName("from_three")
	@Expose
	private String fromThree;

	public String getFromThree() {
		return fromThree;
	}

	public void setFromThree(String fromThree) {
		this.fromThree = fromThree;
	}
	/////////////////////////////////////////////////////
	@SerializedName("to_three")
	@Expose
	private String toThree;

	public String getToThree() {
		return toThree;
	}

	public void setToThree(String toThree) {
		this.toThree = toThree;
	}
	/////////////////////////////////////////////////////////

	@SerializedName("date_three")
	@Expose
	private String dateThree;

	public String getDateThree() {
		return dateThree;
	}

	public void setDateThree(String dateThree) {
		this.dateThree = dateThree;
	}
	////////////////////////////////////////////////////////////////////////
	@SerializedName("from_four")
	@Expose
	private String fromFour;

	public String getFromFour() {
		return fromFour;
	}

	public void setFromFour(String fromFour) {
		this.fromFour = fromFour;
	}
	/////////////////////////////////////////////////////
	@SerializedName("to_four")
	@Expose
	private String toFour;

	public String getToFour() {
		return toFour;
	}

	public void setToFour(String toFour) {
		this.toFour = toFour;
	}
	/////////////////////////////////////////////////////////

	@SerializedName("date_four")
	@Expose
	private String dateFour;

	public String getDateFour() {
		return dateFour;
	}

	public void setDateFour(String dateFour) {
		this.dateFour = dateFour;
	}
	
	@SerializedName("from_five")
	@Expose
	private String fromFive;

	public String getFromFive() {
		return fromFive;
	}

	public void setFromFive(String fromFive) {
		this.fromFive = fromFive;
	}
	/////////////////////////////////////////////////////
	@SerializedName("to_five")
	@Expose
	private String toFive;

	public String getToFive() {
		return toFive;
	}

	public void setToFive(String toFive) {
		this.toFive = toFive;
	}
	/////////////////////////////////////////////////////////

	@SerializedName("date_five")
	@Expose
	private String dateFive;

	public String getDateFive() {
		return dateFive;
	}

	public void setDateFive(String dateFive) {
		this.dateFive = dateFive;
	}
	
	@SerializedName("from_six")
	@Expose
	private String fromSix;

	public String getFromSix() {
		return fromSix;
	}

	public void setFromSix(String fromSix) {
		this.fromSix = fromSix;
	}
	/////////////////////////////////////////////////////
	@SerializedName("to_six")
	@Expose
	private String toSix;

	public String getToSix() {
		return toSix;
	}

	public void setToSix(String toSix) {
		this.toSix = toSix;
	}
	/////////////////////////////////////////////////////////

	@SerializedName("date_six")
	@Expose
	private String dateSix;

	public String getDateSix() {
		return dateSix;
	}

	public void setDateSix(String dateSix) {
		this.dateSix = dateSix;
	}
	
	
	@SerializedName("flexible_date")
	@Expose
	private boolean flexibleDate;

	public boolean getFlexibleDate() {
		return flexibleDate;
	}

	public void setFlexibleDate(boolean flexibleDate) {
		this.flexibleDate = flexibleDate;

	}
	
	@SerializedName("umrah_fare")
	@Expose
	private boolean umrahFare;

	public boolean getUmrahFare() {
		return umrahFare;
	}

	public void setUmrahFare(boolean umrahFare) {
		this.umrahFare = umrahFare;

	}
	
	@SerializedName("return_date")
	@Expose
	private String returnDate;

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.date = returnDate;
		
	}
		
		
		@SerializedName("relevant_keyword_preferred_airlines")
		@Expose
		private String relevantKeywordPreferredAirlines;

		public String getRelevantKeywordPreferredAirlines() {
			return relevantKeywordPreferredAirlines;
		}

		public void setRelevantKeywordPreferredAirlines(String relevantKeywordPreferredAirlines) {
			this.date = relevantKeywordPreferredAirlines;
		}
		
		
		
		@SerializedName("select_refundable")
		@Expose
		private String selectRefundable;

		public String getSelectRefundable() {
			return selectRefundable;
		}

		public void setSelectRefundable(String selectRefundable) {
			this.selectRefundable = selectRefundable;

		}	
		
		@SerializedName("select_not_refundable")
		@Expose
		private boolean selectNotRefundable;

		public boolean getSelectNotRefundable() {
			return selectNotRefundable;
		}

		public void setSelectNotRefundable(boolean selectNotRefundable) {
			this.selectNotRefundable = selectNotRefundable;

		}	
		
		
		@SerializedName("one_stop")
		@Expose
		private boolean oneStop;

		public boolean getOneStop() {
			return oneStop;
		}

		public void setOneStop(boolean oneStop) {
			this.oneStop = oneStop;

		}	
		

		@SerializedName("two_stop")
		@Expose
		private boolean twoStop;

		public boolean getTwoStop() {
			return twoStop;
		}

		public void setTwoStop(boolean twoStop) {
			this.twoStop = twoStop;

		}	
		

		@SerializedName("no_stop")
		@Expose
		private boolean noStop;

		public boolean getNoStop() {
			return noStop;
		}

		public void setNoStop(boolean noStop) {
			this.noStop = noStop;

		}
		
		
		
		@SerializedName("aircraft_types")
		@Expose
		private String aircraftTypes;

		public String getAircraftTypes() {
			return aircraftTypes;
		}

		public void setAircraftTypes(String aircraftTypes) {
			this.date = aircraftTypes;
		}
		
		
		@SerializedName("deprture_stop")
		@Expose
		private String deprtureStop;

		public String getDeprtureStop() {
			return deprtureStop;
		}

		public void setDeprtureStop(String deprtureStop) {
			this.date = deprtureStop;
			
		}
		
		@SerializedName("departure_airport")
		@Expose
		private String departureAirport;

		public String getDepartureAirport() {
			return departureAirport;
		}

		public void setDepartureAirport(String departureAirport) {
			this.date = departureAirport;
			
		}
		
		@SerializedName("arrival_iming")
		@Expose
		private String arrivalTiming;

		public String getarrivalTiming() {
			return arrivalTiming;
		}

		public void setarrivalTiming(String arrivalTiming) {
			this.date = arrivalTiming;
			
		}
		
		@SerializedName("stop")
		@Expose
		private String stop;

		public String getStop() {
			return stop;
		}

		public void setStop(String stop) {
			this.date = stop;
			
		}
		
		@SerializedName("fare_option")
		@Expose
		private String fareOption;

		public String getFareOption() {
			return fareOption;
		}

		public void setFareOption(String fareOption) {
			this.date = fareOption;
			
		}
		
		

		@SerializedName("payment_gateway")
		@Expose
		private String paymentGateway;

		public String getPaymentGateway() {
			return paymentGateway;
		}

		public void setPaymentGateway(String paymentGateway) {
			this.date = paymentGateway;
			
		}
		
		

		
	}
	



