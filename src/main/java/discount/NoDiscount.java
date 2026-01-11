package discount;

/**
 * DISABLED - Demo/Example class only, not used in the application
 * 
 * NoDiscount - No discount strategy implementation
 * Part of Strategy Pattern for discount calculation
 * Returns the original price unchanged
 * 
 * NOTE: This class is kept for reference but is not used in the final application.
 */
public class NoDiscount implements DiscountStrategy {
    
    /**
     * Constructor for No Discount strategy
     */
    public NoDiscount() {
        // No fields needed
    }
    
    @Override
    public double applyDiscount(double originalPrice) {
        if (originalPrice < 0) {
            System.out.println("Invalid price: $" + originalPrice);
            return originalPrice;
        }
        System.out.println("No discount applied");
        return originalPrice;
    }
}
