package com.bank.usecases;

import java.util.List;
import java.util.Scanner;

import com.bank.Exceptions.AccountException;
import com.bank.Exceptions.AccountantException;
import com.bank.Exceptions.CustomerException;
import com.bank.Exceptions.LoanException;
import com.bank.Exceptions.TransactionException;
import com.bank.dao.AccountantDao;
import com.bank.dao.AccountantDaoImp;
import com.bank.dao.CustomerDao;
import com.bank.dao.CustomerDaoImp;
import com.bank.model.Account;
import com.bank.model.Accountant;
import com.bank.model.Customer;
import com.bank.model.Loan;
import com.bank.model.Transaction;

public class Main {
	public static void main(String[] args) {
		System.out.println("WELCOME TO LIFE'S BANK");
		System.out.println("----------------------");
		menu();
	}
	public static void menu() {
		System.out.println("PLEASE SELECT FROM THE OPTIONS");
		System.out.println("1. Accountant");
		System.out.println("2. Customer");
		System.out.println("\n\n\n"+"99. Exit the Application");
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		switch(n) {
		case 1:
			accountant();
	        break;	
		case 2:
			customer();
			break;
		case 99:
			System.out.println("Thanks for using our service");
			break;
		default:
			System.out.println("Please select a valid input!!");
			System.out.println("-----------------------------");
			menu();
		}
	}
	public static void accountant() {
		System.out.println("PLEASE SELECT FROM THE OPTIONS");
		System.out.println("1. New Register Accountant");
		System.out.println("2. Login Accountant");
		System.out.println("\n\n\n"+"0. Go Back");
		System.out.println("99. Exit the Application");
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		switch(n) {
		case 1:
			registerAccountant();
	        break;	
		case 2:
			loginAccountant();
			break;
		case 0:
			menu();
			break;
		case 99:
			System.out.println("Thanks for using our service");
		default:
			System.out.println("Please select a valid input!!");
			System.out.println("-----------------------------");
			accountant();
		}
	}
	public static void customer() {
		System.out.println("PLEASE SELECT FROM THE OPTIONS");
		System.out.println("1. Register New Customer");
		System.out.println("2. Login Customer");
		System.out.println("\n\n\n"+"0. Go Back");
		System.out.println("99. Exit the Application");
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		switch(n) {
		case 1:
			registerCustomer();
	        break;	
		case 2:
			loginCustomer();
			break;
		case 0:
			menu();
			break;
		case 99:
			System.out.println("Thanks for using our service");
		default:
			System.out.println("Please select a valid input!!");
			System.out.println("-----------------------------");
			customer();
		}
	}
	public static void registerCustomer() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Name");
		String name=sc.next();
		System.out.println("Enter Email Address");
		String email=sc.next();
		System.out.println("Enter Username");
		String uname=sc.next();
		System.out.println("Enter Password(max of 12 character");
		String pass=sc.next();
		System.out.println("Enter Address");
		String address=sc.next();
		
		Customer c=new Customer(name,email,uname,pass,address);
		
