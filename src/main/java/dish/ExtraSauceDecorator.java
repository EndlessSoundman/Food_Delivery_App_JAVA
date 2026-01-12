package dish;

/**
 * DISABLED - Demo/Example class only, not used in the application
 * 
 * ExtraSauceDecorator - Adds extra sauce to a dish
 * Part of Decorator Pattern implementation
 * 
 * NOTE: This class is kept for reference but is not used in the final application.
 */
public class ExtraSauceDecorator extends DishDecorator {
    
    /**
     * Constructor to add extra sauce decoration to a dish
     * @param dish The dish to add sauce to
     */
    public ExtraSauceDecorator(Dish dish) {
        super(dish);
    }
    
    @Override
    public String getName() {
        return decoratedDish.getName() + " + Extra Sauce";
    }
    
    @Override
    public double getPrice() {
        return decoratedDish.getPrice() + 1.5;
    }
    
    @Override
    public String getDescription() {
        return decoratedDish.getDescription() + ", with extra sauce";
    }
    
    @Override
    public String getType() {
        return decoratedDish.getType();
    }
}
