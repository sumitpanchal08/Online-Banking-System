package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bank.Exceptions.AccountException;
import com.bank.Exceptions.CustomerException;
import com.bank.Exceptions.TransactionException;
import com.bank.model.Customer;
import com.bank.model.Transaction;
import com.bank.utility.DBUtil;

public class CustomerDaoImp implements CustomerDao {

	@Override
	public String registerCustomer(Customer c) throws CustomerException {
		// TODO Auto-generated method stub
		String result="Customer not Registered";
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps=con.prepareStatement("insert into Customer values(?,?,?,?,?)");
			ps.setString(1, c.getName());
			ps.setString(2, c.getEmail());
			ps.setString(3, c.getUsername());
			ps.setString(4, c.getPassword());
			ps.setString(5, c.getAddress());
			int n=ps.executeUpdate();
			if(n>0) {
				result="Customer Register Successfull";
			}
		}catch(Exception e) {
			throw new CustomerException(e.getMessage());
		}
		return result;
	}

	@Override
	public Customer loginCustomer(String username, String pass) throws CustomerException {
		// TODO Auto-generated method stub
		Customer c=null;
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps=con.prepareStatement("select * from Customer where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				c=new Customer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			}else {
				throw new CustomerException("Invalid username and Password!!!");
			}
		}catch(Exception e) {
			throw new CustomerException(e.getMessage());
		}
		return c;
	}

	@Override
	public String sendMoneyToAccount(String uname,int ac_no_from, int ac_no_to, int amount,String note)
			throws CustomerException, AccountException {
		// TODO Auto-generated method stub
		if(amount<=0) {
			throw new CustomerException("Cannot Send less than 1Rs");
		}
		String result="Money not Send";
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps=con.prepareStatement("select account_number from account a join customer c on a.customer_username=c.username where username=? and account_number=?");
			ps.setString(1, uname);
			ps.setInt(2, ac_no_from);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				PreparedStatement ps1=con.prepareStatement("select balance from account where account_number=?");
				ps1.setInt(1, ac_no_from);
				ResultSet rs1=ps1.executeQuery();
				if(rs1.next()) {
					if(rs1.getInt(1)>=amount) {
						PreparedStatement ps2=con.prepareStatement("update account set balance=balance-? where account_number=?");
						PreparedStatement ps3=con.prepareStatement("update account set balance=balance+? where account_number=?");
						PreparedStatement ps4=con.prepareStatement("insert into Transaction(transaction_id,from_,to_,amount,note) values(?,?,?,?,?)");
						int id=giveTransactionId();
						ps4.setInt(1, id);
						ps4.setInt(2, ac_no_from);
						ps4.setInt(3, ac_no_to);
						ps4.setInt(4, amount);
						ps4.setString(5, note);
						ps3.setInt(1, amount);
						ps3.setInt(2, ac_no_to);
						ps2.setInt(1, amount);
						ps2.setInt(2, ac_no_from);
						int x=ps4.executeUpdate();
						if(x>0) {
							int y=ps3.executeUpdate();
							if(y>0) {
								int z=ps2.executeUpdate();
								result="Payment Done!!";
							}else {
								PreparedStatement ps5=con.prepareStatement("delete from transaction where transaction_id=?");
								ps5.setInt(1, id);
								ps5.executeUpdate();
								throw new AccountException("Recipent Account number is not Correct");
							}
						}
					}else {
						throw new AccountException("Not Enough balance in Your Account!!");
					}
				}
			}else {
				throw new AccountException("Account is not match for the Username!!");
			}
			
		}catch(Exception e) {
			throw new CustomerException(e.getMessage());
		}
		return result;
	}
	public int giveTransactionId() throws AccountException {
		int result=0;
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps1=con.prepareStatement("select transaction_id from transaction");
			ResultSet rs1=ps1.executeQuery();
			List<Integer> alist=new ArrayList<>();
			while(rs1.next()) {
				alist.add(rs1.getInt(1));
			}
			result=giveRandomNumber(1000000000,100000000,alist);
		}catch(Exception e) {
			throw new AccountException(e.getMessage());
		}
		return result;
	}
	public int giveRandomNumber(int m,int n,List<Integer> list) {
		int result=(int)(Math.random()*(m-n+1)+n);
		for(int i=0;i<list.size();i++) {
			if(list.get(i)==result) {
				return giveRandomNumber(m,n,list);
			}
		}
		return result;
	}

	@Override
	public List<Transaction> getTransaction(int account_number) throws CustomerException,TransactionException {
		// TODO Auto-generated method stub
		List<Transaction> list=new ArrayList<>();
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps=con.prepareStatement("select * from transaction where from_=? OR to_=? order by transaction_datetime");
			ps.setInt(1, account_number);
			ps.setInt(2, account_number);
			ResultSet rs=ps.executeQuery();
			int count=0;
			while(rs.next()) {
				Transaction t=new Transaction(rs.getInt(1),rs.getString(5),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(6));
				list.add(t);
				count++;
			}
			if(count==0) {
				throw new TransactionException("Invalid Account Number or No transaction done!!");
			}
		}catch(Exception e) {
			throw new CustomerException(e.getMessage());
		}
		return list;
	}

	@Override
	public String getAccountBalance(int ac) throws AccountException {
		// TODO Auto-generated method stub
		String result=null;
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps=con.prepareStatement("select balance from account where account_number=?");
			ps.setInt(1, ac);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				result="Your Account balance is $"+rs.getInt(1);
			}else {
				throw new AccountException("Invalid Account Number");
			}
		}catch(Exception e) {
			throw new AccountException(e.getMessage());
		}
		return result;
	}
}
