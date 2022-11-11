package com.bank.model;

public class Account {
	private int account_number;
	private int balance;
	private String account_holder_name;
	private String username;
	private String email;
	public Account() {
		
	}
	public Account(int ac,int bal,String name,String username,String email) {
		this.account_number=ac;
		this.balance=bal;
		this.account_holder_name=name;
		this.username=username;
		this.email=email;
	}
	public String toString() {
		return "Account No: "+this.account_number+" Account Holder Name: "+this.account_holder_name
		+" Username: "+this.username+" Balance: "+this.balance+" Email: "+this.email;		
	}
	public int getBalance() {
		return this.balance;
	}
	public int getAccountNumber() {
		return this.account_number;
	}
}
