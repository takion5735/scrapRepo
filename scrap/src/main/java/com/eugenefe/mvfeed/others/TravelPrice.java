package com.eugenefe.mvfeed.others;

public class TravelPrice {

	private double price;
	private String currency;
	private double nativePrice;
	private String nativeCurrency;
	private int isFreeTransfer;   //0 :false , 1: true

	
	public TravelPrice() {
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getNativePrice() {
		return nativePrice;
	}
	public void setNativePrice(double nativePrice) {
		this.nativePrice = nativePrice;
	}
	public String getNativeCurrency() {
		return nativeCurrency;
	}
	public void setNativeCurrency(String nativeCurrency) {
		this.nativeCurrency = nativeCurrency;
	}

	public int getIsFreeTransfer() {
		return isFreeTransfer;
	}

	public void setIsFreeTransfer(int isFreeTransfer) {
		this.isFreeTransfer = isFreeTransfer;
	}
	
	
	
	
}
