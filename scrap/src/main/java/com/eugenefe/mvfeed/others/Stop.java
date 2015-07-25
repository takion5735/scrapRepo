package com.eugenefe.mvfeed.others;

public class Stop {

	private String kind;
	private String name;
//	private GeoLocation pos;
	private String pos;
	private String code;          //Stop code (eg: airport or station code) (optional)
	
	public Stop() {
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	
	
}
