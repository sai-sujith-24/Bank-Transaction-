
import java.util.Scanner;

// Custom Exception 1
class InvalidWithdrawalAmountException extends Exception {
    public InvalidWithdrawalAmountException(String message) {
        super(message);
    }
}

// Custom Exception 2
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

// Custom Exception 3
class WithdrawalLimitExceededException extends Exception {
    public WithdrawalLimitExceededException(String message) {
        super(message);
    }
}

// BankAccount Class
class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;
    private double maxWithdrawalLimit;

    private int successfulTransactions = 0;
    private int unsuccessfulTransactions = 0;

    public BankAccount(String accountNumber, String holderName,
                       double balance, double maxWithdrawalLimit) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
        this.maxWithdrawalLimit = maxWithdrawalLimit;
    }

    // Withdrawal Method
    public void withdraw(double amount)
            throws InvalidWithdrawalAmountException,
                   InsufficientBalanceException,
                   WithdrawalLimitExceededException {

        if (amount <= 0) {
            throw new InvalidWithdrawalAmountException(
                "Withdrawal amount must be greater than zero.");
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Insufficient account balance.");
        }

        if (amount > maxWithdrawalLimit) {
            throw new WithdrawalLimitExceededException(
                "Withdrawal amount exceeds the maximum limit.");
        }

        // Update balance only after validation
        balance = balance - amount;
        successfulTransactions++;

        System.out.println("\nTransaction Successful!");
        System.out.println("Account Holder: " + holderName);
        System.out.println("Withdrawn Amount: Rs. " + amount);
        System.out.println("Remaining Balance: Rs. " + balance);
    }

    // Count failed transactions
    public void recordFailedTransaction() {
        unsuccessfulTransactions++;
    }

    // Display Account Summary
    public void displaySummary() {
        System.out.println("\n===== ACCOUNT SUMMARY =====");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + holderName);
        System.out.println("Final Balance: Rs. " + balance);
        System.out.println("Successful Transactions: "
                           + successfulTransactions);
        System.out.println("Unsuccessful Transactions: "
                           + unsuccessfulTransactions);
    }
}

// Main Class
public class BankTransaction {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Account Number: ");
        String accountNumber = sc.nextLine();

        System.out.print("Enter Account Holder Name: ");
        String holderName = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        System.out.print("Enter Maximum Withdrawal Limit: ");
        double limit = sc.nextDouble();

        BankAccount account = new BankAccount(
            accountNumber, holderName, balance, limit);

        int choice;

        do {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Withdraw Money");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter withdrawal amount: ");
                double amount = sc.nextDouble();

                try {
                    account.withdraw(amount);
                } catch (InvalidWithdrawalAmountException |
                         InsufficientBalanceException |
                         WithdrawalLimitExceededException e) {

                    account.recordFailedTransaction();
                    System.out.println("Transaction Failed: "
                                       + e.getMessage());
                }

            } else if (choice != 2) {
                System.out.println("Invalid menu choice.");
            }

        } while (choice != 2);

        account.displaySummary();
        sc.close();
    }
}
