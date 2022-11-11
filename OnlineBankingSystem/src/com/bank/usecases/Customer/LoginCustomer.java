package com.bank.usecases.Customer;

import java.util.Scanner;

import com.bank.Exceptions.CustomerException;
import com.bank.dao.CustomerDao;
import com.bank.dao.CustomerDaoImp;
import com.bank.model.Customer;

public class LoginCustomer {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter username");
		String uname=sc.next();
		System.out.println("Enter password");
		String pass=sc.next();
		
		CustomerDao cd=new CustomerDaoImp();
		
		try {
			Customer c=cd.loginCustomer(uname, pass);
			System.out.println(c.toString());
		}catch(CustomerException e){
			System.out.println(e.getMessage());
		}
		
		
		
	}
}
