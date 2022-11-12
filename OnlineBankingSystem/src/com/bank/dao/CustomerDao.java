package com.bank.dao;

import java.util.List;

import com.bank.Exceptions.AccountException;
import com.bank.Exceptions.CustomerException;
import com.bank.Exceptions.TransactionException;
import com.bank.model.Customer;
import com.bank.model.Transaction;

public interface CustomerDao {
	public String registerCustomer(Customer c) throws CustomerException;
	public Customer loginCustomer(String username,String pass) throws CustomerException;
	public String sendMoneyToAccount(String uname,int ac_no_from,int ac_no_to,int amount,String note) throws CustomerException, AccountException;
	public List<Transaction> getTransaction(int account_number) throws CustomerException,TransactionException;
	public String getAccountBalance(int ac) throws AccountException;
}
