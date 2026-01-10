package discount;

/**
 * PercentageDiscount - Percentage-based discount strategy implementation
 * Part of Strategy Pattern for discount calculation
 */
public class PercentageDiscount implements DiscountStrategy {
    private double percentage;
    
    /**
     * Constructor to create a percentage discount strategy
     * @param percentage The discount percentage (e.g., 10 for 10%)
     */
    public PercentageDiscount(double percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }
        this.percentage = percentage;
    }
    
    @Override
    public double applyDiscount(double originalPrice) {
        if (originalPrice < 0) {
            System.out.println("Invalid price: $" + originalPrice);
            return originalPrice;
        }
        double discountAmount = originalPrice * percentage / 100;
        double discountedPrice = originalPrice - discountAmount;
        System.out.println("Applied " + percentage + "% discount");
        return discountedPrice;
    }
}
