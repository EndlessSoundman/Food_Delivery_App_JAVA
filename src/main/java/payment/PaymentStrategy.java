package payment;

public interface PaymentStrategy {
    boolean processPayment(double amount);
}
