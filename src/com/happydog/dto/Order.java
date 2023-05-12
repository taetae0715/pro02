package com.happydog.dto;
import java.util.Date;

public class Order {
	private	int	ocode;
	private	String	id;
	private	String	pcode;
	private	int	amount = 1;
	private	String	price;
	private	String	odate;
	private	String	ostate = "배송준비중";
	private	String	tel;
	private	String	dname;
	private	String	addr;
	private	String	dcode;

	public Order(){
		Date now = new Date();
		this.odate = now.toString();
	}

	public int getOcode() {
		return ocode;
	}

	public void setOcode(int ocode) {
		this.ocode = ocode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOdate() {
		return odate;
	}

	public void setOdate(String odate) {
		this.odate = odate;
	}

	public String getOstate() {
		return ostate;
	}

	public void setOstate(String ostate) {
		this.ostate = ostate;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getDcode() {
		return dcode;
	}

	public void setDcode(String dcode) {
		this.dcode = dcode;
	}
	
}
