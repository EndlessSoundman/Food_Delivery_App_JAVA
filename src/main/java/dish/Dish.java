package dish;

/**
 * Dish interface - Defines the contract for all dish types
 * Part of Factory Pattern implementation
 */
public interface Dish {
    /**
     * Get the name of the dish
     * @return Dish name
     */
    String getName();
    
    /**
     * Get the price of the dish
     * @return Dish price
     */
    double getPrice();
    
    /**
     * Get the type of the dish (Vegetarian, Non-Vegetarian, Vegan)
     * @return Dish type
     */
    String getType();
    
    /**
     * Get the description of the dish
     * @return Dish description
     */
    String getDescription();
}
