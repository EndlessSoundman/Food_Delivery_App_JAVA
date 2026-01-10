package dish;

/**
 * VeganDish - Represents a vegan dish
 * Implements Dish interface as part of Factory Pattern
 */
public class VeganDish implements Dish {
    private String name;
    private double price;
    private String description;
    
    /**
     * Constructor to create a vegan dish
     * @param name Name of the dish
     * @param price Price of the dish
     * @param description Description of the dish
     */
    public VeganDish(String name, double price, String description) {
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
        return "Vegan";
    }
    
    @Override
    public String getDescription() {
        return description;
    }
}
