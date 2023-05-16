package com.happydog.dto;

import java.util.Date;

public class Review {
	private	int	rcode;
	private	String	id;
	private	int ocode;
	private	String	resdate;
	private	String	rcontent;
	private	String	rpoint;
	
	public Review(){
		Date now = new Date();
		this.resdate = now.toString();
	}

	public int getRcode() {
		return rcode;
	}

	public void setRcode(int rcode) {
		this.rcode = rcode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPcode() {
		return ocode;
	}

	public void setPcode(int ocode) {
		this.ocode = ocode;
	}

	public String getResdate() {
		return resdate;
	}

	public void setResdate(String resdate) {
		this.resdate = resdate;
	}

	public String getRcontent() {
		return rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	public String getRpoint() {
		return rpoint;
	}

	public void setRpoint(String rpoint) {
		this.rpoint = rpoint;
	}
}
