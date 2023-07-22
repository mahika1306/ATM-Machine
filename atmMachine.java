
import java.util.Scanner;
import java.util.*;

class ATM {
    float balance;
    int attempt=0;
   
    private Map<Integer, Double> pinBalanceMap = new HashMap<>();

    public ATM() {
        Scanner s = new Scanner(System.in);
        System.out.println("Press (N) for new user and Press (E) for existing user");
        String ip = s.next();
        if (ip.equalsIgnoreCase("N"))
            newPin();
        else if (ip.equalsIgnoreCase("E"))
            checkPin();
        else
            System.out.println("Invalid input!\nPlease enter a valid input");

    }

    public void newPin() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter your new PIN");
        int newpin = s.nextInt();
        System.out.println("Enter initial balance:");
        double initialBalance = s.nextDouble();
        pinBalanceMap.put(newpin, initialBalance);

        System.out.println("Your PIN has successfully been added!");
        menu(newpin);
    }

    public void checkPin() {
        Scanner s1 = new Scanner(System.in);

        System.out.println("Enter Your Pin");
        int pin = s1.nextInt();
        if (pinBalanceMap.containsKey(pin)) {
            System.out.println("Login Successful");
            menu(pin);
        }

        else {
        
        	if(attempt>3) {
        		System.out.println("Failed attempts!!");
        		System.out.println("System Exit");
        		
        		System.exit(0);
            
            
        }
        	else {
        		System.out.println("Invalid input. Please Enter the valid input");
                System.out.println("Attempts: "+attempt);
                attempt++;
                checkPin();
        	}
        }

    }

    public void depositMenu(int pin) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the amount to deposit:");
        double amount = s.nextDouble();
        double balance=pinBalanceMap.getOrDefault(pin,0.0);
        if (amount > 0) {
            balance += amount;
            pinBalanceMap.put(pin,balance);
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid amount. Please try again.");
        }

        menu(pin);
    }

    public void withdrawMoney(int pin) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the amount to withdraw:");
        double amount = s.nextDouble();
        double balance=pinBalanceMap.getOrDefault(pin,0.0);
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            pinBalanceMap.put(pin, balance); 
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Insufficient amount or invalid amount. Please try again.");
        }

        menu(pin);
    }

    public void checkBalance(int pin) {
        double balance=pinBalanceMap.getOrDefault(pin, 0.0);
        System.out.println("Your current balance is: " + balance+ " Rs");
        menu(pin);
    }

    public void menu(int pin) {
        System.out.println("Enter your choice:");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Deposit Menu");
        System.out.println("4. Exit");
        Scanner s = new Scanner(System.in);
        int ch = s.nextInt();
        switch (ch) {
            case 1:
                checkBalance(pin);
                break;
            case 2:
                withdrawMoney(pin);
                break;
            case 3:
                depositMenu(pin);
                break;
            case 4:
                System.out.println("Exit");
                System.exit(0);

        }

    }
}


public class atmMachine {
    public static void main(String[] args) {

        ATM atm = new ATM();
    }

}


