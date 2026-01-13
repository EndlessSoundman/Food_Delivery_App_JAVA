package payment;

public class PayPalPayment implements PaymentStrategy {
    private String email;
    
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
