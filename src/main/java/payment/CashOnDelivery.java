package payment;

public class CashOnDelivery implements PaymentStrategy {
    public CashOnDelivery() {
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