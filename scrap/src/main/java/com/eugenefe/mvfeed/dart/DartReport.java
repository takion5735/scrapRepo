package com.eugenefe.mvfeed.dart;

import com.eugenefe.mvfeed.util.Scrappable;

public class DartReport implements Scrappable{
	private String comCode;
	private String comName;
	private String reportName;
	private String reporterName;
	private String submitDate;
	private String desc;
	private String url;
	
	
	public DartReport() {
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



	public String getReportName() {
		return reportName;
	}



	public void setReportName(String reportName) {
		this.reportName = reportName;
	}



	public String getReporterName() {
		return reporterName;
	}



	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}



	public String getSubmitDate() {
		return submitDate;
	}



	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}



	public String getDesc() {
		return desc;
	}



	public void setDesc(String desc) {
		this.desc = desc;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str. append(this.getComCode()).append(";")
		   	.append(this.getComName()).append(";")
		   	.append(this.getReportName()).append(";")
			.append(this.getReporterName()).append(";")
			.append(this.getSubmitDate()).append(";")
			.append(this.getUrl());
		return str.toString();
		
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DartReport){
			DartReport other = (DartReport)obj;
			if(this.comCode.equals(other.comCode)){
				return true;
			}
		}
		return false;
	}
	
	
}
