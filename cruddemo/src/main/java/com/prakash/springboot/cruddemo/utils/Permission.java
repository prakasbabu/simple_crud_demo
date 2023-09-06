package com.prakash.springboot.cruddemo.utils;

public class Permission {
	private String url;
	private String method;
	public String getUrl() {
		return url;
	}
	public Permission(String url, String method) {
		super();
		this.url = url;
		this.method = method;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	
	

}
