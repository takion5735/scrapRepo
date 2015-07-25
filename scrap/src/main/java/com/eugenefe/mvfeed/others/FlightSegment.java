package com.eugenefe.mvfeed.others;

import java.util.List;

public class FlightSegment {
	private String kind;
	private int isMajor; 		//0 :false , 1 :true
	private double distance;		// distance in KM
	private double duration; 		// estimated duration in minutes
	private String sCode;
	private String tCode;
	private TravelPrice indicativePrice;
	private List<FlightItinerary> itineraries;
	
	
	
	public FlightSegment(String kind, int isMajor, double distance, double duration, String sCode, String tCode,
			TravelPrice indicativePrice, List<FlightItinerary> itineraries) {
		super();
		this.kind = kind;
		this.isMajor = isMajor;
		this.distance = distance;
		this.duration = duration;
		this.sCode = sCode;
		this.tCode = tCode;
		this.indicativePrice = indicativePrice;
		this.itineraries = itineraries;
	}


	private FlightSegment() {
	}


	public String getKind() {
		return kind;
	}


	public void setKind(String kind) {
		this.kind = kind;
	}


	


	public int getIsMajor() {
		return isMajor;
	}


	public void setIsMajor(int isMajor) {
		this.isMajor = isMajor;
	}


	public double getDistance() {
		return distance;
	}


	public void setDistance(double distance) {
		this.distance = distance;
	}


	public double getDuration() {
		return duration;
	}


	public void setDuration(double duration) {
		this.duration = duration;
	}


	public String getsCode() {
		return sCode;
	}


	public void setsCode(String sCode) {
		this.sCode = sCode;
	}


	public String gettCode() {
		return tCode;
	}


	public void settCode(String tCode) {
		this.tCode = tCode;
	}


	public TravelPrice getIndicativePrice() {
		return indicativePrice;
	}


	public void setIndicativePrice(TravelPrice indicativePrice) {
		this.indicativePrice = indicativePrice;
	}


	public List<FlightItinerary> getItineraries() {
		return itineraries;
	}


	public void setItineraries(List<FlightItinerary> itineraries) {
		this.itineraries = itineraries;
	}


	
	
	


}
