package com.bank.dao;

import com.bank.Exceptions.CustomerException;
import com.bank.model.Customer;

public interface CustomerDao {
	public String registerCustomer(Customer c) throws CustomerException;
	public Customer loginCustomer(String username,String pass) throws CustomerException;
}
