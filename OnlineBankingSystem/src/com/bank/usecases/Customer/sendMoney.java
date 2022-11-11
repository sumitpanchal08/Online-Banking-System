package com.bank.usecases.Customer;

import java.util.Scanner;

import com.bank.Exceptions.AccountException;
import com.bank.Exceptions.CustomerException;
import com.bank.dao.CustomerDao;
import com.bank.dao.CustomerDaoImp;
import com.bank.model.Customer;

public class sendMoney {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter username");
		String uname=sc.next();
		System.out.println("Enter password");
		String pass=sc.next();
		
		CustomerDao cd=new CustomerDaoImp();
		
		try {
			Customer c=cd.loginCustomer(uname, pass);
			System.out.println(c.toString());
			System.out.println("Login Successfull");
		}catch(CustomerException e){
			System.out.println(e.getMessage());
		}
		System.out.println("Enter Your account Number");
		int acc_from=sc.nextInt();
		System.out.println("Enter Account Number where you wants to send money");
		int acc_to=sc.nextInt();
		System.out.println("Enter amount");
		int amt=sc.nextInt();
		System.out.println("Enter note for transaction(max of 50 character with no Spaces)");
		String note=sc.next();
			try {
				String result=cd.sendMoneyToAccount(uname, acc_from, acc_to, amt, note);
				System.out.println(result);
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (AccountException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
	}
}
