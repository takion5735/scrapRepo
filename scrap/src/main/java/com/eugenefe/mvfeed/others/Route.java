package com.eugenefe.mvfeed.others;
import java.util.List;

import javax.swing.text.Segment;


public class Route {

	private String  name;
	private double distance;
	private double duration;
	private TravelPrice indicativePrice;
	private List<Stop> stops;
	private List<FlightSegment> segments;
	public Route(String name, double distance, double duration, TravelPrice indicativePrice, List<Stop> stops,
			List<FlightSegment> FlightSegment) {
		this.name = name;
		this.distance = distance;
		this.duration = duration;
		this.indicativePrice = indicativePrice;
		this.stops = stops;
		this.segments = FlightSegment;
	}
	public Route() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public TravelPrice getIndicativePrice() {
		return indicativePrice;
	}
	public void setIndicativePrice(TravelPrice indicativePrice) {
		this.indicativePrice = indicativePrice;
	}
	public List<Stop> getStops() {
		return stops;
	}
	public void setStops(List<Stop> stops) {
		this.stops = stops;
	}
	public List<FlightSegment> getSegments() {
		return segments;
	}
	public void setSegments(List<FlightSegment> segments) {
		this.segments = segments;
	}
	
	
	
}
