package util;

import java.util.ArrayList;
import java.util.List;
import restaurant.Restaurant;
import dish.DishFactory;

/**
 * DataInitializer utility class - Initializes sample data for the application
 */
public class DataInitializer {
    
    /**
     * Initialize sample data with restaurants and dishes
     * @return List of restaurants with their menus
     */
    public static List<Restaurant> initializeSampleData() {
        List<Restaurant> restaurants = new ArrayList<>();
        
        // Create Pizza Palace
        Restaurant pizzaPalace = new Restaurant("R001", "Pizza Palace");
        pizzaPalace.addDish(DishFactory.createDish("VEG", "Margherita Pizza", 10.99, "Classic margherita with fresh tomatoes and mozzarella"));
        pizzaPalace.addDish(DishFactory.createDish("NON_VEG", "Pepperoni Pizza", 12.99, "Classic pepperoni pizza"));
        pizzaPalace.addDish(DishFactory.createDish("NON_VEG", "Hawaiian Pizza", 13.99, "Ham and pineapple pizza"));
        pizzaPalace.addDish(DishFactory.createDish("VEG", "Vegetarian Pizza", 11.99, "Loaded with fresh vegetables"));
        restaurants.add(pizzaPalace);
        
        // Create Burger King
        Restaurant burgerKing = new Restaurant("R002", "Burger King");
        burgerKing.addDish(DishFactory.createDish("NON_VEG", "Classic Burger", 8.99, "Juicy beef patty with lettuce and tomato"));
        burgerKing.addDish(DishFactory.createDish("NON_VEG", "Cheeseburger", 9.99, "Classic burger with melted cheese"));
        burgerKing.addDish(DishFactory.createDish("NON_VEG", "Bacon Burger", 10.99, "Burger topped with crispy bacon"));
        burgerKing.addDish(DishFactory.createDish("VEGAN", "Veggie Burger", 9.49, "Plant-based patty with fresh veggies"));
        restaurants.add(burgerKing);
        
        // Create Sushi Master
        Restaurant sushiMaster = new Restaurant("R003", "Sushi Master");
        sushiMaster.addDish(DishFactory.createDish("NON_VEG", "Salmon Sashimi", 15.99, "Fresh salmon sashimi slices"));
        sushiMaster.addDish(DishFactory.createDish("VEG", "California Roll", 8.99, "Avocado, cucumber, and crab stick"));
        sushiMaster.addDish(DishFactory.createDish("NON_VEG", "Dragon Roll", 12.99, "Eel and cucumber topped with avocado"));
        sushiMaster.addDish(DishFactory.createDish("NON_VEG", "Tuna Nigiri", 6.99, "Fresh tuna on seasoned rice"));
        restaurants.add(sushiMaster);
        
        System.out.println("Sample data initialized: " + restaurants.size() + " restaurants loaded.\n");
        return restaurants;
    }
}
