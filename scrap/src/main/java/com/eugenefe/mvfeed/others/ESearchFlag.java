package com.eugenefe.mvfeed.others;

public enum ESearchFlag {
//	 0x00000000( "Include all kinds of segments (Default)")
//	,0x000FFFFF ("Exclude all kinds of segments (See example below)")
//	,0x00000001 ("Exclude flight segments")
//	,0x00000002 ("Exclude flight itineraries")
//	,0x00000010 ("Exclude train segments")
//	,0x00000020 ("Exclude train itineraries")
	
	   ALL 				("0x00000000", "Include all kinds of segments (Default)")
	 , Nothing 			("0x000FFFFF","Exclude all kinds of segments (See example below)")
	 , ExclFlightSeg	("0x00000001","Exclude flight segments")
	 , ExclFlightItin 	("0x00000002", "Exclude flight itineraries")
	 , ExclTrainSeg 	("0x00000010", "Exclude train segments")
	 , ExclTrainItin 	("0x00000020", "Exclude train itineraries")
	 , ExclBusSeg 		("0x00000100", "Exclude bus segments")
	 , ExclBusItin 		("0x00000200", "Exclude bus itineraries")
	 , ExclFerrySeg		("0x00001000", "Exclude ferry segments")
	 , ExclFerryItin	("0x00002000", "Exclude ferry itineraries")
	 , ExclCarSeg 		("0x00010000", "Exclude car segments")
	 , ExclCommHop 		("0x00100000", "Exclude commuter hops (commuter = local bus, train, trams, subways, etc.)")
	 , ExclSpecHop 		("0x00200000", "Exclude special hops (special = funiculars, steam trains, tours, etc.)")
	 , ExclMinorSeg 	("0x00400000", "Exclude minor start segments")
	 , ExclMinorEndSeg 	("0x00800000", "Exclude minor end segments")
	 , ExclPaths 		("0x01000000", "Exclude paths (saves bandwidth)")
	 , ExclPrices		("0x04000000", "Exclude indicative prices (saves bandwidth)")
	 , Debug 			("0x10000000", "Disable scoring and pruning (debug only)")
	 , Flights			("0x000FFFF0", "Flight Only")
	 , Road 			("0x00010100", "Not via road: (0x00000100 + 0x00010000)") 
	;
	 
	 
	 
	private String  hexValue ;
	private String desc;

	private ESearchFlag(String hexValue, String desc) {
		this.hexValue = hexValue;
		this.desc = desc;
	}
	

//	private ESearchFlag(String desc) {
//		this.desc = desc;
//	}


	public String getHexValue() {
		return hexValue;
	}

	public String getDesc() {
		return desc;
	}
}
