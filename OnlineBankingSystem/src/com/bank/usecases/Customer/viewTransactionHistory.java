package com.bank.usecases.Customer;

import java.util.List;
import java.util.Scanner;

import com.bank.Exceptions.CustomerException;
import com.bank.Exceptions.TransactionException;
import com.bank.dao.CustomerDao;
import com.bank.dao.CustomerDaoImp;
import com.bank.model.Customer;
import com.bank.model.Transaction;

public class viewTransactionHistory {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		CustomerDao cd=new CustomerDaoImp();
		System.out.println("Enter Your account Number");
		int acc=sc.nextInt();
			try {
				List<Transaction> list=cd.getTransaction(acc);
				for(Transaction t:list) {
					System.out.println(t.toString());
				}
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch(TransactionException e) {
				System.out.println(e.getMessage());
			}
	}
}
