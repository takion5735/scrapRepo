package com.eugenefe.mvfeed.others;

public class CurrencyConverter {
	private String from;
	private String to;
	private double from_amount;
	private double to_amount;
	
	public CurrencyConverter(String from, String to, double from_amount, double to_amount) {
		this.from = from;
		this.to = to;
		this.from_amount = from_amount;
		this.to_amount = to_amount;
	}
	
	public CurrencyConverter() {
	}

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public double getFrom_amount() {
		return from_amount;
	}
	public void setFrom_amount(double from_amount) {
		this.from_amount = from_amount;
	}
	public double getTo_amount() {
		return to_amount;
	}
	public void setTo_amount(double to_amount) {
		this.to_amount = to_amount;
	}
	
	
}
