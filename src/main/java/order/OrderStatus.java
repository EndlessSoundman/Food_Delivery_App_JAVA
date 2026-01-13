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

public class OrderStatus {
    private static List<Order> placedOrders = new ArrayList<>();
    private static ScheduledExecutorService statusUpdateExecutor = Executors.newScheduledThreadPool(5);
    private static Random random = new Random();
    
    public static void addOrder(Order order) {
        if (order != null) {
            order.setStatus("PLACED");
            placedOrders.add(order);
            scheduleStatusUpdates(order);
        }
    }
    
    private static void scheduleStatusUpdates(Order order) {
        String orderId = order.getOrderId();
        int placedToPreparingDelay = 10 + random.nextInt(11);
        statusUpdateExecutor.schedule(() -> {
            if (order.getStatus().equals("PLACED")) {
                order.setStatus("PREPARING");
                Notification.updateStatus(orderId, "PREPARING");
                int preparingToOutForDeliveryDelay = 30 + random.nextInt(31);
                statusUpdateExecutor.schedule(() -> {
                    if (order.getStatus().equals("PREPARING")) {
                        order.setStatus("OUT FOR DELIVERY");
                        Notification.updateStatus(orderId, "OUT FOR DELIVERY");
                        int outForDeliveryToDeliveredDelay = 30 + random.nextInt(11);
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
            System.out.println("\n--- Order #" + (i + 1) + " ---");
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Customer: " + order.getUser().getName());
            System.out.println("\nDishes:");
            Map<String, List<Dish>> groupedDishes = order.getGroupedDishes();
            DisplayFormatter.displayGroupedDishes(groupedDishes);
            System.out.println("\nFinal Price: $" + DisplayFormatter.formatPrice(order.calculateTotal()));
            System.out.println("\nStatus: " + order.getStatus());
            if (i < placedOrders.size() - 1) {
                System.out.println();
            }
        }
        System.out.println("\n1. Return to Main Menu");
        System.out.print("\nEnter your choice: ");
    }
    
    public static void shutdown() {
        if (statusUpdateExecutor != null && !statusUpdateExecutor.isShutdown()) {
            statusUpdateExecutor.shutdown();
        }
    }
}
