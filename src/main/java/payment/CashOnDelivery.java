package payment;

/**
 * CashOnDelivery - Cash on delivery payment strategy implementation
 * Part of Strategy Pattern for payment processing
 */
public class CashOnDelivery implements PaymentStrategy {
    
    /**
     * Constructor for Cash on Delivery payment strategy
     */
    public CashOnDelivery() {
        // No fields needed for cash on delivery
    }
    
    @Override
    public boolean processPayment(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment amount: $" + amount);
            return false;
        }
        System.out.println("Cash on Delivery: $" + String.format("%.2f", amount) + 
                         " will be collected upon delivery");
        return true;
    }
}
