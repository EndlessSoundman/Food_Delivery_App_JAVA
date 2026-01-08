import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import config.AppConfig;
import restaurant.Restaurant;

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
     */
    private static void initializeSampleData() {
        restaurants = new ArrayList<>();
        
        // Create Pizza Palace
        Restaurant pizzaPalace = new Restaurant("R001", "Pizza Palace");
        pizzaPalace.addDish("Margherita Pizza");
        pizzaPalace.addDish("Pepperoni Pizza");
        pizzaPalace.addDish("Hawaiian Pizza");
        pizzaPalace.addDish("Vegetarian Pizza");
        restaurants.add(pizzaPalace);
        
        // Create Burger King
        Restaurant burgerKing = new Restaurant("R002", "Burger King");
        burgerKing.addDish("Classic Burger");
        burgerKing.addDish("Cheeseburger");
        burgerKing.addDish("Bacon Burger");
        burgerKing.addDish("Veggie Burger");
        restaurants.add(burgerKing);
        
        // Create Sushi Master
        Restaurant sushiMaster = new Restaurant("R003", "Sushi Master");
        sushiMaster.addDish("Salmon Sashimi");
        sushiMaster.addDish("California Roll");
        sushiMaster.addDish("Dragon Roll");
        sushiMaster.addDish("Tuna Nigiri");
        restaurants.add(sushiMaster);
        
        System.out.println("Sample data initialized: " + restaurants.size() + " restaurants loaded.\n");
    }
}
