package com.eugenefe.mvfeed.others;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class GeocodeResponse {
	private String query;
	private String countryCode;
	private String languageCode;
	private List<Place> places;
	
//	public GeocodeResponse(String query, String countryCode, String languageCode, List<Place> places) {
////		super();
//		this.query = query;
//		this.countryCode = countryCode;
//		this.languageCode = languageCode;
//		this.places = places;
//	}
	public GeocodeResponse() {
	}
	
	
	@JsonIgnore
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	@JsonIgnore
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public List<Place> getPlaces() {
		return places;
	}
	public void setPlaces(List<Place> places) {
		this.places = places;
	}
	
	
	
	
}
