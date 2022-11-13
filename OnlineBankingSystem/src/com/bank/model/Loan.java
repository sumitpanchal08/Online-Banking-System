package com.bank.model;

public class Loan {
	private int loan_id;
	private int account_number;
	private int amount;
	private int interest;
	private int amount_to_pay;
	private String issue_datetime;
	private String note;
	public Loan() {
		
	}
	public Loan(int id,int acNo,int amount,int interest,int amount_to_pay,String issue_datetime,String note) {
		this.loan_id=id;
		this.account_number=acNo;
		this.amount=amount;
		this.interest=interest;
		this.amount_to_pay=amount_to_pay;
		this.issue_datetime=issue_datetime;
		this.note=note;
	}
	public String toString() {
		return "Loan_id: "+this.loan_id+" Account: "+this.account_number+" Amount: "+this.amount+" PayableAmount: "+this.amount_to_pay+" issue_datetime: "+this.issue_datetime+" Note: "+this.note;
	}
	public String getNote() {
		return this.note;
	}
	public String getIssueDateTime() {
		return this.issue_datetime;
	}
	public int getTotalPayableAmount() {
		return this.amount_to_pay;
	}
	public int getInterest() {
		return this.interest;
	}
	public int getLoanId() {
		return this.loan_id;
	}
	public int getAmount() {
		return this.amount;
	}
	public int getAccountNumber() {
		return this.account_number;
	}
}
