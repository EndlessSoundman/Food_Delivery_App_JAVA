package payment;

/**
 * PaymentStrategy - Strategy Pattern interface for payment processing
 * Allows different payment methods to be used interchangeably
 */
public interface PaymentStrategy {
    /**
     * Process a payment for the given amount
     * @param amount The amount to be paid
     * @return true if payment was processed successfully, false otherwise
     */
    boolean processPayment(double amount);
}
