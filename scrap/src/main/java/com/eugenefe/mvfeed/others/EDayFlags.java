package com.eugenefe.mvfeed.others;

public enum EDayFlags {
	Sunday ("0x01")
	,Monday ("0x02")
	,Tuesday ("0x04")
	,Wednesday ("0x08")
	,Thursday ("0x10")
	,Friday ("0x20")
	,Saturday ("0x40")
	;
	
	
	private String  hexValue;

	private EDayFlags(String hexValue) {
		this.hexValue = hexValue;
	}

	public String getHexValue() {
		return hexValue;
	}

		
}
