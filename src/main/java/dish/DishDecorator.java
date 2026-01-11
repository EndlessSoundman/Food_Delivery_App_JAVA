package dish;

/**
 * DISABLED - Demo/Example class only, not used in the application
 * 
 * DishDecorator - Abstract decorator class for Decorator Pattern
 * Allows adding additional features (toppings) to dishes dynamically
 * 
 * NOTE: This class and its implementations (CheeseDecorator, ExtraSauceDecorator)
 * are kept for reference but are not used in the final application.
 */
public abstract class DishDecorator implements Dish {
    protected Dish decoratedDish;
    
    /**
     * Constructor to wrap a dish with decorator
     * @param dish The dish to be decorated
     */
    public DishDecorator(Dish dish) {
        this.decoratedDish = dish;
    }
    
    @Override
    public String getName() {
        return decoratedDish.getName();
    }
    
    @Override
    public double getPrice() {
        return decoratedDish.getPrice();
    }
    
    @Override
    public String getType() {
        return decoratedDish.getType();
    }
    
    @Override
    public String getDescription() {
        return decoratedDish.getDescription();
    }
}
