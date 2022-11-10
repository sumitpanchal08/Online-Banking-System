package com.bank.model;

public class Accountant {
	private String name;
	private String email;
	private String username;
	private String password;
	private String address;
	public Accountant(){
		
	}
	public Accountant(String name,String email,String username,String password,String address){
		this.name=name;
		this.email=email;
		this.username=username;
		this.password=password;
		this.address=address;
	}
	@Override
	public String toString() {
		return "Name:- "+this.name+" Email:- "+this.email+" Username:- "+this.username+" Address:- "+this.address;
	}
	public String getName() {
		return this.name;
	}
	public String getEmail() {
		return this.email;
	}
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	public String getAddress() {
		return this.address;
	}
}
