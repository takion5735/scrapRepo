package com.eugenefe.mvfeed.dart;

import com.eugenefe.mvfeed.util.Scrappable;

public class DartCompany implements Scrappable{
	private String comCode;
	private String comName;
	private String ceoName;
	private String krxShortCode;
	private String industry;
	private String bizNum;
	private int pageNum;
	
	
	public DartCompany() {
	}
	
	public DartCompany(String comCode, String comName, String ceoName,
			String krxShortCode, String industry, String bizNum) {
		this.comCode = comCode;
		this.comName = comName;
		this.ceoName = ceoName;
		this.krxShortCode = krxShortCode;
		this.industry = industry;
		this.bizNum = bizNum;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getCeoName() {
		return ceoName;
	}
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
	public String getKrxShortCode() {
		return krxShortCode;
	}
	public void setKrxShortCode(String krxShortCode) {
		this.krxShortCode = krxShortCode;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getBizNum() {
		return bizNum;
	}
	public void setBizNum(String bizNum) {
		this.bizNum = bizNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append(this.getPageNum()).append(";")
			.append(this.getComCode()).append(";")
		   	.append(this.getComName()).append(";")
			.append(this.getCeoName()).append(";")
			.append(this.getKrxShortCode()).append(";")
			.append(this.getIndustry()).append(";")
			.append(this.getBizNum());
		return str.toString();
		
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DartCompany){
			DartCompany other = (DartCompany)obj;
			if(this.comCode.equals(other.comCode)){
				return true;
			}
		}
		return false;
	}
	
	
}
