package com.bank.usecases.Accountant;

import java.util.Scanner;

import com.bank.Exceptions.AccountException;
import com.bank.Exceptions.CustomerException;
import com.bank.dao.AccountantDao;
import com.bank.dao.AccountantDaoImp;
import com.bank.model.Account;

public class showAccountDetail {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Account Number");
		int ac=sc.nextInt();
		
		AccountantDao dao=new AccountantDaoImp();
		try {
			Account a=dao.showDetailOfAccount(ac);
			System.out.println(a.toString());
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
}
