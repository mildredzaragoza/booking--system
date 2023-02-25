package com.aspire.guestregisterservice.model;

import java.util.ArrayList;

public class Response {
	private InfoResult info;
	private ArrayList<Guest> results;
	public Response(InfoResult info,  ArrayList<Guest> results) {
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
	public ArrayList<Guest> getResult() {
		return results;
	}
	public void setResult(ArrayList<Guest> results) {
		this.results = results;
	}
	
	
	
}
