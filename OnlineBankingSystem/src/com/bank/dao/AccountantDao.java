package com.bank.dao;

import java.util.List;

import com.bank.Exceptions.AccountException;
import com.bank.Exceptions.AccountantException;
import com.bank.Exceptions.CustomerException;
import com.bank.Exceptions.LoanException;
import com.bank.Exceptions.TransactionException;
import com.bank.model.Account;
import com.bank.model.Accountant;
import com.bank.model.Loan;
import com.bank.model.Transaction;

public interface AccountantDao {
	public String registerAccountant(Accountant a) throws AccountantException;
	public Accountant loginAccountant(String username,String password) throws AccountantException;
	public String addNewCustomerAccount(String Customer_username,String Customer_password) throws AccountantException,CustomerException;
	public Account showDetailOfAccount(int account_number) throws AccountException,CustomerException;
	public List<Account> showAllAccount() throws AccountException;
	public String depositMoney(int account_number,int amount) throws AccountException;
	public String issueLoan(int account_number,int amount,int interest,String note) throws AccountException;
	public Loan getLoanInfo(int loan_id) throws LoanException;
	public List<Loan> getLoansByAccount(int acc_no) throws LoanException;
	public String payLoan(int loan_id) throws LoanException;
	public List<Transaction> getTransaction(int account_number) throws CustomerException,TransactionException;
}
