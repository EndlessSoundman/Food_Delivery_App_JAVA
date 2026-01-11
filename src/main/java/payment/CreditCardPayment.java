package payment;

/**
 * CreditCardPayment - Credit card payment strategy implementation
 * Part of Strategy Pattern for payment processing
 */
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;
    
    /**
     * Constructor to create a credit card payment strategy
     * @param cardNumber The credit card number
     * @param cardHolderName The name of the card holder
     */
    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }
    
    @Override
    public boolean processPayment(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment amount: $" + amount);
            return false;
        }
        System.out.println("Processing credit card payment of $" + String.format("%.2f", amount) + 
                         " for " + cardHolderName + " (Card: " + maskCardNumber(cardNumber) + ")");
        return true;
    }
    
    /**
     * Mask the card number for security (show only last 4 digits)
     * @param cardNumber The full card number
     * @return Masked card number
     */
    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "****";
        }
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }
}
