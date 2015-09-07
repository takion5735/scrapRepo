package scrap;

public enum EKsdElsDataGroup {
	 BASIC 		("기본정보", "issuInfoList")
	,STRIKE 	("행사가격", "bassetXrcList")
	,UNDERLYING	("기초자산","bassetInfoList")
	,PAYOFF		("수익구조","midValatSkedulRedCondiList")
	;
	 
	private String korName;
	private String sebCode;

	private EKsdElsDataGroup() {
	}

	private EKsdElsDataGroup(String korName, String sebCode) {
		this.korName = korName;
		this.sebCode = sebCode;
	}

	public String getKorName() {
		return korName;
	}
	public String getSebCode(){
		return sebCode;
	}
}
