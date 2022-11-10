package com.bank.usecases;
import java.util.*;

import com.bank.Exceptions.AccountantException;
import com.bank.dao.AccountantDao;
import com.bank.dao.AccountantDaoImp;
import com.bank.model.Accountant;
public class LoginAccountant {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Username");
		String username=sc.next();
		System.out.println("Enter Password");
		String pass=sc.next();
		
		AccountantDao dao=new AccountantDaoImp();
		try {
			Accountant at=dao.loginAccountant(username, pass);
			System.out.println(at.toString());
		}catch(AccountantException e) {
			System.out.println(e.getMessage());
		}
	}
}