		CustomerDao cd=new CustomerDaoImp();
		try {
			String result=cd.registerCustomer(c);
			System.out.println(result);
			customer();
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			customer();
		}
	}
	public static void loginCustomer() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter username");
		String uname=sc.next();
		System.out.println("Enter password");
		String pass=sc.next();
		
		CustomerDao cd=new CustomerDaoImp();
		
		try {
			Customer c=cd.loginCustomer(uname, pass);
			System.out.println("Customer Name:- "+c.getName());
			System.out.println("Customer Username:- "+c.getUsername());
			System.out.println("Customer Email:- "+c.getEmail());
			System.out.println("Customer Address:- "+c.getAddress());
			System.out.println("------------------------------------");
			customerAfterLogin(uname);
		}catch(CustomerException e){
			System.out.println(e.getMessage());
			customer();
		}
	}
	public static void registerAccountant() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Name");
		String name=sc.next();
		System.out.println("Enter Email Address");
		String email=sc.next();
		System.out.println("Enter Username");
		String uname=sc.next();
		System.out.println("Enter Password(max of 12 character");
		String pass=sc.next();
		System.out.println("Enter Address");
		String address=sc.next();
		
		Accountant at=new Accountant(name,email,uname,pass,address);
		
		AccountantDao dao=new AccountantDaoImp();
		try {
			String result=dao.registerAccountant(at);
			System.out.println(result);
			accountant();
		} catch (AccountantException e) {
			// TODO Auto-generated catch 
			System.out.println(e.getMessage());
			accountant();
		}
	}
	public static void loginAccountant() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Username");
		String username=sc.next();
		System.out.println("Enter Password");
		String pass=sc.next();
		
		AccountantDao dao=new AccountantDaoImp();
		try {
			Accountant at=dao.loginAccountant(username, pass);
			System.out.println("Accountant Name:- "+at.getName());
			System.out.println("Accountant Username:- "+at.getUsername());
			System.out.println("Accountant Email:- "+at.getEmail());
			System.out.println("-----------------------------------");
			accountantAfterLogin();
			
		}catch(AccountantException e) {
			System.out.println(e.getMessage());
			accountant();
		}
	}
	public static void sendMoney(String uname) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Your account Number");
		int acc_from=sc.nextInt();
		System.out.println("Enter Account Number where you wants to send money");
		int acc_to=sc.nextInt();
		System.out.println("Enter amount");
		int amt=sc.nextInt();
		System.out.println("Enter note for transaction(max of 50 character with no Spaces)");
		String note=sc.next();
		CustomerDao cd=new CustomerDaoImp();
			try {
				String result=cd.sendMoneyToAccount(uname, acc_from, acc_to, amt, note);
				System.out.println(result);
				System.out.println("----------------------------------");
				customerAfterLogin(uname);
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				System.out.println("----------------------------------");
				customerAfterLogin(uname);
			} catch (AccountException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				System.out.println("----------------------------------");
				customerAfterLogin(uname);
			}
	}
	public static void getAccountBalance(String uname) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Account Number");
		int ac=sc.nextInt();
		
		CustomerDao dao=new CustomerDaoImp();
		try {
			String res=dao.getAccountBalance(uname,ac);
			System.out.println(res);
			System.out.println("----------------------------------");
			customerAfterLogin(uname);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println("----------------------------------");
			customerAfterLogin(uname);
		}
	}
	public static void customerAccountsDetails(String uname) {
		CustomerDao dao=new CustomerDaoImp();
		try {
			List<Account> res=dao.getAllAccountDetails(uname);
			for(Account a:res) {
				System.out.println("Account Number:- "+a.getAccountNumber());
				System.out.println("Account Balance:- "+a.getBalance());
				System.out.println("-----------------------------------");
			}
			customerAfterLogin(uname);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println("----------------------------------");
			customerAfterLogin(uname);
		}
	}
	public static void customerAfterLogin(String uname) {
		System.out.println("--------------------------------");
		System.out.println("PLEASE SELECT FROM THE OPTIONS");
		System.out.println("1. Send Money to other Account");
		System.out.println("2. Show Account Balance");
		System.out.println("3. View all Accounts Details");
		System.out.println("4. View Account Transaction History by Account Number");
		System.out.println("\n\n\n"+"0. Go Back");
		System.out.println("99. Exit the Application");
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		switch(n) {
		case 1:
	        sendMoney(uname);
	        break;	
		case 2:
			getAccountBalance(uname);
			break;
		case 3:
			customerAccountsDetails(uname);
	        break;	
		case 4:
			customerTransactionHistory(uname);
			break;	
		case 0:
			customer();
			break;
		case 99:
			System.out.println("Thanks for using our service");
		default:
			System.out.println("Please select a valid input!!");
			System.out.println("-----------------------------");
			customerAfterLogin(uname);
		}
	}
    public static void accountantAfterLogin() {
    	System.out.println("--------------------------------");
    	System.out.println("PLEASE SELECT FROM THE OPTIONS");
		System.out.println("1. Open New Customer Account");
		System.out.println("2. Deposit Money to Customer Account");
		System.out.println("3. View Account Details by Account Number");
		System.out.println("4. View Account Transaction History by Account Number");
		System.out.println("5. View All Accounts with Details");
		System.out.println("6. Issue Loan to Account");
		System.out.println("7. View Loan Information by Loan Id");
		System.out.println("8. View Loan Information by Account Number");
		System.out.println("9. Pay Loan From Account");
		System.out.println("\n\n\n"+"0. Go Back");
		System.out.println("99. Exit the Application");
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		switch(n) {
		case 1:
			openCustomerAccount();
	        break;	
		case 2:
			depositMoney();
			break;
		case 3:
			accountDetails();
	        break;	
		case 4:
			transactionHistory();
			break;
		case 5:
			showAllAccounts();
			break;	
		case 6:
			issueLoan();
	        break;	
		case 7:
			loanInfoLoanId();
			break;
		case 8:
			loanInfoAccountNumber();
	        break;	
		case 9:
			payLoan();
			break;
		case 0:
			accountant();
			break;
		case 99:
			System.out.println("Thanks for using our service");
		default:
			System.out.println("Please select a valid input!!");
			System.out.println("-----------------------------");
			accountantAfterLogin();
		}
    }
    public static void customerTransactionHistory(String uname) {
    	Scanner sc=new Scanner(System.in);
		CustomerDao cd=new CustomerDaoImp();
		System.out.println("Enter Account Number");
		int acc=sc.nextInt();
			try {
				List<Transaction> list=cd.getTransaction(uname,acc);
				for(Transaction t:list) {
					System.out.println("Transaction Id:- "+t.getId());
					System.out.println("Transaction From:- "+t.getFrom_());
					System.out.println("Transaction To:- "+t.getTo_());
					System.out.println("Transaction Date & Time:- "+t.getDateTime());
					System.out.println("Transaction Amount:- "+t.getAmount());
					System.out.println("-------------------------------------------");
				}
				customerAfterLogin(uname);
				
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				customerAfterLogin(uname);
			} catch(TransactionException e) {
				System.out.println(e.getMessage());
				customerAfterLogin(uname);
			}
    }
    public static void payLoan() {
    	Scanner sc=new Scanner(System.in);
		System.out.println("Enter loan Id");
		int id=sc.nextInt();
		AccountantDao dao=new AccountantDaoImp();
		try {
			String res=dao.payLoan(id);
			System.out.println(res);
			accountantAfterLogin();
		} catch (LoanException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			accountantAfterLogin();
		}
    }
    public static void loanInfoAccountNumber() {
    	Scanner sc=new Scanner(System.in);
		AccountantDao dao=new AccountantDaoImp();
			System.out.println("Enter Account number");
			int ac=sc.nextInt();
			try {
				List<Loan> list=dao.getLoansByAccount(ac);
				for(Loan l:list) {
					System.out.println("Loan id:- "+l.getLoanId());
					System.out.println("Loan Account Number:- "+l.getAccountNumber());
					System.out.println("Loan Amount:- $"+l.getAmount());
					System.out.println("Loan Interest:- "+l.getInterest()+"%");
					System.out.println("Loan Payable Amount:- $"+l.getTotalPayableAmount());
					System.out.println("Loan Issue Date&Time:- "+l.getIssueDateTime());
					System.out.println("------------------------------------------");
					accountantAfterLogin();
				}
			} catch (LoanException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				accountantAfterLogin();
			}
    }
    public static void loanInfoLoanId() {
    	Scanner sc=new Scanner(System.in);
		AccountantDao dao=new AccountantDaoImp();
			System.out.println("Enter Loan id");
			int id=sc.nextInt();
			try {
				Loan l=dao.getLoanInfo(id);
				System.out.println("Loan id:- "+l.getLoanId());
				System.out.println("Loan Account Number:- "+l.getAccountNumber());
				System.out.println("Loan Amount:- $"+l.getAmount());
				System.out.println("Loan Interest:- "+l.getInterest()+"%");
				System.out.println("Loan Payable Amount:- $"+l.getTotalPayableAmount());
				System.out.println("Loan Issue Date&Time:- "+l.getIssueDateTime());
				System.out.println("------------------------------------------");
				accountantAfterLogin();
			} catch (LoanException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				accountantAfterLogin();
			}
		}
    public static void issueLoan() {
    	Scanner sc=new Scanner(System.in);
		System.out.println("Enter Account Number");
		int acc_no=sc.nextInt();
		System.out.println("Enter Amount");
		int amt=sc.nextInt();
		System.out.println("Enter Interest");
		int inter=sc.nextInt();
		System.out.println("Enter Reason(no Spaces max of 50 character)");
		String note=sc.next();
		
		AccountantDao dao=new AccountantDaoImp();
		try {
			String res=dao.issueLoan(acc_no, amt, inter, note);
			System.out.println(res);
			accountantAfterLogin();
		} catch (AccountException e) {
			System.out.println(e.getMessage());
			accountantAfterLogin();
		}
    }
    public static void showAllAccounts() {
    	AccountantDao dao=new AccountantDaoImp();
		try {
			List<Account> list=dao.showAllAccount();
            for(Account a:list) {
            	System.out.println("Account Number:- "+a.getAccountNumber());
    			System.out.println("Account Holder Name:- "+a.getAccountHolderName());
    			System.out.println("Account Holder Username:- "+a.getAccountHolderUsername());
    			System.out.println("Account Balance:- "+a.getBalance());
    			System.out.println("-------------------------------------------");
            }
            accountantAfterLogin();
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			 accountantAfterLogin();
		}
    }
    public static void transactionHistory() {
    	Scanner sc=new Scanner(System.in);
		AccountantDao cd=new AccountantDaoImp();
		System.out.println("Enter Account Number");
		int acc=sc.nextInt();
			try {
				List<Transaction> list=cd.getTransaction(acc);
				for(Transaction t:list) {
					System.out.println("Transaction Id:- "+t.getId());
					System.out.println("Transaction From:- "+t.getFrom_());
					System.out.println("Transaction To:- "+t.getTo_());
					System.out.println("Transaction Date & Time:- "+t.getDateTime());
					System.out.println("Transaction Amount:- "+t.getAmount());
					System.out.println("-------------------------------------------");
				}
				accountantAfterLogin();
				
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				accountantAfterLogin();
			} catch(TransactionException e) {
				System.out.println(e.getMessage());
				accountantAfterLogin();
			}
    }
    public static void accountBalance(String uname) {
    	Scanner sc=new Scanner(System.in);
		System.out.println("Enter Account Number");
		int ac=sc.nextInt();
		
		CustomerDao dao=new CustomerDaoImp();
		try {
			String res=dao.getAccountBalance(uname,ac);
			System.out.println(res);
			accountantAfterLogin();
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			accountantAfterLogin();
		}
    }
    public static void accountDetails() {
    	Scanner sc=new Scanner(System.in);
		System.out.println("Enter Account Number");
		int ac=sc.nextInt();
		
		AccountantDao dao=new AccountantDaoImp();
		try {
			Account a=dao.showDetailOfAccount(ac);
			System.out.println("Account Number:- "+a.getAccountNumber());
			System.out.println("Account Holder Name:- "+a.getAccountHolderName());
			System.out.println("Account Holder Username:- "+a.getAccountHolderUsername());
			System.out.println("Account Balance:- "+a.getBalance());
			System.out.println("-------------------------------------------");
			accountantAfterLogin();
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			accountantAfterLogin();
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			accountantAfterLogin();
		}
    }
    public static void openCustomerAccount() {
    	Scanner sc=new Scanner(System.in);
		System.out.println("Enter Customer Username");
		String uname=sc.next();
		System.out.println("Enter Customer Password");
		String pass=sc.next();
		
		AccountantDao dao=new AccountantDaoImp();
		try {
			String result=dao.addNewCustomerAccount(uname, pass);
			System.out.println(result);
			accountantAfterLogin();
		} catch (AccountantException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			accountantAfterLogin();
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			accountantAfterLogin();
		}
    }
    public static void depositMoney() {
    	Scanner sc=new Scanner(System.in);
		System.out.println("Enter Account Number");
		int acc_no=sc.nextInt();
		System.out.println("Enter Amount");
		int amt=sc.nextInt();
		
		AccountantDao dao=new AccountantDaoImp();
		try {
		    String res=dao.depositMoney(acc_no, amt);
		    System.out.println(res);
		    accountantAfterLogin();
		}catch(AccountException e) {
			System.out.println(e.getMessage());
			accountantAfterLogin();
		}
    }
}
