package com.happydog.dto;

import java.util.Date;

public class Pay {
	private	int pno;
	private	String	id;
	private	int	ocode;
	private	String	type;
	private	String	type_no;
	private	int	price;
	private	String	pdate;
	
	public Pay(){
		Date now = new Date();
		this.pdate = now.toString();
	}
	
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getOcode() {
		return ocode;
	}
	public void setOcode(int ocode) {
		this.ocode = ocode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType_no() {
		return type_no;
	}
	public void setType_no(String type_no) {
		this.type_no = type_no;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPdate() {
		return pdate;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
	

}
