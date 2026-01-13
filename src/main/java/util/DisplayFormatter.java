package util;

import java.util.List;
import java.util.Map;
import dish.Dish;

public class DisplayFormatter {
    public static String formatPrice(double price) {
        return String.format("%.2f", price);
    }
    
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
    
    public static void displayGroupedDishes(Map<String, List<Dish>> groupedDishes) {
        displayGroupedDishes(groupedDishes, false, "  ");
    }
}