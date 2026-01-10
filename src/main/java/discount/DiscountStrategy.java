package discount;

/**
 * DiscountStrategy - Strategy Pattern interface for applying discounts
 * Allows different discount calculation methods to be used interchangeably
 */
public interface DiscountStrategy {
    /**
     * Apply discount to the original price
     * @param originalPrice The original price before discount
     * @return The price after discount is applied
     */
    double applyDiscount(double originalPrice);
}
