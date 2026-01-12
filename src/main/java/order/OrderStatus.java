package order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import dish.Dish;
import util.DisplayFormatter;
import notification.Notification;

/**
 * OrderStatus class - Manages and displays order status information
 * Tracks multiple orders and their statuses with automatic status updates
 */
public class OrderStatus {
    private static List<Order> placedOrders = new ArrayList<>();
    private static ScheduledExecutorService statusUpdateExecutor = Executors.newScheduledThreadPool(5);
    private static Random random = new Random();
    
    /**
     * Add an order to the list of placed orders and start status update process
     * @param order The order to add
     */
    public static void addOrder(Order order) {
        if (order != null) {
            order.setStatus("PLACED");
            placedOrders.add(order);
            // Start automatic status updates for this order
            scheduleStatusUpdates(order);
        }
    }
    
    /**
     * Schedule automatic status updates for an order
     * @param order The order to update
     */
    private static void scheduleStatusUpdates(Order order) {
        String orderId = order.getOrderId();
        
        // PLACED → PREPARING: 10-20 seconds
        int placedToPreparingDelay = 10 + random.nextInt(11); // 10-20 seconds
        statusUpdateExecutor.schedule(() -> {
            if (order.getStatus().equals("PLACED")) {
                order.setStatus("PREPARING");
                Notification.updateStatus(orderId, "PREPARING");
                
                // PREPARING → OUT FOR DELIVERY: 30-60 seconds
                int preparingToOutForDeliveryDelay = 30 + random.nextInt(31); // 30-60 seconds
                statusUpdateExecutor.schedule(() -> {
                    if (order.getStatus().equals("PREPARING")) {
                        order.setStatus("OUT FOR DELIVERY");
                        Notification.updateStatus(orderId, "OUT FOR DELIVERY");
                        
                        // OUT FOR DELIVERY → DELIVERED: 30-40 seconds
                        int outForDeliveryToDeliveredDelay = 30 + random.nextInt(11); // 30-40 seconds
                        statusUpdateExecutor.schedule(() -> {
                            if (order.getStatus().equals("OUT FOR DELIVERY")) {
                                order.setStatus("DELIVERED");
                                Notification.updateStatus(orderId, "DELIVERED");
                            }
                        }, outForDeliveryToDeliveredDelay, TimeUnit.SECONDS);
                    }
                }, preparingToOutForDeliveryDelay, TimeUnit.SECONDS);
            }
        }, placedToPreparingDelay, TimeUnit.SECONDS);
    }
    
    /**
     * Get all placed orders
     * @return List of all placed orders
     */
    public static List<Order> getPlacedOrders() {
        return new ArrayList<>(placedOrders); // Return a copy to prevent external modification
    }
    
    /**
     * Display all orders with their status
     */
    public static void displayOrderStatus() {
        if (placedOrders.isEmpty()) {
            System.out.println("\n\n=== Check Order Status ===");
            System.out.println("\nNo orders have been placed yet.");
            System.out.println("\n1. Return to Main Menu");
            System.out.print("\nEnter your choice: ");
            return;
        }
        
        System.out.println("\n\n=== Check Order Status ===");
        
        for (int i = 0; i < placedOrders.size(); i++) {
            Order order = placedOrders.get(i);
            
            // Order header
            System.out.println("\n--- Order #" + (i + 1) + " ---");
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Customer: " + order.getUser().getName());
            
            // Display dishes with prices
            System.out.println("\nDishes:");
            Map<String, List<Dish>> groupedDishes = order.getGroupedDishes();
            DisplayFormatter.displayGroupedDishes(groupedDishes);
            
            // Final price
            System.out.println("\nFinal Price: $" + DisplayFormatter.formatPrice(order.calculateTotal()));
            
            // Status (placed between dishes/price and options)
            System.out.println("\nStatus: " + order.getStatus());
            
            // Add spacing between orders if not the last one
            if (i < placedOrders.size() - 1) {
                System.out.println();
            }
        }
        
        System.out.println("\n1. Return to Main Menu");
        System.out.print("\nEnter your choice: ");
    }
    
    /**
     * Shutdown the status update executor (call when application closes)
     */
    public static void shutdown() {
        if (statusUpdateExecutor != null && !statusUpdateExecutor.isShutdown()) {
            statusUpdateExecutor.shutdown();
        }
    }
}
