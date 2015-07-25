package com.eugenefe.mvfeed.dart;

import com.eugenefe.mvfeed.util.Scrappable;

public class DartFnReport implements Scrappable{
	private String rcpNo;
	private String dcmNo;
	private String eleId;
	private String eleName;
	
	
	public DartFnReport() {
	}
	

	public DartFnReport(String rcpNo, String dcmNo, String eleId, String eleName) {
		this.rcpNo = rcpNo;
		this.dcmNo = dcmNo;
		this.eleId = eleId;
		this.eleName = eleName;
	}


	public String getRcpNo() {
		return rcpNo;
	}
	public void setRcpNo(String rcpNo) {
		this.rcpNo = rcpNo;
	}


	public String getDcmNo() {
		return dcmNo;
	}


	public void setDcmNo(String dcmNo) {
		this.dcmNo = dcmNo;
	}


	public String getEleId() {
		return eleId;
	}


	public void setEleId(String eleId) {
		this.eleId = eleId;
	}


	public String getEleName() {
		return eleName;
	}


	public void setEleName(String eleName) {
		this.eleName = eleName;
	}


	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append(this.getRcpNo()).append(";")
			.append(this.getDcmNo()).append(";")
		   	.append(this.getEleName()).append(";")
			.append("1;1;dart3.xsd");
		return str.toString();
		
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DartFnReport){
			DartFnReport other = (DartFnReport)obj;
			if(this.rcpNo.equals(other.rcpNo)){
				return true;
			}
		}
		return false;
	}
	
}
