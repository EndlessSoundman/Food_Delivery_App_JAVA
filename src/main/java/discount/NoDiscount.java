package discount;

public class NoDiscount implements DiscountStrategy {
    public NoDiscount() {
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
