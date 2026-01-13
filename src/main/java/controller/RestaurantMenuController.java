package controller;

import java.util.List;
import java.util.Scanner;
import restaurant.Restaurant;
import dish.Dish;
import dish.VegDish;
import dish.VeganDish;
import dish.CheeseDecorator;
import dish.ExtraSauceDecorator;
import order.Order;
import util.InputHandler;
import util.DisplayFormatter;

public class RestaurantMenuController {
    private List<Restaurant> restaurants;
    private Order currentOrder;
    private Scanner scanner;
    
    public RestaurantMenuController(List<Restaurant> restaurants, Order currentOrder, Scanner scanner) {
        this.restaurants = restaurants;
        this.currentOrder = currentOrder;
        this.scanner = scanner;
    }
    
    public void browseRestaurants() {
        boolean browsing = true;
        while (browsing) {
            System.out.println("\n\n=== Browse Restaurants ===");
            for (int i = 0; i < restaurants.size(); i++) {
                System.out.println((i + 1) + ". " + restaurants.get(i).getName());
            }
            System.out.println((restaurants.size() + 1) + ". Return to Main Menu");
            int choice = InputHandler.readInt(scanner, "\nEnter your choice: ");
            if (choice == -1) {
                continue;
            }
            if (choice >= 1 && choice <= restaurants.size()) {
                Restaurant selectedRestaurant = restaurants.get(choice - 1);
                displayRestaurantMenu(selectedRestaurant);
            } else if (choice == restaurants.size() + 1) {
                browsing = false;
                System.out.println();
            } else {
                System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }
    
    private void displayRestaurantMenu(Restaurant restaurant) {
        boolean viewingMenu = true;
        while (viewingMenu) {
            System.out.println("\n\n=== " + restaurant.getName() + " Menu ===");
            List<Dish> menu = restaurant.getMenu();
            if (menu.isEmpty()) {
                System.out.println("No dishes available at this restaurant.");
            } else {
                for (int i = 0; i < menu.size(); i++) {
                    Dish dish = menu.get(i);
                    String dishName = dish.getName();
                    if (dish instanceof VegDish || dish instanceof VeganDish) {
                        dishName += " (VEG)";
                    }
                    System.out.println((i + 1) + ". " + dishName + 
                                     " - $" + DisplayFormatter.formatPrice(dish.getPrice()));
                }
            }
            System.out.println((menu.size() + 1) + ". Return to Restaurants");
            System.out.println();
            System.out.println("Current Order Total: $" + DisplayFormatter.formatPrice(currentOrder.calculateTotal()));
            int choice = InputHandler.readInt(scanner, "\nEnter your choice: ");
            if (choice == -1) {
                continue;
            }
            if (choice >= 1 && choice <= menu.size()) {
                Dish selectedDish = menu.get(choice - 1);
                boolean supportsDecorators = restaurant.getName().equals("Pizza Palace") || 
                                            restaurant.getName().equals("Burger King");
                Dish dishToAdd = selectedDish;
                if (supportsDecorators) {
                    dishToAdd = showDecoratorOptions(selectedDish);
                }
                currentOrder.addDish(dishToAdd, restaurant);
                System.out.println("\n\n✓ Added \"" + dishToAdd.getName() + 
                                 "\" ($" + DisplayFormatter.formatPrice(dishToAdd.getPrice()) + 
                                 ") to your order.");
                System.out.println("Current order total: $" + DisplayFormatter.formatPrice(currentOrder.calculateTotal()));
                System.out.println("Items in cart: " + currentOrder.getItemCount() + "\n");
            } else if (choice == menu.size() + 1) {
                viewingMenu = false;
            } else {
                System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }
    
    private Dish showDecoratorOptions(Dish baseDish) {
        System.out.println("\n--- Add Extras ---");
        System.out.println("1. Extra Cheese (+$2.00)");
        System.out.println("2. Extra Sauce (+$1.50)");
        System.out.println("3. Extra Cheese + Extra Sauce (+$3.50)");
        System.out.println("4. None");
        int decoratorChoice = InputHandler.readInt(scanner, "\nEnter your choice: ");
        if (decoratorChoice == -1) {
            return baseDish;
        }
        switch (decoratorChoice) {
            case 1:
                return new CheeseDecorator(baseDish);
            case 2:
                return new ExtraSauceDecorator(baseDish);
            case 3:
                Dish withCheese = new CheeseDecorator(baseDish);
                return new ExtraSauceDecorator(withCheese);
            case 4:
            default:
                return baseDish;
        }
    }
}