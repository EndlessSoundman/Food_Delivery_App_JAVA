package dish;

/**
 * NonVegDish - Represents a non-vegetarian dish
 * Implements Dish interface as part of Factory Pattern
 */
public class NonVegDish implements Dish {
    private String name;
    private double price;
    private String description;
    
    /**
     * Constructor to create a non-vegetarian dish
     * @param name Name of the dish
     * @param price Price of the dish
     * @param description Description of the dish
     */
    public NonVegDish(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public double getPrice() {
        return price;
    }
    
    @Override
    public String getType() {
        return "Non-Vegetarian";
    }
    
    @Override
    public String getDescription() {
        return description;
    }
}
