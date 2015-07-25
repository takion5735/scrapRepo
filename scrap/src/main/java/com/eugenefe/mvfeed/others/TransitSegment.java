package com.eugenefe.mvfeed.others;

public class TransitSegment {
	private String kind;
	private boolean isMajor; 		//0 :false , 1 :true
	private boolean isImperial;     //Is this segment in a location where imperial unit are used (eg: miles)?
	private double distance;		// distance in KM
	private double duration; 		// estimated duration in minutes
	private String sName;
	private GeoLocation sPos;
	private String tName;
	private GeoLocation tPos;
	private TravelPrice price;
	private FlightItinerary[] itineraries;
//	private GeoLocation[] path;
	
	
	private TransitSegment() {
	}


	public String getKind() {
		return kind;
	}


	public void setKind(String kind) {
		this.kind = kind;
	}


	public boolean isMajor() {
		return isMajor;
	}


	public void setMajor(boolean isMajor) {
		this.isMajor = isMajor;
	}


	public boolean isImperial() {
		return isImperial;
	}


	public void setImperial(boolean isImperial) {
		this.isImperial = isImperial;
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


	public TravelPrice getPrice() {
		return price;
	}


	public void setPrice(TravelPrice price) {
		this.price = price;
	}


	public FlightItinerary[] getItineraries() {
		return itineraries;
	}


	public void setItineraries(FlightItinerary[] itineraries) {
		this.itineraries = itineraries;
	}


	

}
