package com.eugenefe.mvfeed.others;

public class TransitHop {
	private String sName;
	private GeoLocation sPos;
	private String tName;
	private GeoLocation tPos;
	private double frequency ;
	private double duration;
	private TravelPrice price;
	private TransitLine[] lines;
	
	
	public TransitHop() {
	}


	public String getsName() {
		return sName;
	}


	public void setsName(String sName) {
		this.sName = sName;
	}


	public GeoLocation getsPos() {
		return sPos;
	}


	public void setsPos(GeoLocation sPos) {
		this.sPos = sPos;
	}


	public String gettName() {
		return tName;
	}


	public void settName(String tName) {
		this.tName = tName;
	}


	public GeoLocation gettPos() {
		return tPos;
	}


	public void settPos(GeoLocation tPos) {
		this.tPos = tPos;
	}


	public double getFrequency() {
		return frequency;
	}


	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}


	public double getDuration() {
		return duration;
	}


	public void setDuration(double duration) {
		this.duration = duration;
	}


	public TravelPrice getPrice() {
		return price;
	}


	public void setPrice(TravelPrice price) {
		this.price = price;
	}


	public TransitLine[] getLines() {
		return lines;
	}


	public void setLines(TransitLine[] lines) {
		this.lines = lines;
	}
	
	
	
}
