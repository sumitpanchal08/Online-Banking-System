package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bank.Exceptions.AccountException;
import com.bank.Exceptions.AccountantException;
import com.bank.Exceptions.CustomerException;
import com.bank.Exceptions.LoanException;
import com.bank.model.Account;
import com.bank.model.Accountant;
import com.bank.model.Loan;
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
			result=giveRandomNumber(10000000,1000000,alist);
		}catch(Exception e) {
			throw new AccountException(e.getMessage());
		}
		return result;
	}
	public int giveRandomNumber(int m,int n,List<Integer> list) {
		int result=(int)((Math.random()*(m-n+1)+n));
		for(int i=0;i<list.size();i++) {
			if(list.get(i)==result) {
				return giveRandomNumber(m,n,list);
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
			PreparedStatement ps4=con.prepareStatement("insert into Transaction(transaction_id,from_,to_,amount,note) values(?,?,?,?,?)");
			int id=giveTransactionId();
			ps4.setInt(1, id);
			ps4.setString(2,"byAccountant");
			ps4.setInt(3, account_number);
			ps4.setInt(4, amount);
			ps4.setString(5, "ByAccountant");
			ps1.setInt(1, account_number);
			ps.setInt(1, amount);
			ps.setInt(2, account_number);
			int x=ps.executeUpdate();
			if(x>0) {
				ResultSet rs=ps1.executeQuery();
			    int updated_bal=0;
			    if(rs.next()) {
				    updated_bal=rs.getInt(1);
				    ps4.executeUpdate();
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
	public int giveTransactionId() throws AccountException {
		int result=0;
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps1=con.prepareStatement("select transaction_id from transaction");
			ResultSet rs1=ps1.executeQuery();
			List<Integer> alist=new ArrayList<>();
			while(rs1.next()) {
				alist.add(rs1.getInt(1));
			}
			result=giveRandomNumber(999999999,100000000,alist);
		}catch(Exception e) {
			throw new AccountException(e.getMessage());
		}
		return result;
	}
	@Override
	public String issueLoan(int account_number, int amount, int interest, String note) throws AccountException {
		// TODO Auto-generated method stub
		String result="Loan not Issued!!";
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps=con.prepareStatement("select * from account where account_number=?");
			ps.setInt(1, account_number);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				PreparedStatement ps1=con.prepareStatement("insert into loan(loan_id,account_number,amount,interest,note,amount_to_pay) values(?,?,?,?,?,?)");
				int LoanId=getLoanId();
				ps1.setInt(1, LoanId);
				ps1.setInt(2, account_number);
				ps1.setInt(3, amount);
				ps1.setInt(4, interest);
				ps1.setString(5, note);
				int total_Amount=amount+((amount*interest)/100);
				ps1.setInt(6, total_Amount);
				int x=ps1.executeUpdate();
				if(x>0) {
					depositMoney(account_number,amount);
					result="Loan Issued Successfully, Loan id="+LoanId+" Total amount to be paid="+total_Amount;
				}
			}else {
				throw new AccountException("Invalid Account Number!!");
			}
		}catch(Exception e) {
			throw new AccountException(e.getMessage());
		}
		return result;
	}
	public int getLoanId() throws LoanException {
		int result=0;
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps1=con.prepareStatement("select loan_id from loan");
			ResultSet rs1=ps1.executeQuery();
			List<Integer> alist=new ArrayList<>();
			while(rs1.next()) {
				alist.add(rs1.getInt(1));
			}
			result=giveRandomNumber(1000000,100000,alist);
		}catch(Exception e) {
			throw new LoanException(e.getMessage());
		}
		return result;
	}

	@Override
	public Loan getLoanInfo(int loan_id) throws LoanException {
		// TODO Auto-generated method stub
		Loan l=null;
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps1=con.prepareStatement("select * from loan where loan_id=?");
			ps1.setInt(1, loan_id);
			ResultSet rs=ps1.executeQuery();
			if(rs.next()) {
				l=new Loan(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(5),rs.getInt(7),rs.getString(4),rs.getString(6));
			}else {
				throw new LoanException("Invalid Loan id");
			}
			
		}catch(Exception e) {
			throw new LoanException(e.getMessage());
		}
		return l;
	}

	@Override
	public List<Loan> getLoansByAccount(int acc_no) throws LoanException {
		// TODO Auto-generated method stub
		List<Loan> list=new ArrayList<>();
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps1=con.prepareStatement("select * from loan where account_number=?");
			ps1.setInt(1, acc_no);
			ResultSet rs=ps1.executeQuery();
			int count=0;
			while(rs.next()) {
				Loan l=new Loan(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(5),rs.getInt(7),rs.getString(4),rs.getString(6));
				list.add(l);
				count++;
			}
			if(count==0){
				throw new LoanException("Invalid Loan id!!");
			}
			
		}catch(Exception e) {
			throw new LoanException(e.getMessage());
		}
		return list;
	}

	@Override
	public String payLoan(int loan_id) throws LoanException {
		// TODO Auto-generated method stub
		String result="Payment Failed";
		try(Connection con=DBUtil.provideConnection()){
			PreparedStatement ps=con.prepareStatement("select account_number,amount_to_pay from loan where loan_id=?");
			ps.setInt(1, loan_id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				int ac=rs.getInt(1);
				int tot=rs.getInt(2);
				PreparedStatement ps1=con.prepareStatement("select balance from account where account_number=?");
				ps1.setInt(1, ac);
				ResultSet rs1=ps1.executeQuery();
				if(rs1.next()) {
					int bal=rs1.getInt(1);
					if(bal>=tot) {
						PreparedStatement ps2=con.prepareStatement("update account set balance=balance-? where account_number=?");
						PreparedStatement ps3=con.prepareStatement("update loan set amount_to_pay=0 where loan_id=?");
						PreparedStatement ps4=con.prepareStatement("insert into Transaction(transaction_id,from_,to_,amount,note) values(?,?,?,?,?)");
						int id=giveTransactionId();
						ps4.setInt(1, id);
						ps4.setInt(2,ac);
						ps4.setString(3,"LoanPayment");
						ps4.setInt(4, tot);
						ps4.setString(5, "LoanPayment");
						ps2.setInt(1,tot);
						ps2.setInt(2, ac);
						ps3.setInt(1, loan_id);
						int x=ps4.executeUpdate();
						if(x>0) {
							int y=ps3.executeUpdate();
							int z=ps2.executeUpdate();
							if(y>0 && z>0) {
								result ="Loan payment Done";
							}else {
								PreparedStatement ps5=con.prepareStatement("delete from transaction where transaction_id=?");
								ps5.setInt(1, id);
								ps5.executeUpdate();
								result="Error Occ";
							}
						}
					}else {
						throw new AccountException("Not Enough Balance in your Account");
					}
				}
			}else {
				throw new LoanException("Invalid Loan_id!!");
			}
		}catch(Exception e) {
			throw new LoanException(e.getMessage());
		}
		return result;
	}

}
