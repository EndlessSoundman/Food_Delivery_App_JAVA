package dish;

public class ExtraSauceDecorator extends DishDecorator {
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