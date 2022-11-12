package com.bank.usecases.Accountant.loan;

import java.util.List;
import java.util.Scanner;

import com.bank.Exceptions.LoanException;
import com.bank.dao.AccountantDao;
import com.bank.dao.AccountantDaoImp;
import com.bank.model.Loan;

public class getLoanInfo {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter 1 if you have Account Number and 0 if you have Loan_id");
		int x=sc.nextInt();
		AccountantDao dao=new AccountantDaoImp();
		if(x==1) {
			System.out.println("Enter Account number");
			int ac=sc.nextInt();
			try {
				List<Loan> list=dao.getLoansByAccount(ac);
				for(Loan l:list) {
					System.out.println(l.toString());
				}
			} catch (LoanException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}else {
			System.out.println("Enter Loan id");
			int id=sc.nextInt();
			try {
				Loan l=dao.getLoanInfo(id);
				System.out.println(l.toString());
			} catch (LoanException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}
}
