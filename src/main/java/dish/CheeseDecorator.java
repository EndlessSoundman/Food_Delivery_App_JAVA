package dish;

/**
 * DISABLED - Demo/Example class only, not used in the application
 * 
 * CheeseDecorator - Adds extra cheese to a dish
 * Part of Decorator Pattern implementation
 * 
 * NOTE: This class is kept for reference but is not used in the final application.
 */
public class CheeseDecorator extends DishDecorator {
    
    /**
     * Constructor to add cheese decoration to a dish
     * @param dish The dish to add cheese to
     */
    public CheeseDecorator(Dish dish) {
        super(dish);
    }
    
    @Override
    public String getName() {
        return decoratedDish.getName() + " + Extra Cheese";
    }
    
    @Override
    public double getPrice() {
        return decoratedDish.getPrice() + 2.0;
    }
    
    @Override
    public String getDescription() {
        return decoratedDish.getDescription() + ", with extra cheese";
    }
    
    @Override
    public String getType() {
        return decoratedDish.getType();
    }
}
