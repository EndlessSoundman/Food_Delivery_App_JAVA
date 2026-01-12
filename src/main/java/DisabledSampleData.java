import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import restaurant.Restaurant;
import dish.DishFactory;
import dish.Dish;
import dish.CheeseDecorator;
import dish.ExtraSauceDecorator;
import discount.DiscountStrategy;
import discount.PercentageDiscount;
import discount.FlatDiscount;
import discount.NoDiscount;
import payment.PaymentStrategy;
import payment.CreditCardPayment;
import payment.PayPalPayment;
import payment.CashOnDelivery;
import order.OrderStatus;
import util.InputHandler;

/**
 * DISABLED - Sample Data Initialization and Demonstration Code
 * This file contains disabled code for initializing sample data and demonstrating
 * design patterns (Decorator, Strategy). Keep disabled for now.
 */
public class DisabledSampleData {
    private static List<Restaurant> restaurants;
    private static Scanner scanner;
    
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
        
        // Demonstrate Payment and Discount Strategies
        demonstratePaymentAndDiscountStrategies();
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

    /**
     * Demonstrate Payment and Discount Strategy Patterns
     * Shows how different payment methods and discount strategies can be used
     */
    private static void demonstratePaymentAndDiscountStrategies() {
        System.out.println("=== Payment & Discount Strategy Demo ===\n");
        
        // Create an example order with a dish
        Dish orderDish = DishFactory.createDish("NON_VEG", "Pepperoni Pizza", 12.99, "Classic pepperoni pizza");
        double orderTotal = orderDish.getPrice();
        System.out.println("Order Item: " + orderDish.getName());
        System.out.println("Original Price: $" + String.format("%.2f", orderTotal));
        System.out.println();
        
        // Test all 3 discount strategies
        System.out.println("--- Testing Discount Strategies ---");
        
        // Percentage Discount
        DiscountStrategy percentDiscount = new PercentageDiscount(10);
        double discountedPrice1 = percentDiscount.applyDiscount(orderTotal);
        System.out.println("Price after discount: $" + String.format("%.2f", discountedPrice1));
        System.out.println();
        
        // Flat Discount
        DiscountStrategy flatDiscount = new FlatDiscount(2.0);
        double discountedPrice2 = flatDiscount.applyDiscount(orderTotal);
        System.out.println("Price after discount: $" + String.format("%.2f", discountedPrice2));
        System.out.println();
        
        // No Discount
        DiscountStrategy noDiscount = new NoDiscount();
        double discountedPrice3 = noDiscount.applyDiscount(orderTotal);
        System.out.println("Price after discount: $" + String.format("%.2f", discountedPrice3));
        System.out.println();
        
        // Test all 3 payment methods
        System.out.println("--- Testing Payment Strategies ---");
        
        // Credit Card Payment
        PaymentStrategy creditCard = new CreditCardPayment("1234-5678-9012-3456", "John Doe");
        System.out.print("Payment method: Credit Card - ");
        creditCard.processPayment(discountedPrice1);
        System.out.println();
        
        // PayPal Payment
        PaymentStrategy paypal = new PayPalPayment("john.doe@example.com");
        System.out.print("Payment method: PayPal - ");
        paypal.processPayment(discountedPrice2);
        System.out.println();
        
        // Cash on Delivery
        PaymentStrategy cashOnDelivery = new CashOnDelivery();
        System.out.print("Payment method: Cash on Delivery - ");
        cashOnDelivery.processPayment(discountedPrice3);
        System.out.println();
        
        // Show complete flow: Original price → Apply discount → Process payment
        System.out.println("--- Complete Order Flow Example ---");
        double originalPrice = 50.0;
        System.out.println("Step 1 - Original Order Total: $" + String.format("%.2f", originalPrice));
        
        DiscountStrategy discount = new PercentageDiscount(15);
        double finalPrice = discount.applyDiscount(originalPrice);
        System.out.println("Step 2 - After 15% discount: $" + String.format("%.2f", finalPrice));
        
        PaymentStrategy payment = new CreditCardPayment("9876-5432-1098-7654", "Jane Smith");
        System.out.print("Step 3 - Payment: ");
        payment.processPayment(finalPrice);
        System.out.println();
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
