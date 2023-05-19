import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class RBI {
	public static void main(String[] args) {

		System.out.println("*******Welcome to Banking Management System");
		System.out.println("\n");
		System.out.println("Do you want to open an account:1. Yes 2. No");

		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		if (s.equalsIgnoreCase("Yes")) {
			OpenAccount obj = new OpenAccount();
			obj.createAccount();
		}
		if (s.equalsIgnoreCase("No")) {
			BankAccount obj1 = new BankAccount();
			obj1.showMenu();
		}

	}

}

class OpenAccount {
	String name;
	int accountNum;
	String accountType;
	String dob;
	String bank;

	void createAccount() {
		Scanner sc = new Scanner(System.in);

		System.out.println("In which bank you want to open it: 1.SBI 2.PNB 3.ICICI");
		int sBank = sc.nextInt();
		if (sBank == 1) {
			bank = "SBI";
		}
		if (sBank == 2) {
			bank = "PNB";
		}
		if (sBank == 3) {
			bank = "ICICI";
		}
		System.out.println("Please enter your name: ");
		sc.nextLine();
		name = sc.nextLine();

		System.out.println("Please enter your date of birth: ");
		sc.nextLine();
		dob = sc.nextLine();

		System.out.println("What type of account do you want to open: 1. Saving  2. Current  ");
		int s = sc.nextInt();
		if (s == 1) {
			accountType = "Saving";
		}
		if (s == 2) {
			accountType = "Current";
		}
		System.out.println("Your account has been opened with following details ");
		System.out.println("Bank: " + bank);
		System.out.println("Name: " + name);
		System.out.println("DOB: " + dob);
		System.out.println("Account Type: " + accountType);
		System.out.println("Account Number: " + Math.random());

		System.out.println("\n");

		BankAccount obj1 = new BankAccount();
		obj1.showMenu();
		sc.close();

	}

}

class BankAccount {
	int balance;
	int previousTransaction;
	String customerName;
	String customerId;
	String accountType;
	double totalInterest;

	void calculateInterest(double balance) {
		System.out.println("What type of account do you have: 1. Saving 2. Current");
		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();

		if (s == 1) {
			accountType = "Saving";
			int r = 12;
			int t;
			System.out.println("Enter year to calculate interest");
			t = sc.nextInt();
			
			double finalAmount = balance * (1 + r * t);

			totalInterest = finalAmount - balance;
			System.out.println("Total interest earned is: " + totalInterest);
		}
		if (s == 2) {
			accountType = "Current";
			int r = 18;
			int t, n;
			System.out.println("Enter year to calculate interest");
			t = sc.nextInt();
			System.out.println("Enter the frequency that interest has been compound in a year");

			n = sc.nextInt();

			double finalAmount = balance * (Math.pow((1 + r / n), n * t));

			totalInterest = finalAmount - balance;
			System.out.println("Total interest earned is: " + totalInterest);
			sc.close();
		}

	}

	void deposit(int amount) {
		if (amount != 0)
			;
		{
			balance = balance + amount;
			System.out.println("Balance after deposit: " + balance);
			previousTransaction = amount;
		}

	}

	void withdraw(int amount) {
		if (amount != 0) {
			balance = balance - amount;
			System.out.println("Balance after withdraw:" + balance);
			previousTransaction = -amount;
		}
	}

	void getPreviousTransaction() {
		FileOutputStream out;
		PrintStream p;

		try {
			out = new FileOutputStream("C:\\Users\\acedi\\eclipse-workspace\\Project2\\src\\Statement.txt");
			p = new PrintStream(out);
			if (previousTransaction > 0) {
				p.append("Deposited: " + previousTransaction);
				System.out.println("Deposited: " + previousTransaction);
			} else if (previousTransaction < 0) {
				p.append("Withdrawn: " + previousTransaction);
				System.out.println("Withdrawn: " + Math.abs(previousTransaction));
			} else {
				System.out.println("No Transaction occured");
			}
			p.close();
		} catch (Exception e) {
			System.out.println("Error in printing the data" + e);
		}
	}

	void showMenu() {
		char option = '\0';
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the menu");
		System.out.println("\n");
		System.out.println("A. Check Balance");
		System.out.println("B. Deposit Amount");
		System.out.println("C. Withdraw Amount");
		System.out.println("D. See Previous Transaction");
		System.out.println("E. Calculate Interest");
		System.out.println("F. Exit");

		do {
			System.out.println("****************************************");
			System.out.println("Enter an option");
			System.out.println("****************************************");
			option = scanner.next().charAt(0);
			System.out.println("\n");

			switch (option) {
			case 'A':
				System.out.println("--------------------------");
				System.out.println("Balance = " + balance);
				System.out.println("\n");
				break;

			case 'B':
				System.out.println("--------------------------");
				System.out.println("Enter an amount to deposit: ");
				int amount = scanner.nextInt();
				deposit(amount);
				System.out.println("\n");
				break;

			case 'C':
				System.out.println("--------------------------");
				System.out.println("Enter an amount to withdraw: ");
				int amountA = scanner.nextInt();
				withdraw(amountA);
				System.out.println("\n");
				break;

			case 'D':
				System.out.println("--------------------------");
				getPreviousTransaction();
				System.out.println("\n");
				break;

			case 'E':
				System.out.println("--------------------------");
				calculateInterest(balance);
				System.out.println("\n");
				break;

			case 'F':
				System.out.println("--------------------------");
				break;

			default:
				System.out.println("Entered Invalid option!. Please enter Again");
				break;

			}

		} while (option != 'F');
		System.out.println("Thank you for using our services");
		scanner.close();
	}

}
