package discount;

/**
 * DISABLED - Demo/Example class only, not used in the application
 * 
 * DiscountStrategy - Strategy Pattern interface for applying discounts
 * Allows different discount calculation methods to be used interchangeably
 * 
 * NOTE: This interface and its implementations (PercentageDiscount, FlatDiscount, NoDiscount)
 * are kept for reference but are not used in the final application.
 */
public interface DiscountStrategy {
    /**
     * Apply discount to the original price
     * @param originalPrice The original price before discount
     * @return The price after discount is applied
     */
    double applyDiscount(double originalPrice);
}
