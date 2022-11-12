package com.bank.usecases.Accountant.loan;

import java.util.Scanner;

import com.bank.Exceptions.LoanException;
import com.bank.dao.AccountantDao;
import com.bank.dao.AccountantDaoImp;

public class payLoan {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter loan Id");
		int id=sc.nextInt();
		
		AccountantDao dao=new AccountantDaoImp();
		try {
			String res=dao.payLoan(id);
			System.out.println(res);
		} catch (LoanException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
