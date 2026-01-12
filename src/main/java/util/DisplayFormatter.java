package util;

import java.util.List;
import java.util.Map;
import dish.Dish;

/**
 * DisplayFormatter utility class - Provides common display formatting methods
 * Reduces code duplication for formatting prices and displaying grouped dishes
 */
public class DisplayFormatter {
    
    /**
     * Format a price to 2 decimal places
     * @param price The price to format
     * @return Formatted price string (e.g., "$10.99")
     */
    public static String formatPrice(double price) {
        return String.format("%.2f", price);
    }
    
    /**
     * Display grouped dishes with prices
     * @param groupedDishes Map of dish names to lists of dishes
     * @param showNumbers Whether to show numbers before dish names
     * @param indent Indent string for dish lines (e.g., "  " for 2 spaces)
     */
    public static void displayGroupedDishes(Map<String, List<Dish>> groupedDishes, boolean showNumbers, String indent) {
        int index = 1;
        for (Map.Entry<String, List<Dish>> entry : groupedDishes.entrySet()) {
            String dishName = entry.getKey();
            List<Dish> dishList = entry.getValue();
            int quantity = dishList.size();
            double unitPrice = dishList.get(0).getPrice();
            double totalPrice = unitPrice * quantity;
            
            String prefix = showNumbers ? (index + ". ") : indent;
            if (quantity > 1) {
                System.out.println(prefix + dishName + " x" + quantity + " - $" + formatPrice(totalPrice));
            } else {
                System.out.println(prefix + dishName + " - $" + formatPrice(totalPrice));
            }
            index++;
        }
    }
    
    /**
     * Display grouped dishes without numbers (for cart display)
     * @param groupedDishes Map of dish names to lists of dishes
     */
    public static void displayGroupedDishes(Map<String, List<Dish>> groupedDishes) {
        displayGroupedDishes(groupedDishes, false, "  ");
    }
}
