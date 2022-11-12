package com.bank.usecases.Accountant.loan;

import java.util.*;

import com.bank.Exceptions.AccountException;
import com.bank.dao.AccountantDao;
import com.bank.dao.AccountantDaoImp;
public class issueLoanToAccount {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Account Number");
		int acc_no=sc.nextInt();
		System.out.println("Enter Amount");
		int amt=sc.nextInt();
		System.out.println("Enter Interest");
		int inter=sc.nextInt();
		System.out.println("Enter Reason(no Spaces max of 50 character)");
		String note=sc.next();
		
		AccountantDao dao=new AccountantDaoImp();
		try {
			String res=dao.issueLoan(acc_no, amt, inter, note);
			System.out.println(res);
		} catch (AccountException e) {
			System.out.println(e.getMessage());
		}
	}
}
