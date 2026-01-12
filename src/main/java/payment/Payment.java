package payment;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import order.Order;
import order.OrderStatus;
import dish.Dish;
import util.InputHandler;
import util.DisplayFormatter;
import user.User;
import discount.DiscountStrategy;
import discount.PercentageDiscount;
import discount.FlatDiscount;
import discount.NoDiscount;

/**
 * Payment class - Handles payment processing for orders
 * Utilizes PaymentStrategy pattern for different payment methods
 */
public class Payment {
    private String paymentId;
    private double amount;
    private String method;
    
    /**
     * Constructor to create a Payment object
     * @param paymentId Unique identifier for the payment
     * @param amount Payment amount
     * @param method Payment method name
     */
    public Payment(String paymentId, double amount, String method) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.method = method;
    }
    
    /**
     * Get the payment ID
     * @return Payment ID
     */
    public String getPaymentId() {
        return paymentId;
    }
    
    /**
     * Get the payment amount
     * @return Payment amount
     */
    public double getAmount() {
        return amount;
    }
    
    /**
     * Get the payment method
     * @return Payment method name
     */
    public String getMethod() {
        return method;
    }
    
    /**
     * Display payment window and handle payment processing
     * @param order The order to process payment for
     * @param scanner Scanner for user input
     * @return true if payment was successful, false otherwise
     */
    public static boolean displayPaymentWindow(Order order, Scanner scanner) {
        if (order.getItemCount() == 0) {
            System.out.println("\nCart is empty.");
            return false;
        }
        
        System.out.println("\n\n=== Payment ===");
        
        // Display all dishes with prices
        Map<String, List<Dish>> groupedDishes = order.getGroupedDishes();
        System.out.println("\nOrder Items:");
        DisplayFormatter.displayGroupedDishes(groupedDishes);
        
        double originalTotal = order.calculateTotal();
        System.out.println("\nOriginal Total: $" + DisplayFormatter.formatPrice(originalTotal));
        
        // Display discount options and let user select
        System.out.println("\n--- Select Discount ---");
        System.out.println("1. No Discount - $" + DisplayFormatter.formatPrice(originalTotal));
        
        double priceWithPercent = calculateDiscountSilently(originalTotal, new PercentageDiscount(10));
        System.out.println("2. 10% Discount - $" + DisplayFormatter.formatPrice(priceWithPercent));
        
        double priceWithFlat = calculateDiscountSilently(originalTotal, new FlatDiscount(2.0));
        System.out.println("3. $2.00 Flat Discount - $" + DisplayFormatter.formatPrice(priceWithFlat));
        
        int discountChoice = InputHandler.readInt(scanner, "\nSelect discount option: ");
        
        DiscountStrategy selectedDiscount = null;
        double finalPrice = originalTotal;
        
        if (discountChoice == -1) {
            return false;
        }
        
        switch (discountChoice) {
            case 1:
                selectedDiscount = new NoDiscount();
                finalPrice = originalTotal;
                System.out.println("\nSelected: No Discount");
                break;
            case 2:
                selectedDiscount = new PercentageDiscount(10);
                finalPrice = calculateDiscountSilently(originalTotal, selectedDiscount);
                System.out.println("\nSelected: 10% Discount");
                System.out.println("Discount Amount: $" + DisplayFormatter.formatPrice(originalTotal - finalPrice));
                break;
            case 3:
                selectedDiscount = new FlatDiscount(2.0);
                finalPrice = calculateDiscountSilently(originalTotal, selectedDiscount);
                System.out.println("\nSelected: $2.00 Flat Discount");
                System.out.println("Discount Amount: $" + DisplayFormatter.formatPrice(originalTotal - finalPrice));
                break;
            default:
                System.out.println("Invalid discount selection. Using no discount.");
                selectedDiscount = new NoDiscount();
                finalPrice = originalTotal;
                break;
        }
        
        System.out.println("Final Price: $" + DisplayFormatter.formatPrice(finalPrice));
        
        // Payment method selection
        System.out.println("\nSelect Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("3. Cash on Delivery");
        
        int paymentChoice = InputHandler.readInt(scanner, "\nEnter your choice: ");
        String paymentMethod = "";
        PaymentStrategy paymentStrategy = null;
        
        if (paymentChoice == -1) {
            return false;
        }
        
        switch (paymentChoice) {
            case 1:
                paymentMethod = "Credit Card";
                String cardNumber = InputHandler.readCreditCardNumber(scanner, "Enter card number *: ");
                String cardHolderName = InputHandler.readMandatoryString(scanner, "Enter cardholder name *: ", 
                    "Cardholder name is required. Please enter the cardholder name.");
                paymentStrategy = new CreditCardPayment(cardNumber, cardHolderName);
                break;
            case 2:
                paymentMethod = "PayPal";
                String paypalEmail = InputHandler.readEmail(scanner, "Enter PayPal email *: ");
                paymentStrategy = new PayPalPayment(paypalEmail);
                break;
            case 3:
                paymentMethod = "Cash on Delivery";
                paymentStrategy = new CashOnDelivery();
                break;
            default:
                System.out.println("Invalid payment method selection.");
                return false;
        }
        
        // Collect user information
        System.out.println("\n--- Customer Information ---");
        
        // Get name (mandatory)
        String name = InputHandler.readMandatoryString(scanner, "Name *: ", 
            "Name is required. Please enter your name.");
        
        // Get address (mandatory)
        String address = InputHandler.readMandatoryString(scanner, "Address *: ", 
            "Address is required. Please enter your address.");
        
        // Get phone number (mandatory, numbers only)
        String phone = InputHandler.readMandatoryPhoneNumber(scanner, "Phone Number *: ", 
            "Phone number is required. Please enter your phone number.");
        
        // Get email (optional)
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        
        // Confirmation prompt
        System.out.print("\nPlace order? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (!confirm.equals("yes") && !confirm.equals("y")) {
            System.out.println("\nOrder cancelled.\n");
            return false;
        }
        
        // Process payment with discount
        System.out.println("\n--- Processing Payment ---");
        // Apply discount silently before processing payment
        boolean paymentSuccessful = paymentStrategy.processPayment(finalPrice);
        
        if (paymentSuccessful) {
            // Generate payment ID (simple implementation)
            String paymentId = "PAY" + System.currentTimeMillis();
            Payment payment = new Payment(paymentId, finalPrice, paymentMethod);
            
            System.out.println("\n✓ Payment processed successfully!");
            System.out.println("Payment ID: " + payment.getPaymentId());
            System.out.println("Payment Method: " + payment.getMethod());
            System.out.println("Original Amount: $" + DisplayFormatter.formatPrice(originalTotal));
            if (finalPrice < originalTotal) {
                System.out.println("Discount Applied: $" + DisplayFormatter.formatPrice(originalTotal - finalPrice));
            }
            System.out.println("Final Amount: $" + DisplayFormatter.formatPrice(payment.getAmount()));
            System.out.println("\nOrder will be delivered to:");
            System.out.println("  Name: " + name);
            System.out.println("  Address: " + address);
            System.out.println("  Phone: " + phone);
            if (!email.isEmpty()) {
                System.out.println("  Email: " + email);
            }
            
            // Create a new user with the provided information
            String userId = "U" + System.currentTimeMillis();
            User orderUser = new User(userId, name, email.isEmpty() ? phone + "@order.com" : email);
            
            // Create a copy of the order for tracking with the actual user
            String newOrderId = "ORD" + System.currentTimeMillis();
            Order placedOrder = new Order(newOrderId, orderUser);
            
            // Copy all dishes from the current order
            for (Dish dish : order.getDishes()) {
                placedOrder.addDish(dish, order.getRestaurantForDish(dish));
            }
            
            // Add order to OrderStatus (sets status to PLACED automatically)
            OrderStatus.addOrder(placedOrder);
            
            // Clear the shopping cart after successful order placement
            order.clearOrder();
            
            System.out.println("\n✓ Order placed successfully!");
            System.out.println("Order ID: " + placedOrder.getOrderId());
            System.out.println("Order Status: " + placedOrder.getStatus());
            System.out.println();
            return true;
        } else {
            System.out.println("\nPayment processing failed. Please try again.\n");
            return false;
        }
    }
    
    /**
     * Calculate discount price without printing messages
     * @param originalPrice Original price
     * @param discountStrategy Discount strategy to apply
     * @return Discounted price
     */
    private static double calculateDiscountSilently(double originalPrice, DiscountStrategy discountStrategy) {
        if (discountStrategy instanceof PercentageDiscount) {
            PercentageDiscount pd = (PercentageDiscount) discountStrategy;
            double percentage = pd.getPercentage();
            double discountAmount = originalPrice * percentage / 100;
            return originalPrice - discountAmount;
        } else if (discountStrategy instanceof FlatDiscount) {
            FlatDiscount fd = (FlatDiscount) discountStrategy;
            double discountAmount = fd.getDiscountAmount();
            double discountedPrice = originalPrice - discountAmount;
            return discountedPrice < 0 ? 0 : discountedPrice;
        } else {
            return originalPrice;
        }
    }
}
