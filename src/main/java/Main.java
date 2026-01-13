import java.util.List;
import java.util.Scanner;
import config.AppConfig;
import restaurant.Restaurant;
import controller.RestaurantMenuController;
import controller.ShoppingCartController;
import payment.Payment;
import order.Order;
import order.OrderStatus;
import user.User;
import util.DataInitializer;
import util.InputHandler;

public class Main {
    private static List<Restaurant> restaurants;
    private static Scanner scanner;
    private static Order currentOrder;
    
    public static void main(String[] args) {
        AppConfig config = AppConfig.getInstance();
        System.out.println("========================================");
        System.out.println("Welcome to " + config.getAppName());
        System.out.println("Version: " + config.getVersion());
        System.out.println("========================================\n");
        restaurants = DataInitializer.initializeSampleData();
        User defaultUser = new User("Guest User");
        currentOrder = new Order("ORD001", defaultUser);
        scanner = new Scanner(System.in);
        RestaurantMenuController restaurantController = new RestaurantMenuController(restaurants, currentOrder, scanner);
        ShoppingCartController cartController = new ShoppingCartController(currentOrder, scanner);
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = InputHandler.readInt(scanner, "");
            if (choice == -1) {
                continue;
            }
            switch (choice) {
                case 1:
                    restaurantController.browseRestaurants();
                    break;
                case 2:
                    placeOrder();
                    break;
                case 3:
                    cartController.checkShoppingCart();
                    break;
                case 4:
                    checkOrderStatus();
                    break;
                case 5:
                    System.out.println("Thank you for using " + config.getAppName() + "!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
        OrderStatus.shutdown();
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Browse Restaurants");
        System.out.println("2. Place Order");
        System.out.println("3. Check Shopping Cart");
        System.out.println("4. Check Order Status");
        System.out.println("5. Exit");
        System.out.print("\nEnter your choice: ");
    }
    
    private static void placeOrder() {
        if (currentOrder.getItemCount() == 0) {
            System.out.println("\nCart is empty.");
            System.out.println();
            return;
        }
        Payment.displayPaymentWindow(currentOrder, scanner);
    }
    
    private static void checkOrderStatus() {
        boolean viewingStatus = true;
        while (viewingStatus) {
            OrderStatus.displayOrderStatus();
            int choice = InputHandler.readInt(scanner, "");
            if (choice == -1) {
                continue;
            }
            if (choice == 1) {
                viewingStatus = false;
                System.out.println();
            } else {
                System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }
}