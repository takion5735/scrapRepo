package com.eugenefe.mvfeed.isin;

import com.eugenefe.mvfeed.enums.EIsinProdType;

public class IsinMaster {
	private String prodType;
	private EIsinProdType eprodType;
	private String prodId;
	private String prodName;
	private String issuer;
	private String issueDate;
	private String maturityDate;
	private String listYn;
	private String listDate;
	private String genDate;
	
	public IsinMaster() {
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	
	
	

	public EIsinProdType getEprodType() {
		return eprodType;
	}

	public void setEprodType(EIsinProdType eprodType) {
		this.eprodType = eprodType;
	}

	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getListYn() {
		return listYn;
	}

	public void setListYn(String listYn) {
		this.listYn = listYn;
	}

	public String getListDate() {
		return listDate;
	}

	public void setListDate(String listDate) {
		this.listDate = listDate;
	}

	public String getGenDate() {
		return genDate;
	}

	public void setGenDate(String genDate) {
		this.genDate = genDate;
	}
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append(this.getProdType()).append(";")
			.append(this.getProdId()).append(";")
			.append(this.getProdName()).append(";")
			.append(this.getIssuer()).append(";")
			.append(this.getIssueDate()).append(";")
			.append(this.getMaturityDate()).append(";")
			.append(this.getListYn()).append(";")
			.append(this.getListDate()).append(";")
			.append(this.getGenDate());
		return str.toString();
	}
}
