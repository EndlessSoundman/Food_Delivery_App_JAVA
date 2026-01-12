package util;

import java.util.Scanner;

/**
 * InputHandler utility class - Provides common input handling methods
 * Reduces code duplication for scanner input operations
 */
public class InputHandler {
    
    /**
     * Read an integer from the scanner with error handling
     * @param scanner Scanner object for input
     * @param prompt Prompt message to display
     * @return The integer value entered by user, or -1 if error occurred
     */
    public static int readInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        try {
            int value = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            return value;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
            return -1; // Indicate error
        }
    }
    
    /**
     * Read a mandatory string input (non-empty)
     * @param scanner Scanner object for input
     * @param prompt Prompt message to display
     * @param errorMessage Error message to display when input is empty
     * @return The non-empty string entered by user
     */
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
    
    /**
     * Read a valid email address with format validation
     * @param scanner Scanner object for input
     * @param prompt Prompt message to display
     * @return The valid email address entered by user
     */
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
                // Validate email format: x.x@x.x or x@x.x
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
    
    /**
     * Read a mandatory phone number (numbers only, non-empty)
     * @param scanner Scanner object for input
     * @param prompt Prompt message to display
     * @param errorMessage Error message to display when input is empty or invalid
     * @return The valid phone number entered by user (numbers only)
     */
    public static String readMandatoryPhoneNumber(Scanner scanner, String prompt, String errorMessage) {
        String phone = "";
        boolean validPhone = false;
        
        while (!validPhone) {
            System.out.print(prompt);
            phone = scanner.nextLine().trim();
            if (phone.isEmpty()) {
                System.out.println(errorMessage);
            } else {
                // Validate phone number contains only digits (and optionally spaces, dashes, parentheses)
                // But for simplicity, we'll accept only digits or digits with common separators
                if (phone.matches("^[0-9\\s\\-\\(\\)]+$") && phone.replaceAll("[^0-9]", "").length() > 0) {
                    // Accept if it contains at least one digit
                    validPhone = true;
                } else {
                    System.out.println("Phone number must contain only numbers. Please enter a valid phone number.");
                }
            }
        }
        return phone;
    }
    
    /**
     * Read a valid credit card number with format validation (XXXX-XXXX-XXXX)
     * @param scanner Scanner object for input
     * @param prompt Prompt message to display
     * @return The valid credit card number entered by user in format XXXX-XXXX-XXXX
     */
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
                // Validate credit card format: XXXX-XXXX-XXXX (4 digits, dash, 4 digits, dash, 4 digits)
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
