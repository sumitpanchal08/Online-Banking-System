package com.bank.dao;

import com.bank.Exceptions.AccountException;
import com.bank.Exceptions.CustomerException;
import com.bank.model.Customer;

public interface CustomerDao {
	public String registerCustomer(Customer c) throws CustomerException;
	public Customer loginCustomer(String username,String pass) throws CustomerException;
	public String sendMoneyToAccount(String uname,int ac_no_from,int ac_no_to,int amount,String note) throws CustomerException, AccountException;
	public String getTransaction(int account_number) throws CustomerException;
}
