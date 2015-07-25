package com.eugenefe.mvfeed.others;
public class Place {

	private String kind; // city, landmark, country, airport...
	private String longName;
	private String shortName;
	private String canonicalName;
	private double lat; 		// float Latitude (WGS84);
	private double lng; 		// float Longitude (WGS84)
	private String rad;;		 // string Radius (size of place in km)
	private int special;
	private String code;
	private String regionName; // string Region display name (optional)
	private String regionCode; // string Region code (optional)
	private String countryName; // string Country display name
	private String countryCode; // string Country code
//	private GeoLocation geoLocation;
	
	
	
	
	public Place() {
		
	}
	
//***************************Getter and Setter******************	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getLongName() {
		return longName;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public String getCanonicalName() {
		return canonicalName;
	}

	public void setCanonicalName(String canonicalName) {
		this.canonicalName = canonicalName;
	}

	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getRad() {
		return rad;
	}
	public void setRad(String rad) {
		this.rad = rad;
	}
	
	public int getSpecial() {
		return special;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setSpecial(int special) {
		this.special = special;
	}

	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	

}
