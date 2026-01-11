package order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import user.User;
import dish.Dish;
import restaurant.Restaurant;

/**
 * Order class - Represents an order in the food delivery system
 * Tracks dishes, their associated restaurants, and order status
 */
public class Order {
    private String orderId;
    private User user;
    private List<Dish> dishes;
    private Map<Dish, Restaurant> dishRestaurantMap; // Track which restaurant each dish comes from
    private String status;
    
    /**
     * Constructor to create a new Order
     * @param orderId Unique identifier for the order
     * @param user User placing the order
     */
    public Order(String orderId, User user) {
        this.orderId = orderId;
        this.user = user;
        this.dishes = new ArrayList<>();
        this.dishRestaurantMap = new HashMap<>();
        this.status = "PENDING"; // Default status
    }
    
    /**
     * Add a dish to the order
     * @param dish Dish to add
     * @param restaurant Restaurant the dish is from
     */
    public void addDish(Dish dish, Restaurant restaurant) {
        dishes.add(dish);
        dishRestaurantMap.put(dish, restaurant);
    }
    
    /**
     * Add a dish to the order (overloaded method for UML compatibility)
     * Note: This method requires restaurant information, so use addDish(Dish, Restaurant) instead
     */
    public void addDish() {
        // This method signature matches the UML but requires restaurant info
        // In practice, use addDish(Dish dish, Restaurant restaurant)
        System.out.println("Please use addDish(Dish dish, Restaurant restaurant) to add dishes with restaurant information.");
    }
    
    /**
     * Calculate the total price of all dishes in the order
     * @return Total price of the order
     */
    public double calculateTotal() {
        double total = 0.0;
        for (Dish dish : dishes) {
            total += dish.getPrice();
        }
        return total;
    }
    
    /**
     * Get the order ID
     * @return Order ID
     */
    public String getOrderId() {
        return orderId;
    }
    
    /**
     * Get the user who placed the order
     * @return User object
     */
    public User getUser() {
        return user;
    }
    
    /**
     * Get the list of dishes in the order
     * @return List of dishes
     */
    public List<Dish> getDishes() {
        return new ArrayList<>(dishes); // Return a copy to prevent external modification
    }
    
    /**
     * Get the order status
     * @return Order status
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * Set the order status
     * @param status New status
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * Get the number of items in the shopping basket
     * @return Number of dishes in the order
     */
    public int getItemCount() {
        return dishes.size();
    }
    
    /**
     * Get the restaurant for a specific dish
     * @param dish Dish to find restaurant for
     * @return Restaurant object, or null if not found
     */
    public Restaurant getRestaurantForDish(Dish dish) {
        return dishRestaurantMap.get(dish);
    }
    
    /**
     * Display the order summary (shows total price before opening basket)
     * This represents what the user sees before opening the shopping basket
     * @return String representation of order summary
     */
    public String getOrderSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("=== Shopping Basket ===");
        summary.append("\nItems in cart: ").append(getItemCount());
        summary.append("\nTotal: $").append(String.format("%.2f", calculateTotal()));
        return summary.toString();
    }
    
    /**
     * Display full basket details (names and restaurants)
     * This represents what the user sees when opening the shopping basket
     * @return String representation of full basket details
     */
    public String displayBasket() {
        StringBuilder basket = new StringBuilder();
        basket.append("\n=== Shopping Basket Details ===");
        basket.append("\nOrder ID: ").append(orderId);
        basket.append("\nCustomer: ").append(user.getName());
        basket.append("\n");
        
        if (dishes.isEmpty()) {
            basket.append("Your basket is empty.\n");
        } else {
            basket.append("\nItems:\n");
            for (int i = 0; i < dishes.size(); i++) {
                Dish dish = dishes.get(i);
                Restaurant restaurant = dishRestaurantMap.get(dish);
                basket.append((i + 1)).append(". ")
                      .append(dish.getName())
                      .append(" - $").append(String.format("%.2f", dish.getPrice()))
                      .append("\n   Restaurant: ").append(restaurant != null ? restaurant.getName() : "Unknown")
                      .append("\n");
            }
            basket.append("\nTotal: $").append(String.format("%.2f", calculateTotal()));
            basket.append("\n");
        }
        
        return basket.toString();
    }
    
    /**
     * Save the current order and prepare to return to dishes menu
     * This represents the "return to menu" button action
     * The order remains saved and can be accessed later
     * @return true if order is saved successfully
     */
    public boolean returnToMenu() {
        // Order is automatically saved as it's stored in memory
        // In a real application, this might save to database or session
        setStatus("DRAFT"); // Mark as draft so user can continue editing
        return true;
    }
    
    /**
     * Prepare order for payment processing
     * This represents the "proceed to payment" button action
     * @return true if order is ready for payment
     */
    public boolean proceedToPayment() {
        if (dishes.isEmpty()) {
            System.out.println("Cannot proceed to payment: Your basket is empty.");
            return false;
        }
        setStatus("READY_FOR_PAYMENT");
        return true;
    }
    
    /**
     * Check if order is ready for payment
     * @return true if order has items and is ready
     */
    public boolean isReadyForPayment() {
        return !dishes.isEmpty() && status.equals("READY_FOR_PAYMENT");
    }
    
    /**
     * Remove a dish from the order
     * @param dish Dish to remove
     * @return true if dish was removed successfully
     */
    public boolean removeDish(Dish dish) {
        boolean removed = dishes.remove(dish);
        if (removed) {
            dishRestaurantMap.remove(dish);
        }
        return removed;
    }
    
    /**
     * Get grouped dishes by name - returns a map where key is dish name and value is list of dishes with that name
     * @return Map of dish name to list of dishes
     */
    public Map<String, List<Dish>> getGroupedDishes() {
        Map<String, List<Dish>> grouped = new HashMap<>();
        for (Dish dish : dishes) {
            String name = dish.getName();
            grouped.putIfAbsent(name, new ArrayList<>());
            grouped.get(name).add(dish);
        }
        return grouped;
    }
    
    /**
     * Get the quantity of a specific dish by name
     * @param dishName Name of the dish
     * @return Quantity of dishes with that name
     */
    public int getDishQuantity(String dishName) {
        int count = 0;
        for (Dish dish : dishes) {
            if (dish.getName().equals(dishName)) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Remove a specific quantity of dishes by name
     * @param dishName Name of the dish to remove
     * @param quantity Number of dishes to remove (if exceeds available amount, removes all available)
     * @return Number of dishes actually removed
     */
    public int removeDishQuantity(String dishName, int quantity) {
        int removedCount = 0;
        List<Dish> toRemove = new ArrayList<>();
        
        // Collect dishes to remove
        for (Dish dish : dishes) {
            if (dish.getName().equals(dishName) && removedCount < quantity) {
                toRemove.add(dish);
                removedCount++;
            }
        }
        
        // Remove collected dishes
        for (Dish dish : toRemove) {
            dishes.remove(dish);
            dishRestaurantMap.remove(dish);
        }
        
        return removedCount;
    }
    
    /**
     * Clear all dishes from the order
     */
    public void clearOrder() {
        dishes.clear();
        dishRestaurantMap.clear();
        setStatus("PENDING");
    }
    
    /**
     * Get a string representation of the order
     * @return String representation
     */
    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", user=" + user.getName() +
                ", itemCount=" + getItemCount() +
                ", total=$" + String.format("%.2f", calculateTotal()) +
                ", status='" + status + '\'' +
                '}';
    }
}