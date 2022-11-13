package com.bank.model;

public class Transaction {
	private int id;
	private String from_;
	private String to_;
	private int amount;
	private String note;
	private String dateTime;
	public Transaction(){
		
	}
	public Transaction(int id,String dateTime,String from_,String to_,int amount,String note) {
		this.id=id;
		this.from_=from_;
		this.to_=to_;
		this.amount=amount;
		this.note=note;
		this.dateTime=dateTime;
	}
	public String toString() {
		return "id: "+this.id+" From_account: "+this.from_+" To_account: "+this.to_+" Amount: "+this.amount+" Note: ("+this.note+") datetime: "+this.dateTime;
	}
	public int getId() {
		return this.id;
	}
	public String getFrom_() {
		return this.from_;
	}
	public String getTo_() {
		return this.to_;
	}
	public int getAmount() {
		return this.amount;
	}
	public String getDateTime() {
		return this.dateTime;
	}
	public String getNote() {
		return this.note;
	}
}
