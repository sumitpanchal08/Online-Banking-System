package com.bank.usecases.Accountant;

import java.util.Scanner;

import com.bank.Exceptions.AccountantException;
import com.bank.Exceptions.CustomerException;
import com.bank.dao.AccountantDao;
import com.bank.dao.AccountantDaoImp;

public class addNewCustomerAccount {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Customer Username");
		String uname=sc.next();
		System.out.println("Enter Customer Password");
		String pass=sc.next();
		
		AccountantDao dao=new AccountantDaoImp();
		try {
			String result=dao.addNewCustomerAccount(uname, pass);
			System.out.println(result);
		} catch (AccountantException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
