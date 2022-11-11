package com.bank.usecases.Accountant;
import java.util.*;

import com.bank.Exceptions.AccountantException;
import com.bank.dao.AccountantDao;
import com.bank.dao.AccountantDaoImp;
import com.bank.model.Accountant;

public class RegisterAccountant {
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
		
		Accountant at=new Accountant(name,email,uname,pass,address);
		
		AccountantDao dao=new AccountantDaoImp();
		try {
			String result=dao.registerAccountant(at);
			System.out.println(result);
		} catch (AccountantException e) {
			// TODO Auto-generated catch 
			System.out.println(e.getMessage());
		}
	}
}
