package restaurant;

import java.util.ArrayList;
import java.util.List;
import dish.Dish;

public class Restaurant {
    private String name;
    private List<Dish> dishes;
    
    public Restaurant(String name) {
        this.name = name;
        this.dishes = new ArrayList<>();
    }
    
    public void addDish(Dish dish) {
        dishes.add(dish);
    }
    
    public List<Dish> getMenu() {
        return new ArrayList<>(dishes);
    }
    
    public String getName() {
        return name;
    }
}
