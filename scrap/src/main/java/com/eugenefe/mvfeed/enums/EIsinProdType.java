package com.eugenefe.mvfeed.enums;

public enum EIsinProdType {
	BOND 	("채권")
	,STOCK 	("주권")
	,ELS	("ELS")
	,ELB	("ELB")
	,DLS	("DLS")
	,DLB	("DLB")
	,CD		("CD")
	,BW		("신주인수권증서")
	,WR 	("신주인수권증권")
//	,WR		("주식워런트")
	,CP		("CP")
	,EBOND	("전자단기사채")
	,DR		("유가증권예탁증서")
	,STRIP	("스트립")
	,FUND	("펀드")
	,ELW	("주식워런트")
	,FUTURES ("선물")
	,OPTION	("옵션")
	,DERI	("선물/옵션")
	,INDEX	("지수")
	,GOLD	("일반상품(금)")
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
		System.out.println("Error : New Korean Name of EIsinProdType :" + korName );
		return null;
	}
}
