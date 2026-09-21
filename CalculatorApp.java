import java.util.Scanner;

public class CalculatorApp {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("          SIMPLE CALCULATOR");
        System.out.println("       AVIRENZA TECHNOLOGIES");
        System.out.println("======================================");

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = getMenuChoice();

            switch (choice) {

                case 1:
                    addition();
                    break;

                case 2:
                    subtraction();
                    break;

                case 3:
                    multiplication();
                    break;

                case 4:
                    division();
                    break;

                case 5:
                    modulus();
                    break;

                case 6:
                    running = false;
                    System.out.println();
                    System.out.println("Thank you for using the calculator!");
                    System.out.println("Program ended successfully.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

            if (running) {
                System.out.println();
                System.out.println("--------------------------------------");
                System.out.println("Returning to main menu...");
                System.out.println("--------------------------------------");
            }
        }

        scanner.close();
    }

    // Display the calculator menu
    public static void displayMenu() {

        System.out.println();
        System.out.println("========== CALCULATOR MENU ==========");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Modulus");
        System.out.println("6. Exit");
        System.out.println("=====================================");
    }

    // Get and validate menu choice
    public static int getMenuChoice() {

        while (true) {

            System.out.print("Enter your choice (1-6): ");

            if (scanner.hasNextInt()) {

                int choice = scanner.nextInt();

                if (choice >= 1 && choice <= 6) {
                    return choice;
                }

                System.out.println(
                    "Invalid choice. Please enter a number between 1 and 6."
                );

            } else {

                System.out.println(
                    "Invalid input. Please enter a number."
                );

                scanner.next();
            }
        }
    }

    // Get a valid number from the user
    public static double getNumber(String message) {

        while (true) {

            System.out.print(message);

            if (scanner.hasNextDouble()) {

                return scanner.nextDouble();

            } else {

                System.out.println(
                    "Invalid input. Please enter a valid number."
                );

                scanner.next();
            }
        }
    }

    // Addition
    public static void addition() {

        double num1 = getNumber("Enter first number: ");
        double num2 = getNumber("Enter second number: ");

        double result = num1 + num2;

        System.out.println("Result: " + result);
    }

    // Subtraction
    public static void subtraction() {

        double num1 = getNumber("Enter first number: ");
        double num2 = getNumber("Enter second number: ");

        double result = num1 - num2;

        System.out.println("Result: " + result);
    }

    // Multiplication
    public static void multiplication() {

        double num1 = getNumber("Enter first number: ");
        double num2 = getNumber("Enter second number: ");

        double result = num1 * num2;

        System.out.println("Result: " + result);
    }

    // Division
    public static void division() {

        double num1 = getNumber("Enter first number: ");
        double num2 = getNumber("Enter second number: ");

        if (num2 == 0) {

            System.out.println(
                "Error: Division by zero is not allowed."
            );

        } else {

            double result = num1 / num2;

            System.out.println("Result: " + result);
        }
    }

    // Modulus
    public static void modulus() {

        double num1 = getNumber("Enter first number: ");
        double num2 = getNumber("Enter second number: ");

        if (num2 == 0) {

            System.out.println(
                "Error: Modulus by zero is not allowed."
            );

        } else {

            double result = num1 % num2;

            System.out.println("Result: " + result);
        }
    }
}