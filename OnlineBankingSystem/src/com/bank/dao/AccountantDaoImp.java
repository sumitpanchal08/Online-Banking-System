package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bank.Exceptions.AccountException;
import com.bank.Exceptions.AccountantException;
import com.bank.Exceptions.CustomerException;
import com.bank.model.Account;
import com.bank.model.Accountant;
import com.bank.utility.DBUtil;

public class AccountantDaoImp implements AccountantDao {

	@Override
	public String registerAccountant(Accountant a) throws AccountantException {
		// TODO Auto-generated method stub
		String result="Not Registered";
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps=con.prepareStatement("insert into Accountant values(?,?,?,?,?)");
			ps.setString(1, a.getName());
			ps.setString(2, a.getEmail());
			ps.setString(3, a.getUsername());
			ps.setString(4, a.getPassword());
			ps.setString(5, a.getAddress());
			int x=ps.executeUpdate();
			if(x>0) {
				result="Accountant Register Successfull";
			}
		}catch(Exception e) {
			throw new AccountantException(e.getMessage());
		}
		return result;
	}

	@Override
	public Accountant loginAccountant(String username, String password) throws AccountantException {
		// TODO Auto-generated method stub
		Accountant at=null;
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps=con.prepareStatement("select * from accountant where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				at=new Accountant(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			}else {
				throw new AccountantException("Invalid username or password");
			}
		}catch(Exception e) {
			throw new AccountantException(e.getMessage());
		}
		return at;
	}

	@Override
	public String addNewCustomerAccount(String Customer_username, String Customer_password)
			throws AccountantException, CustomerException {
		// TODO Auto-generated method stub
		String a="Account not Created";
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps=con.prepareStatement("Select * from customer where username=? and password=?");
			ps.setString(1, Customer_username);
			ps.setString(2, Customer_password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				PreparedStatement ps1=con.prepareStatement("insert into Account(account_number,customer_username) values(?,?)");
				int ac_no=giveAccountNo();
				ps1.setInt(1, ac_no);
				ps1.setString(2, Customer_username);
				int x=ps1.executeUpdate();
				if(x>0) {
					a="Account Created Successfully. Account no is "+ac_no;
				}
			}else {
				throw new CustomerException("Invalid username or password!!");
			}
		}catch(Exception e) {
			throw new CustomerException(e.getMessage());
		}
		return a;
	}
	public int giveAccountNo() throws AccountException {
		int result=0;
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps1=con.prepareStatement("select account_number from account");
			ResultSet rs1=ps1.executeQuery();
			List<Integer> alist=new ArrayList<>();
			while(rs1.next()) {
				alist.add(rs1.getInt(1));
			}
			result=giveRandomNumber(1000000,alist);
		}catch(Exception e) {
			throw new AccountException(e.getMessage());
		}
		return result;
	}
	public int giveRandomNumber(int n,List<Integer> list) {
		int result=(int)(Math.random()*n);
		for(int i=0;i<list.size();i++) {
			if(list.get(i)==result) {
				return giveRandomNumber(n,list);
			}
		}
		return result;
	}

	@Override
	public Account showDetailOfAccount(int account_number) throws AccountException, CustomerException {
		// TODO Auto-generated method stub
		Account a=null;
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps=con.prepareStatement(" select account_number,balance,name,username,email from account a join customer c on a.customer_username=c.username where account_number=?");
			ps.setInt(1, account_number);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				a=new Account(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));
			}else {
				throw new AccountException("Account Number is not Correct");
			}
		}catch(Exception e) {
			throw new AccountException(e.getMessage());
		}
		return a;
	}

	@Override
	public List<Account> showAllAccount() throws AccountException {
		// TODO Auto-generated method stub
		List<Account> list=new ArrayList<>();
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps=con.prepareStatement(" select account_number,balance,name,username,email from account a join customer c on a.customer_username=c.username group by account_number");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Account a=new Account(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));
				list.add(a);
			}
		}catch(Exception e) {
			throw new AccountException(e.getMessage());
		}
		return list;
	}

	@Override
	public String depositMoney(int account_number, int amount) throws AccountException {
		// TODO Auto-generated method stub
		String result="Money not Deposited..";
		if(amount<=0) {
			throw new AccountException("Cannot deposit less than 1Rs");
		}
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps=con.prepareStatement("update account set balance=balance+? where account_number=?");
			PreparedStatement ps1=con.prepareStatement("select balance from account where account_number=?");
			ps1.setInt(1, account_number);
			ps.setInt(1, amount);
			ps.setInt(2, account_number);
			int x=ps.executeUpdate();
			
			if(x>0) {
				ResultSet rs=ps1.executeQuery();
			    int updated_bal=0;
			    if(rs.next()) {
				    updated_bal=rs.getInt(1);
			    }
				result="Money Deposited Successfully, Updated balance is "+updated_bal;
			}else {
				result="Invalid Account Number!!!";
			}
		}catch(Exception e) {
			throw new AccountException(e.getMessage());
		}
		return result;
	}

}
