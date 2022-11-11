package com.bank.usecases.Customer;

import java.util.Scanner;

import com.bank.Exceptions.CustomerException;
import com.bank.dao.CustomerDao;
import com.bank.dao.CustomerDaoImp;
import com.bank.model.Customer;

public class RegisterCustomer {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Name");
		String name=sc.next();
		System.out.println("Enter Email Address");
		String email=sc.next();
		System.out.println("Enter Username");
		String uname=sc.next();
		System.out.println("Enter Password(max of 12 character");
		String pass=sc.next();
		System.out.println("Enter Address");
		String address=sc.next();
		
		Customer c=new Customer(name,email,uname,pass,address);
		
		CustomerDao cd=new CustomerDaoImp();
		try {
			String result=cd.registerCustomer(c);
			System.out.println(result);
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
