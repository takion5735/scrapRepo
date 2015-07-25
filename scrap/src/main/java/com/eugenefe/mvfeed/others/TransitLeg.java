package com.eugenefe.mvfeed.others;

public class TransitLeg {
	private String url;
	private TransitHop[] hops;
	public TransitLeg() {
		super();
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public TransitHop[] getHops() {
		return hops;
	}
	public void setHops(TransitHop[] hops) {
		this.hops = hops;
	}
	
	
}
