package com.eugenefe.mvfeed.others;

import java.util.List;


//@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchResponse {
	private int serveTime;
	private List<Airport> airports;
	private List<Airline> airlines;
	private List<Agency> _agencies;
	private List<Route> routes;
	public SearchResponse(int serveTime, List<Airport> airports, List<Airline> airlines, List<Agency> agencies,
			List<Route> routes) {
		this.serveTime = serveTime;
		this.airports = airports;
		this.airlines = airlines;
//		this.agencies = agencies;
		this.routes = routes;
	}
	public SearchResponse() {
	}
	
	public int getServeTime() {
		return serveTime;
	}
	public void setServeTime(int serveTime) {
		this.serveTime = serveTime;
	}
	public List<Airport> getAirports() {
		return airports;
	}
	public void setAirports(List<Airport> airports) {
		this.airports = airports;
	}
	public List<Airline> getAirlines() {
		return airlines;
	}
	public void setAirlines(List<Airline> airlines) {
		this.airlines = airlines;
	}
	public List<Agency> getAgencies() {
		return _agencies;
	}
	public void setAgencies(List<Agency> agencies) {
		this._agencies = agencies;
	}
	public List<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	
	
	
	
	
	
}
