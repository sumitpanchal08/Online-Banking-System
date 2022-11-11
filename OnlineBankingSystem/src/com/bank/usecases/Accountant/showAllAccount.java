package com.bank.usecases.Accountant;

import java.util.List;

import com.bank.Exceptions.AccountException;
import com.bank.dao.AccountantDao;
import com.bank.dao.AccountantDaoImp;
import com.bank.model.Account;

public class showAllAccount {
	public static void main(String[] args) {
		AccountantDao dao=new AccountantDaoImp();
		try {
			List<Account> list=dao.showAllAccount();
            for(Account a:list) {
            	System.out.println(a.toString());
            }
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
//		324979
//		27813241
	}
}
