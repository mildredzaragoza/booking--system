package com.aspire.guestregisterservice.model;

public class Response {
	private InfoResult info;
	private Object results;
	
	public Response() {
		
	}
	public Response(InfoResult info,  Object results) {
		super();
		this.info = info;
		this.results = results;
	}
	public InfoResult getInfo() {
		return info;
	}
	public void setInfo(InfoResult info) {
		this.info = info;
	}
	public Object getResult() {
		return results;
	}
	public void setResult(Object results) {
		this.results = results;
	}
	
	
	
}
