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

public class Payment {
    private String paymentId;
    private double amount;
    private String method;
    
    public Payment(String paymentId, double amount, String method) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.method = method;
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getMethod() {
        return method;
    }
    
    public static boolean displayPaymentWindow(Order order, Scanner scanner) {
        if (order.getItemCount() == 0) {
            System.out.println("\nCart is empty.");
            return false;
        }
        
        System.out.println("\n\n=== Payment ===");
        Map<String, List<Dish>> groupedDishes = order.getGroupedDishes();
        System.out.println("\nOrder Items:");
        DisplayFormatter.displayGroupedDishes(groupedDishes);
        double originalTotal = order.calculateTotal();
        System.out.println("\nOriginal Total: $" + DisplayFormatter.formatPrice(originalTotal));
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
                finalPrice = originalTotal;
                break;
        }
        
        System.out.println("Final Price: $" + DisplayFormatter.formatPrice(finalPrice));
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
        System.out.println("\n--- Customer Information ---");
        String name = InputHandler.readMandatoryString(scanner, "Name *: ", 
            "Name is required. Please enter your name.");
        String address = InputHandler.readMandatoryString(scanner, "Address *: ", 
            "Address is required. Please enter your address.");
        String phone = InputHandler.readMandatoryPhoneNumber(scanner, "Phone Number *: ", 
            "Phone number is required. Please enter your phone number.");
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("\nPlace order? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (!confirm.equals("yes") && !confirm.equals("y")) {
            System.out.println("\nOrder cancelled.\n");
            return false;
        }
        System.out.println("\n--- Processing Payment ---");
        boolean paymentSuccessful = paymentStrategy.processPayment(finalPrice);
        if (paymentSuccessful) {
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
            User orderUser = new User(name);
            String newOrderId = "ORD" + System.currentTimeMillis();
            Order placedOrder = new Order(newOrderId, orderUser);
            for (Dish dish : order.getDishes()) {
                placedOrder.addDish(dish, order.getRestaurantForDish(dish));
            }
            OrderStatus.addOrder(placedOrder);
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
