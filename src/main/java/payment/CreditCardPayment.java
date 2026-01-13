package payment;

public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;
    
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
    
    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "****";
        }
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }
}
