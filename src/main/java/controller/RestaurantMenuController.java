package controller;

import java.util.List;
import java.util.Scanner;
import restaurant.Restaurant;
import dish.Dish;
import order.Order;
import util.InputHandler;
import util.DisplayFormatter;

/**
 * RestaurantMenuController - Handles restaurant browsing and menu display logic
 */
public class RestaurantMenuController {
    private List<Restaurant> restaurants;
    private Order currentOrder;
    private Scanner scanner;
    
    /**
     * Constructor for RestaurantMenuController
     * @param restaurants List of restaurants
     * @param currentOrder Current order object
     * @param scanner Scanner for user input
     */
    public RestaurantMenuController(List<Restaurant> restaurants, Order currentOrder, Scanner scanner) {
        this.restaurants = restaurants;
        this.currentOrder = currentOrder;
        this.scanner = scanner;
    }
    
    /**
     * Browse available restaurants - lists all restaurants with numbers
     */
    public void browseRestaurants() {
        boolean browsing = true;
        while (browsing) {
            System.out.println("\n\n=== Browse Restaurants ===");
            
            // Display all restaurants with numbers
            for (int i = 0; i < restaurants.size(); i++) {
                System.out.println((i + 1) + ". " + restaurants.get(i).getName());
            }
            System.out.println((restaurants.size() + 1) + ". Return to Main Menu");
            
            int choice = InputHandler.readInt(scanner, "\nEnter your choice: ");
            
            if (choice == -1) {
                // Error occurred, continue loop
                continue;
            }
            
            if (choice >= 1 && choice <= restaurants.size()) {
                // User selected a restaurant - display its menu
                Restaurant selectedRestaurant = restaurants.get(choice - 1);
                displayRestaurantMenu(selectedRestaurant);
            } else if (choice == restaurants.size() + 1) {
                // Return to main menu
                browsing = false;
                System.out.println();
            } else {
                System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }
    
    /**
     * Display restaurant menu and allow user to add dishes to order
     * @param restaurant The restaurant whose menu to display
     */
    private void displayRestaurantMenu(Restaurant restaurant) {
        boolean viewingMenu = true;
        while (viewingMenu) {
            System.out.println("\n\n=== " + restaurant.getName() + " Menu ===");
            List<Dish> menu = restaurant.getMenu();
            
            if (menu.isEmpty()) {
                System.out.println("No dishes available at this restaurant.");
            } else {
                // Display all dishes with numbers, names, and prices
                for (int i = 0; i < menu.size(); i++) {
                    Dish dish = menu.get(i);
                    System.out.println((i + 1) + ". " + dish.getName() + 
                                     " - $" + DisplayFormatter.formatPrice(dish.getPrice()));
                }
            }
            System.out.println((menu.size() + 1) + ". Return to Restaurants");
            System.out.println();
            System.out.println("Current Order Total: $" + DisplayFormatter.formatPrice(currentOrder.calculateTotal()));
            
            int choice = InputHandler.readInt(scanner, "\nEnter your choice: ");
            
            if (choice == -1) {
                // Error occurred, continue loop
                continue;
            }
            
            if (choice >= 1 && choice <= menu.size()) {
                // User selected a dish - add it to order
                Dish selectedDish = menu.get(choice - 1);
                currentOrder.addDish(selectedDish, restaurant);
                System.out.println("\n\n✓ Added \"" + selectedDish.getName() + 
                                 "\" ($" + DisplayFormatter.formatPrice(selectedDish.getPrice()) + 
                                 ") to your order.");
                System.out.println("Current order total: $" + DisplayFormatter.formatPrice(currentOrder.calculateTotal()));
                System.out.println("Items in cart: " + currentOrder.getItemCount() + "\n");
            } else if (choice == menu.size() + 1) {
                // Return to restaurant list
                viewingMenu = false;
            } else {
                System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }
}
