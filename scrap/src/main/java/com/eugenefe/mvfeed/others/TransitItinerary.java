package com.eugenefe.mvfeed.others;

public class TransitItinerary {
	private TransitLeg[] legs;

	public TransitItinerary(TransitLeg[] legs) {
		this.legs = legs;
	}

	public TransitLeg[] getLegs() {
		return legs;
	}

	public void setLegs(TransitLeg[] legs) {
		this.legs = legs;
	}
	
	
	
	
}
