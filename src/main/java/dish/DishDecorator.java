package dish;

public abstract class DishDecorator implements Dish {
    protected Dish decoratedDish;
    
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