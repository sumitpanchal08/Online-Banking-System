package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public Account addNewCustomerAccount(String Customer_username, String Customer_password)
			throws AccountantException, CustomerException {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Account showDetailOfAccount(int account_number) throws AccountException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> showAllAccount() throws AccountException {
		// TODO Auto-generated method stub
		return null;
	}

}
