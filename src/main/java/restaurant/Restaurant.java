package restaurant;

import java.util.ArrayList;
import java.util.List;
import dish.Dish;

/**
 * Restaurant class - Represents a restaurant in the food delivery system
 */
public class Restaurant {
    private String restaurantId;
    private String name;
    private List<Dish> dishes;
    
    /**
     * Constructor to create a new Restaurant
     * @param restaurantId Unique identifier for the restaurant
     * @param name Restaurant name
     */
    public Restaurant(String restaurantId, String name) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.dishes = new ArrayList<>();
    }
    
    /**
     * Add a dish to the restaurant's menu
     * @param dish Dish object to add
     */
    public void addDish(Dish dish) {
        dishes.add(dish);
    }
    
    /**
     * Get the restaurant's menu
     * @return List of dish objects
     */
    public List<Dish> getMenu() {
        return new ArrayList<>(dishes); // Return a copy to prevent external modification
    }
    /**
     * Get the restaurant name
     * @return The restaurant name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the restaurant ID
     * @return The restaurant ID
     */
    public String getRestaurantId() {
        return restaurantId;
    }
}
