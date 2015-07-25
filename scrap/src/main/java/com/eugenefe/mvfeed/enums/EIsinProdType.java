package com.eugenefe.mvfeed.enums;

public enum EIsinProdType {
	BOND 	("채권")
	,STOCK 	("주식")
	,ELS	("ELS")
	,ELB	("ELB")
	,DLS	("DLS")
	,DLB	("DLB")
	,CD		("CD")
	,BW		("신주인수권")
	,WR		("주식워런트")
	,CP		("CP")
	,EBOND	("")
	,DR		("")
	,STRIP	("")
	,FUND	("펀드")
	,ELW	("ELW")
	,FUTURES ("선물")
	,OPTION	("옵션")
	,DERI	("파생상품")
	,INDEX	("지수")
	,GOLD	("금")
	;
	
	private String korName;

	private EIsinProdType(String korName) {
		this.korName = korName;
	}

	public String getKorName() {
		return korName;
	}

	public static EIsinProdType getProdType(String korName){
		for( EIsinProdType  aa : EIsinProdType.values()){
			if(aa.getKorName().equals(korName)){
				return aa;
			}
		}
		System.out.println("Error : New Korean Name of EIsinProdType " + korName );
		return null;
	}
}
