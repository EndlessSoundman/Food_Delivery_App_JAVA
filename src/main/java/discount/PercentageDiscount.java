package discount;

/**
 * DISABLED - Demo/Example class only, not used in the application
 * 
 * PercentageDiscount - Percentage-based discount strategy implementation
 * Part of Strategy Pattern for discount calculation
 * 
 * NOTE: This class is kept for reference but is not used in the final application.
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
    
    /**
     * Get the discount percentage
     * @return The discount percentage
     */
    public double getPercentage() {
        return percentage;
    }
}
