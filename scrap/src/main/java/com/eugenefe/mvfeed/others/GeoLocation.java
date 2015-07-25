package com.eugenefe.mvfeed.others;
public class GeoLocation {
	private double lat; // float Latitude (WGS84);
	private double lng; // float Longitude (WGS84)

	
	public GeoLocation() {
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


}
