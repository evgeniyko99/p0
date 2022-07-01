package com.revature.bankapp;

import java.util.Scanner;

import com.revature.bankapp.data.BankAccountPostgres;
import com.revature.bankapp.data.PersonPostgres;
import com.revature.bankapp.data.TransactionPostgres;
import com.revature.bankapp.ds.ArrayList;
import com.revature.bankapp.ds.List;
import com.revature.bankapp.models.BankAccount;
import com.revature.bankapp.models.Person;
import com.revature.bankapp.models.Transaction;
import com.revature.bankapp.services.UserService;


public class BankAppDriver {
	private static UserService userService;
	private static BankAccountPostgres bankAccountPostgres = new BankAccountPostgres();
	private static PersonPostgres personPostgres = new PersonPostgres();
	private static TransactionPostgres transactionPostgres = new TransactionPostgres();
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		Person person = startMenu();
		System.out.println(person.toString());
		mainMenu(person);
		
		scanner.close();
	}
	
	private static ArrayList<BankAccount> accounts () {
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();

		return accounts;
	}

	
	//this method is to define the static variable userAccount with the user's account.
	private static Person startMenu() {
		boolean userContinue = true;
		BankAccount userAccount = new BankAccount();
		Person person = new Person();
		while (userContinue) {
			System.out.println("Do you have an account with us? y/n");
			String input = scanner.nextLine();
			switch (input) {
				case "y":
					person = logIn();
					userContinue=false;
					break;
				case "n":
					register();
					break;
			}
		}
		
		return person;
	}
	
	//this method will define userAccount if username and password are valid, or it will return to the bankApp
	private static Person logIn() {
		boolean loggingIn = true;
		
		while (loggingIn) {
			System.out.println("Enter your username: ");
			String username = scanner.nextLine();
			System.out.println("Enter your password: ");
			String password = scanner.nextLine();
			
			Person user = personPostgres.findByUsername(username);
			
			if (user==null) {
				System.out.println("Hmm, we couldn't find a user matching those credentials.");
				System.out.println("Do you want to try again? y/n");
				String input = scanner.nextLine().toLowerCase();
				// if they did not say "yes" to trying again
				if (!("y".equals(input))) {
					loggingIn = false;
				}
			} else {
				return user;
			}
		}
		return null;
	}
	
	//this method will create a new account
	private static void register () {
		boolean registering = true;
		System.out.println("Enter a username: ");
		String username = scanner.nextLine();
		System.out.println("Enter a password: ");
		String password = scanner.nextLine();
		System.out.println("Enter your first name: ");
		String firstName = scanner.nextLine();
		System.out.println("Enter your last name: ");
		String lastName = scanner.nextLine();
		System.out.println("Which type of account would you like to make? 1: savings 2: checking");
		String accountType = scanner.nextLine();
		Person newPerson = new Person(username, password, firstName, lastName);
		System.out.println(newPerson.toString());
		personPostgres.create(newPerson);
	}
	
	public static void mainMenu(Person person) {
		System.out.println("Welcome to Evgeniy's banking application!");
		Boolean menuLoop = true;
		while (menuLoop) {
			System.out.println("Please choose an option:");
			System.out.println("1: Create a new savings/checking account");
			System.out.println("2: Access an existing account");
			System.out.println("0: Close");
			String input = scanner.nextLine();
			switch (input) {
			case "1":
				System.out.println("Please choose an option:");
				System.out.println("1: savings");
				System.out.println("2: checking");
				int accountType = scanner.nextInt();
				BankAccount bankAccount = new BankAccount(person, accountType);
				bankAccountPostgres.create(bankAccount);
				String temp = scanner.nextLine();
				break;
			case "2":
				bankMenu(person);
				break;
			case "0":
				menuLoop = false;
				break;
			}

		}	
		System.out.println("Thank you! Have a nice day!");
	}
	
	public static void bankMenu(Person person) {
		List<BankAccount> accounts = bankAccountPostgres.findByUser(person);
		BankAccount currentAccount = new BankAccount();
		Boolean menuLoop = true;
		int input = 9;
		while (menuLoop) {
			if (((ArrayList<BankAccount>) accounts).getNextIndex() != 0) {
				System.out.println();
				System.out.println("Which account would you like to access?");
				for (int i = 0; i < ((ArrayList<BankAccount>) accounts).getNextIndex(); i++) {
					BankAccount temp = accounts.get(i);
					System.out.println(i + ": " + temp.getAccountType() + " - " + temp.getId() + " $" + temp.getBalance());
				}
				System.out.println("9: Return to main menu");
				input = scanner.nextInt();
			} else {
				System.out.println("You have no bank accounts, please create a new one.");
				menuLoop = false;
			}
			if (input == 9) {
				break;
			}
			currentAccount = accounts.get(input);
			System.out.println();
			System.out.println("Please choose an option:");
			System.out.println("1: Deposit");
			System.out.println("2: Withdraw");
			System.out.println("3: Transfer");
			System.out.println("4: Transaction History");
			System.out.println("0: Back");
			input = scanner.nextInt();
			switch (input) {
			case 1:
				deposit(currentAccount);
				break;
			case 2:
				withdraw(currentAccount);
				break;
			case 3:
				transfer(currentAccount);			
				break;
			case 4:
				transactionHistory(currentAccount);
				break;
			case 0:
				break;
			}
		}
	}
	
	public static void deposit (BankAccount userAccount) {
		System.out.println();
		System.out.println("How much would you like to deposit?");
		double input = scanner.nextDouble();
		double sum = input + userAccount.getBalance();
		userAccount.setBalance(sum);
		bankAccountPostgres.update(userAccount);
		System.out.println("$" + sum + " has been deposited into your account.");
	}
	
	public static void withdraw (BankAccount userAccount) {
		System.out.println();
		System.out.println("How much would you like to withdraw?");
		double input = scanner.nextDouble();
		double sum = 0.0;
		if (input > userAccount.getBalance()) {
			System.out.println("You do not have sufficient funds in your account");
		} else {
			sum = userAccount.getBalance() - input;
			userAccount.setBalance(sum);
			bankAccountPostgres.update(userAccount);
		}
		System.out.println("$" + sum + " has been withdrawn from your account.");
	}
	
	public static void transfer (BankAccount userAccount) {
		System.out.println();
		Boolean menuLoop = true;
		System.out.println("What is the bank ID you are transferring to?");
		int receiverId = scanner.nextInt();
		BankAccount receiverAccount = bankAccountPostgres.findById(receiverId);
		if (receiverAccount == null) {
			System.out.println("No account exists with this ID.");
			return;
		} 
		Person receiver = receiverAccount.getUser();
		System.out.println("How much would you like to transfer to " + receiver.getFirstName() + " " + receiver.getLastName() + "?");
		double amount = scanner.nextDouble();
		if (amount > userAccount.getBalance()) {
			System.out.println("You do not have sufficient funds for this transfer.");
			return;
		}
		double difference = userAccount.getBalance() - amount;
		double sum = receiverAccount.getBalance() + amount;
		userAccount.setBalance(difference);
		bankAccountPostgres.update(userAccount);
		receiverAccount.setBalance(sum);
		bankAccountPostgres.update(receiverAccount);
		System.out.println("$" + amount + " has been transferred out of " + userAccount.getAccountType() + " - " + userAccount.getId() + " and into " + receiverAccount.getAccountType() + " - " + receiverAccount.getId());
		Transaction transaction = new Transaction(userAccount.getId(), receiverAccount.getId(), amount);
		transactionPostgres.create(transaction);
	}
	
	public static void transactionHistory(BankAccount userAccount) {
		System.out.println();
		System.out.println(userAccount.getAccountType() + " - " + userAccount.getId() + " transaction history:");
		List<Transaction> transactions = transactionPostgres.findById(userAccount);
		System.out.println("Date \t Sender \t Receiver \t Amount");
		for (int i = 0; i < ((ArrayList<Transaction>) transactions).getNextIndex(); i++) {
			Transaction temp = transactions.get(i);
			System.out.println(temp.getTransactionDate() + " \t| " + temp.getSenderId() + " \t| " + temp.getReceiverId() + " \t| $" + temp.getTransactionAmount());
		}
	}
	
}

