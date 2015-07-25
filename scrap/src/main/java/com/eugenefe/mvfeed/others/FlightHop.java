package com.eugenefe.mvfeed.others;

public class FlightHop {
	private String sCode;   //Source airport code (IATA)
	private String tCode;
	private String sTime;		//24-hour local time
	private String tTime;
	private String airline;
	private String flight;			// flight number
	private double duration ;		// flight time in minutes
	private int dayChange;			// Num day change in flight
	private double lDuration;  // Layover time in minutes
	private int	lDayChange;    // Num day changes during layover
	
	public FlightHop() {
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
	public String getsTime() {
		return sTime;
	}
	public void setsTime(String sTime) {
		this.sTime = sTime;
	}
	public String gettTime() {
		return tTime;
	}
	public void settTime(String tTime) {
		this.tTime = tTime;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public int getDayChange() {
		return dayChange;
	}
	public void setDayChange(int dayChange) {
		this.dayChange = dayChange;
	}
	public double getlDuration() {
		return lDuration;
	}
	public void setlDuration(double lDuration) {
		this.lDuration = lDuration;
	}
	public int getlDayChange() {
		return lDayChange;
	}
	public void setlDayChange(int lDayChange) {
		this.lDayChange = lDayChange;
	}
	
	
	
}
