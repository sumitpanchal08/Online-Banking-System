package com.bank.usecases.Accountant;

import java.util.Scanner;

import com.bank.Exceptions.AccountException;
import com.bank.dao.AccountantDao;
import com.bank.dao.AccountantDaoImp;

public class depositMoney {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Account Number");
		int acc_no=sc.nextInt();
		System.out.println("Enter Amount");
		int amt=sc.nextInt();
		
		AccountantDao dao=new AccountantDaoImp();
		try {
		    String res=dao.depositMoney(acc_no, amt);
		    System.out.println(res);
		}catch(AccountException e) {
			System.out.println(e.getMessage());
		}
	}
}
