package com.revature.bank.main;

import java.util.Objects;

public class BankAccount {
	//fields for a bank account
	private int bankId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private double balance;
	
	{
		this.bankId++;
	}
	
	//constructor methods
	public BankAccount(String username, String password, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = 0;
	}
	
	//getter/setter methods
	public int getBankId() {
		return bankId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(balance, firstName, lastName, password, username);
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
		return Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
	
	@Override
	public String toString() {
		return "BankAccount [username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", balance=" + balance + "]";
	}
	
	
	
}
