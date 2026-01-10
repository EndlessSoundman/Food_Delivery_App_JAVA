package discount;

/**
 * FlatDiscount - Flat amount discount strategy implementation
 * Part of Strategy Pattern for discount calculation
 */
public class FlatDiscount implements DiscountStrategy {
    private double discountAmount;
    
    /**
     * Constructor to create a flat discount strategy
     * @param discountAmount The fixed discount amount
     */
    public FlatDiscount(double discountAmount) {
        if (discountAmount < 0) {
            throw new IllegalArgumentException("Discount amount cannot be negative");
        }
        this.discountAmount = discountAmount;
    }
    
    @Override
    public double applyDiscount(double originalPrice) {
        if (originalPrice < 0) {
            System.out.println("Invalid price: $" + originalPrice);
            return originalPrice;
        }
        double discountedPrice = originalPrice - discountAmount;
        // Ensure price never goes below 0
        if (discountedPrice < 0) {
            discountedPrice = 0;
        }
        System.out.println("Applied $" + String.format("%.2f", discountAmount) + " flat discount");
        return discountedPrice;
    }
}
