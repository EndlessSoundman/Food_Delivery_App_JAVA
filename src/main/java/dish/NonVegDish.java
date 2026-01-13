package dish;

public class NonVegDish implements Dish {
    private String name;
    private double price;
    private String description;
    
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