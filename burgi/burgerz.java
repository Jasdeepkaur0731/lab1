import java.util.Scanner;

public class burgerz {
    private static final String RESTAURANT_NAME = "Brampton Hamburgers";
    private static final String[] BURGER_MENU = {
        "Veggie Burger",
        "Deluxe Veggie Burger",
        "Chicken Burger",
        "Cheese Chicken Burger",
        "Cheese Bacon Burger",
        "Deluxe Bacon Burger",
        "Healthy Organic Burger"
    };
    private static final double[] BURGER_PRICES = {5.95, 7.95, 6.45, 8.50, 9.25, 10.95, 12.45};

    private static int customerCount = 5;
    private static double totalBill = 0;

    public static void main(String[] args) {
        welcomeMessage();

        for (int i = 1; i <= customerCount; i++) {
            System.out.println("\nCustomer " + i);
            System.out.println("---------------");

            double bill = processOrder();

            double tax = calculateTax(bill);
            bill += tax;

            double discount = calculateDiscount(bill);
            bill -= discount;

            totalBill += bill;
            System.out.println("Bill: $" + bill);
        }

        printTotalBillingAmount();
    }

    private static void welcomeMessage() {
        System.out.println("Welcome to " + RESTAURANT_NAME);
    }

    private static double processOrder() {
        int choice;
        double bill = 0;

        try {
            do {
                displayMenu();
                choice = getMenuChoice();

                if (choice == 8) {
                    break;
                } else if (choice < 1 || choice > 7) {
                    throw new Exception("Invalid choice!");
                }

                int quantity = getQuantity();
                bill += BURGER_PRICES[choice - 1] * quantity;

            } while (true);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }

        return bill;
    }

    private static void displayMenu() {
        System.out.println("\nMenu for " + RESTAURANT_NAME + ":");
        System.out.println("Type of Burger\tPrice");
        for (int i = 0; i < BURGER_MENU.length; i++) {
            System.out.println((i + 1) + ". " + BURGER_MENU[i] + "\t$" + BURGER_PRICES[i]);
        }
        System.out.println("8. Quit");
    }

    private static int getMenuChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select an item: ");
        int choice = scanner.nextInt();
        return choice;
    }

    private static int getQuantity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        return quantity;
    }

    private static double calculateTax(double bill) {
        return bill * 0.13;
    }

    private static double calculateDiscount(double bill) {
        if (bill > 100) {
            return bill * 0.10;
        } else if (bill > 50) {
            return bill * 0.05;
        }
        return 0;
    }

    private static void printTotalBillingAmount() {
        System.out.println("\nTotal billing amount for " + customerCount + " customers: $" + totalBill);
    }
}
