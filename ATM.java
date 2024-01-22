package atm;
import java.util.List;
import java.util.Scanner;
public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create users
        User user1 = new User("123456", "1234", 1000.0);
        User user2 = new User("567891", "5678", 500.0);

        // ATM interface
        System.out.println("Welcome to the ATM");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        User currentUser = null;

        if (user1.getUserId().equals(userId) && user1.validatePin(pin)) {
            currentUser = user1;
        } else if (user2.getUserId().equals(userId) && user2.validatePin(pin)) {
            currentUser = user2;
        } else {
            System.out.println("Invalid User ID or PIN");
            System.exit(0);
        }

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Transaction History");
            System.out.println("6. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: $" + currentUser.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    currentUser.deposit(depositAmount);
                    System.out.println("Deposit successful");
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawalAmount = scanner.nextDouble();
                    currentUser.withdraw(withdrawalAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient's User ID: ");
                    String recipientId = scanner.next();
                    User recipient = (user1.getUserId().equals(recipientId)) ? user1 : user2;
                    System.out.print("Enter transfer amount: $");
                    double transferAmount = scanner.nextDouble();
                    currentUser.transfer(recipient, transferAmount);
                    break;
                case 5:
                    List<Transaction> transactions = currentUser.getTransactions();
                    System.out.println("Transaction History:");
                    for (Transaction transaction : transactions) {
                        System.out.println(transaction.getType() + ": $" + transaction.getAmount());
                    }
                    break;
                case 6:
                    System.out.println("Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

