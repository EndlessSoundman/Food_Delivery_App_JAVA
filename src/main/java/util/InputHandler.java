package util;

import java.util.Scanner;

public class InputHandler {
    public static int readInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        try {
            int value = scanner.nextInt();
            scanner.nextLine();
            return value;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return -1;
        }
    }
    
    public static String readMandatoryString(Scanner scanner, String prompt, String errorMessage) {
        String input = "";
        while (input.isEmpty()) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(errorMessage);
            }
        }
        return input;
    }
    
    public static String readEmail(Scanner scanner, String prompt) {
        String email = "";
        boolean validEmail = false;
        while (!validEmail) {
            System.out.print(prompt);
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("Email is required. Please enter your email.");
                System.out.println("Example: john.doe@gmail.com or johnya@icloud.com");
            } else {
                if (email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                    validEmail = true;
                } else {
                    System.out.println("Invalid email format. Please enter a valid email address.");
                    System.out.println("Example: john.doe@gmail.com or johnya@icloud.com");
                }
            }
        }
        return email;
    }
    
    public static String readMandatoryPhoneNumber(Scanner scanner, String prompt, String errorMessage) {
        String phone = "";
        boolean validPhone = false;
        while (!validPhone) {
            System.out.print(prompt);
            phone = scanner.nextLine().trim();
            if (phone.isEmpty()) {
                System.out.println(errorMessage);
            } else {
                if (phone.matches("^[0-9\\s\\-\\(\\)]+$") && phone.replaceAll("[^0-9]", "").length() > 0) {
                    validPhone = true;
                } else {
                    System.out.println("Phone number must contain only numbers. Please enter a valid phone number.");
                }
            }
        }
        return phone;
    }
    
    public static String readCreditCardNumber(Scanner scanner, String prompt) {
        String cardNumber = "";
        boolean validCard = false;
        while (!validCard) {
            System.out.print(prompt);
            cardNumber = scanner.nextLine().trim();
            if (cardNumber.isEmpty()) {
                System.out.println("Card number is required. Please enter your card number.");
                System.out.println("Format: XXXX-XXXX-XXXX (e.g., 1234-5678-9012)");
            } else {
                if (cardNumber.matches("^\\d{4}-\\d{4}-\\d{4}$")) {
                    validCard = true;
                } else {
                    System.out.println("Invalid card number format. Please enter the card number in the correct format.");
                    System.out.println("Format: XXXX-XXXX-XXXX (e.g., 1234-5678-9012)");
                }
            }
        }
        return cardNumber;
    }
}
