package dish;

public class CheeseDecorator extends DishDecorator {
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
