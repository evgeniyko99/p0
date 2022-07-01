package com.revature.bankapp.models;

import java.util.Objects;

public class BankAccount {
	//fields for a bank account
	private int id;
	private Person user;
	private double balance;
	private int accountType;
	
	{
		this.id++;
	}
	
	//constructor methods
	public BankAccount() {
		super();
		this.balance = 0;
	}
	public BankAccount(Person user, int accountType) {
		super();
		this.user = user;
		this.balance = 0.00;
		this.accountType = accountType;
	}
	public BankAccount(int id, Person user, int accountType, double balance) {
		super();
		this.id = id;
		this.user = user;
		this.balance = balance;
		this.accountType = accountType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getUser() {
		return user;
	}

	public void setUserId(Person user) {
		this.user = user;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAccountType() {
		String type = "";
		if (accountType == 1) {
			type = "savings";
		} else {
			type = "checking";
		}
		return type;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(accountType, balance, id, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		return accountType == other.accountType
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance) && id == other.id
				&& Objects.equals(user, other.user);
	}
	
	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", user=" + user + ", balance=" + balance + ", accountType=" + accountType+ "]";
	}
	
	
	
}

