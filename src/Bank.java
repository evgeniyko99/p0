import java.util.Scanner; 

public class Bank {
	private static String response;
	
	public static void main(String[] args) {
		System.out.println("Do you already have an account?(Y|N):");
		Scanner scanner = new Scanner(System.in);
		response = scanner.next();
		if(response.equalsIgnoreCase("y")) {
			//call method to login
		} else if(response.equalsIgnoreCase("n")) {
			//call method to register
			BankAccount newAccount = new BankAccount();
			System.out.println("Enter your first name:");
			response = scanner.next();
			newAccount.setFirstName(response);
			System.out.println("Enter your last name:");
			response = scanner.next();
			newAccount.setLastName(response);
			System.out.println("Enter a username:");
			response = scanner.next();
			newAccount.setUsername(response);
			System.out.println("Enter a password:");
			response = scanner.next();
			newAccount.setPassword(response);
			
			//call method to login
		}
		scanner.close();
	}
}
