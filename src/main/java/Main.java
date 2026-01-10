import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import config.AppConfig;
import restaurant.Restaurant;
import dish.Dish;
import dish.DishFactory;
import dish.CheeseDecorator;
import dish.ExtraSauceDecorator;

/**
 * Main class - Entry point for the Food Delivery Application
 * Provides a console-based menu system for users
 */
public class Main {
    private static List<Restaurant> restaurants;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        // Display app information from Singleton
        AppConfig config = AppConfig.getInstance();
        System.out.println("========================================");
        System.out.println("Welcome to " + config.getAppName());
        System.out.println("Version: " + config.getVersion());
        System.out.println("========================================\n");
        
        // Initialize sample data
        initializeSampleData();
        
        // Initialize scanner
        scanner = new Scanner(System.in);
        
        // Main menu loop
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        browseRestaurants();
                        break;
                    case 2:
                        placeOrder();
                        break;
                    case 3:
                        checkOrderStatus();
                        break;
                    case 4:
                        System.out.println("Thank you for using " + config.getAppName() + "!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.\n");
                scanner.nextLine(); // Clear invalid input
            }
        }
        
        scanner.close();
    }
    
    /**
     * Display the main menu options
     */
    private static void displayMenu() {
        System.out.println("=== Main Menu ===");
        System.out.println("1. Browse Restaurants");
        System.out.println("2. Place Order");
        System.out.println("3. Check Order Status");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
    
    /**
     * Browse available restaurants (placeholder)
     */
    private static void browseRestaurants() {
        System.out.println("\n=== Browse Restaurants ===");
        System.out.println("Coming soon...\n");
    }
    
    /**
     * Place an order (placeholder)
     */
    private static void placeOrder() {
        System.out.println("\n=== Place Order ===");
        System.out.println("Coming soon...\n");
    }
    
    /**
     * Check order status (placeholder)
     */
    private static void checkOrderStatus() {
        System.out.println("\n=== Check Order Status ===");
        System.out.println("Coming soon...\n");
    }
    
    /**
     * Initialize sample data with restaurants and dishes
     * Uses DishFactory to create dishes with different types
     */
    private static void initializeSampleData() {
        restaurants = new ArrayList<>();
        
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
        
        // Demonstrate Decorator Pattern
        demonstrateDecorators();
    }
    
    /**
     * Demonstrate the Decorator Pattern by adding toppings to a dish
     */
    private static void demonstrateDecorators() {
        System.out.println("=== Decorator Pattern Demo ===");
        
        // Create a base pizza dish
        Dish pizza = DishFactory.createDish("NON_VEG", "Pepperoni Pizza", 12.99, "Classic pepperoni pizza");
        System.out.println("Original: " + pizza.getName() + " - $" + String.format("%.2f", pizza.getPrice()));
        
        // Add cheese decorator
        Dish pizzaWithCheese = new CheeseDecorator(pizza);
        System.out.println("With Cheese: " + pizzaWithCheese.getName() + " - $" + String.format("%.2f", pizzaWithCheese.getPrice()));
        
        // Add sauce decorator to the pizza with cheese
        Dish pizzaWithCheeseAndSauce = new ExtraSauceDecorator(pizzaWithCheese);
        System.out.println("With Cheese + Sauce: " + pizzaWithCheeseAndSauce.getName() + " - $" + String.format("%.2f", pizzaWithCheeseAndSauce.getPrice()));
        
        System.out.println();
    }
}
