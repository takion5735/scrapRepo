package com.eugenefe.mvfeed.others;

public class SearchResponseOld {

	private Airport[] airports;
	private Airline[] airlines;
	private Agency[] agencies;
	private Route[] route;
	
	public SearchResponseOld(Airport[] airports, Airline[] airlines, Agency[] agencies, Route[] route) {
		this.airports = airports;
		this.airlines = airlines;
		this.agencies = agencies;
		this.route = route;
	}
	
	public SearchResponseOld() {
	}
	
	
	public Airport[] getAirports() {
		return airports;
	}
	public void setAirports(Airport[] airports) {
		this.airports = airports;
	}
	public Airline[] getAirlines() {
		return airlines;
	}
	public void setAirlines(Airline[] airlines) {
		this.airlines = airlines;
	}
	public Agency[] getAgencies() {
		return agencies;
	}
	public void setAgencies(Agency[] agencies) {
		this.agencies = agencies;
	}
	public Route[] getRoute() {
		return route;
	}
	public void setRoute(Route[] route) {
		this.route = route;
	}
	
	
	
	
}
