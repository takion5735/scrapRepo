package com.eugenefe.mvfeed.quandl;

import org.codehaus.jackson.annotate.JsonIgnore;

public class QDataSource {
	private String id;
	private String host;
	private String description;
	private String name;
	private String code;
	private int	datasets_count;
	@JsonIgnore
	private String concurrency;
	@JsonIgnore
	private String use_proxy;
	
	public String getUse_proxy() {
		return use_proxy;
	}
	public void setUse_proxy(String use_proxy) {
		this.use_proxy = use_proxy;
	}
	public String getConcurrency() {
		return concurrency;
	}
	public void setConcurrency(String concurrency) {
		this.concurrency = concurrency;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getDatasets_count() {
		return datasets_count;
	}
	public void setDatasets_count(int datasets_count) {
		this.datasets_count = datasets_count;
	}
	
	
}
