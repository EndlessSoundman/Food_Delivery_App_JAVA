package payment;

/**
 * PayPalPayment - PayPal payment strategy implementation
 * Part of Strategy Pattern for payment processing
 */
public class PayPalPayment implements PaymentStrategy {
    private String email;
    
    /**
     * Constructor to create a PayPal payment strategy
     * @param email The PayPal email address
     */
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public boolean processPayment(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment amount: $" + amount);
            return false;
        }
        System.out.println("Processing PayPal payment of $" + String.format("%.2f", amount) + 
                         " for " + email);
        return true;
    }
}
