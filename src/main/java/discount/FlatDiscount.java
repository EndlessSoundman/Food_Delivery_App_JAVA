package discount;

public class FlatDiscount implements DiscountStrategy {
    private double discountAmount;
    
    public FlatDiscount(double discountAmount) {
        if (discountAmount < 0) {
            throw new IllegalArgumentException("Discount amount cannot be negative");
        }
        this.discountAmount = discountAmount;
    }
    
    @Override
    public double applyDiscount(double originalPrice) {
        if (originalPrice < 0) {
            return originalPrice;
        }
        double discountedPrice = originalPrice - discountAmount;
        if (discountedPrice < 0) {
            discountedPrice = 0;
        }
        System.out.println("Applied $" + String.format("%.2f", discountAmount) + " flat discount");
        return discountedPrice;
    }
    
    public double getDiscountAmount() {
        return discountAmount;
    }
}
