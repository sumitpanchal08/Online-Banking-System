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
}