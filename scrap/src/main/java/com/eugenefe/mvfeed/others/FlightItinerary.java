package com.eugenefe.mvfeed.others;

import java.util.List;

public class FlightItinerary {
	private List<FlightLeg> legs;

	
	
	private FlightItinerary() {
	}


	public FlightItinerary(List<FlightLeg> legs) {
		this.legs = legs;
	}


	public List<FlightLeg> getLegs() {
		return legs;
	}


	public void setLegs(List<FlightLeg> legs) {
		this.legs = legs;
	}

	
	
	
}
