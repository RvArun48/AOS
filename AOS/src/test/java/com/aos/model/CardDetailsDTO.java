package com.aos.model;

import org.openqa.selenium.WebElement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardDetailsDTO {

	@SerializedName("payment_gateway")
	@Expose
	private String paymentGateway;

	public String getpaymentGateway() {
		return paymentGateway;
	}

	public void setpaymentGateway(String paymentGateway) {
		this.paymentGateway = paymentGateway;
	}
	

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
	@SerializedName("tapapi_cardnumber")
	@Expose
	private String tapapiCardnumber;

	public String getTapapiCardnumber() {
		return tapapiCardnumber;
	}

	public void setTapapiCardnumber(String tapapiCardnumber) {
		this.tapapiCardnumber = tapapiCardnumber;
		
	}
	@SerializedName("tapapi_cardname")
	@Expose
	private String tapapiCardname;

	public String getTapapiCardname() {
		return tapapiCardname;
	}

	public void setTapapiCardname(String tapapiCardname) {
		this.tapapiCardname = tapapiCardname;
		
	}
	@SerializedName("tapapi_exprimonth")
	@Expose
	private String tapapiExprimonth;

	public String getTapapiExprimonth() {
		return tapapiExprimonth;
	}

	public void setTapapiExprimonth(String tapapiExprimonth) {
		this.tapapiExprimonth = tapapiExprimonth;
		
	}
	@SerializedName("tapapi_expriyear")
	@Expose
	private String tapapiExpriyear;

	public String getTapapiExpriyear() {
		return tapapiExpriyear;
	}

	public void setTapapiExpriyear(String tapapiExpriyear) {
		this.tapapiExpriyear = tapapiExpriyear;
		
	}
	@SerializedName("tapapi_cardcvv")
	@Expose
	private String tapapiCardcvv;

	public String getTapapiCardcvv() {
		return tapapiCardcvv;
	}

	public void setTapapiCardcvv(String tapapiCardcvv) {
		this.tapapiCardcvv = tapapiCardcvv;
		
	}
	@SerializedName("tapapi_address_one")
	@Expose
	private String tapapiAddressOne;

	public String getTapapiAddressOne() {
		return tapapiAddressOne;
	}

	public void setTapapiAddressOne(String tapapiAddressOne) {
		this.tapapiAddressOne = tapapiAddressOne;
		
	}
	@SerializedName("tapapi_address_two")
	@Expose
	private String tapapiAddressTwo;

	public String getTapapiAddressTwo() {
		return tapapiAddressTwo;
	}

	public void setTapapiAddressTwo(String tapapiAddressTwo) {
		this.tapapiAddressTwo = tapapiAddressTwo;
		
	}
	@SerializedName("tapapi_country")
	@Expose
	private String tapapiCountry;

	public String getTapapiCountry() {
		return tapapiCountry;
	}

	public void setTapapiCountry(String tapapiCountry) {
		this.tapapiCountry = tapapiCountry;
		
	}
	@SerializedName("tapapi_state")
	@Expose
	private String tapapiState;

	public String getTapapiState() {
		return tapapiState;
	}

	public void setTapapiState(String tapapiState) {
		this.tapapiState = tapapiState;
		
	}
	@SerializedName("tapapi_pin")
	@Expose
	private String tapapiPin;

	public String getTapapiPin() {
	    return tapapiPin;
	}
	public void setTapapiPin(String tapapiPin) {
		this.tapapiPin = tapapiPin;
		
	}
	@SerializedName("tapapi_city")
	@Expose
	private String tapapiCity;

	public String getTapapiCity() {
	    return tapapiCity;
	}

}
