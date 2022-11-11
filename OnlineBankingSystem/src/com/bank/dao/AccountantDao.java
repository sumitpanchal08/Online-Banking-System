package com.bank.dao;

import java.util.List;

import com.bank.Exceptions.AccountException;
import com.bank.Exceptions.AccountantException;
import com.bank.Exceptions.CustomerException;
import com.bank.model.Account;
import com.bank.model.Accountant;

public interface AccountantDao {
	public String registerAccountant(Accountant a) throws AccountantException;
	public Accountant loginAccountant(String username,String password) throws AccountantException;
	public String addNewCustomerAccount(String Customer_username,String Customer_password) throws AccountantException,CustomerException;
	public Account showDetailOfAccount(int account_number) throws AccountException,CustomerException;
	public List<Account> showAllAccount() throws AccountException;
	public String depositMoney(int account_number,int amount) throws AccountException;
}
