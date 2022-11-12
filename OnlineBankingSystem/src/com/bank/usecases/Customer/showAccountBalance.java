package com.bank.usecases.Customer;

import java.util.Scanner;

import com.bank.Exceptions.AccountException;
import com.bank.dao.CustomerDao;
import com.bank.dao.CustomerDaoImp;

public class showAccountBalance {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Account Number");
		int ac=sc.nextInt();
		
		CustomerDao dao=new CustomerDaoImp();
		try {
			String res=dao.getAccountBalance(ac);
			System.out.println(res);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
