package com.revature.bank.main;

import java.util.Arrays;
import java.util.Scanner; 

public class Bank {
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		bankApp();
		
		scanner.close();
	}
	
	private static void bankApp() {
		startUp();
		
		
	}
	
	private static void startUp() {
		BankAccount[] accounts = new BankAccount[10];
		boolean userContinue = true;
		
		while(userContinue) {
			System.out.println("Do you already have an account?(Y|N):");
			String input = scanner.nextLine();
			
			switch (input) {
			case "Y":
				//call method to login
				
				userContinue = false;
				
			case "N":
				//register();
				System.out.println("Enter your first name:");
				String firstName = scanner.next();
				System.out.println("Enter your last name:");
				String lastName = scanner.next();
				System.out.println("Enter a username:");
				String username = scanner.next();
				System.out.println("Enter a password:");
				String password = scanner.next();
				

				BankAccount newAccount = new BankAccount(firstName, lastName, username, password);
				int index = newAccount.getBankId() - 1;
				
				accounts[index] = newAccount;
				
				System.out.println(Arrays.toString(accounts));
			}
		}
	}
	
//	private static void register() {
//	
//	}
}

