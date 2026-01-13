package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import order.Order;
import dish.Dish;
import util.InputHandler;
import util.DisplayFormatter;

public class ShoppingCartController {
    private Order currentOrder;
    private Scanner scanner;
    
    public ShoppingCartController(Order currentOrder, Scanner scanner) {
        this.currentOrder = currentOrder;
        this.scanner = scanner;
    }
    
    public void checkShoppingCart() {
        boolean viewingCart = true;
        while (viewingCart) {
            System.out.println("\n\n=== Check Shopping Cart ===");
            
            if (currentOrder.getItemCount() == 0) {
                System.out.println("Your shopping cart is empty.");
                System.out.println("\n1. Return to Main Menu");
                
                int choice = InputHandler.readInt(scanner, "\nEnter your choice: ");
                if (choice == 1) {
                    viewingCart = false;
                    System.out.println();
                } else if (choice != -1) {
                    System.out.println("Invalid choice. Please try again.\n");
                }
            } else {
                System.out.println("\nOrder ID: " + currentOrder.getOrderId());
                System.out.println("Customer: " + currentOrder.getUser().getName());
                System.out.println("\nItems:");
                Map<String, List<Dish>> groupedDishes = currentOrder.getGroupedDishes();
                DisplayFormatter.displayGroupedDishes(groupedDishes);
                double originalTotal = currentOrder.calculateTotal();
                displayDiscountOptions(originalTotal);
                System.out.println("\n1. Remove from shopping cart");
                System.out.println("2. Return to Main Menu");
                int choice = InputHandler.readInt(scanner, "\nEnter your choice: ");
                if (choice == -1) {
                    continue;
                }
                if (choice == 1) {
                    removeFromCart();
                } else if (choice == 2) {
                    viewingCart = false;
                    System.out.println();
                } else {
                    System.out.println("Invalid choice. Please try again.\n");
                }
            }
        }
    }
    
    private void removeFromCart() {
        boolean removing = true;
        while (removing) {
            System.out.println("\n\n=== Remove from Shopping Cart ===");
            
            Map<String, List<Dish>> groupedDishes = currentOrder.getGroupedDishes();
            List<String> dishNames = new ArrayList<>(groupedDishes.keySet());
            
            if (dishNames.isEmpty()) {
                System.out.println("Your shopping cart is empty.");
                removing = false;
            } else {
                for (int i = 0; i < dishNames.size(); i++) {
                    String dishName = dishNames.get(i);
                    List<Dish> dishList = groupedDishes.get(dishName);
                    int quantity = dishList.size();
                    double unitPrice = dishList.get(0).getPrice();
                    double totalPrice = unitPrice * quantity;
                    if (quantity > 1) {
                        System.out.println((i + 1) + ". " + dishName + " x" + quantity + 
                                         " - $" + DisplayFormatter.formatPrice(totalPrice));
                    } else {
                        System.out.println((i + 1) + ". " + dishName + " - $" + DisplayFormatter.formatPrice(totalPrice));
                    }
                }
                int removeAllOption = dishNames.size() + 1;
                int returnOption = dishNames.size() + 2;
                System.out.println(removeAllOption + ". Remove All Items");
                System.out.println(returnOption + ". Return to previous page");
                int choice = InputHandler.readInt(scanner, "\nEnter your choice: ");
                if (choice == -1) {
                    continue;
                }
                if (choice >= 1 && choice <= dishNames.size()) {
                    String selectedDishName = dishNames.get(choice - 1);
                    int quantity = currentOrder.getDishQuantity(selectedDishName);
                    if (quantity > 1) {
                        System.out.print("\nEnter quantity to remove (max " + quantity + ", or " + quantity + "+ to remove all): ");
                        try {
                            int removeQuantity = scanner.nextInt();
                            scanner.nextLine();
                            if (removeQuantity > quantity) {
                                removeQuantity = quantity;
                            }
                            int removedCount = currentOrder.removeDishQuantity(selectedDishName, removeQuantity);
                            if (removedCount > 0) {
                                System.out.println("\n✓ Removed " + removedCount + "x \"" + selectedDishName + 
                                                 "\" from your order.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a number.\n");
                            scanner.nextLine();
                        }
                    } else {
                        int removedCount = currentOrder.removeDishQuantity(selectedDishName, 1);
                        if (removedCount > 0) {
                            System.out.println("\n✓ Removed \"" + selectedDishName + "\" from your order.");
                        }
                    }
                    if (currentOrder.getItemCount() == 0) {
                        removing = false;
                    }
                } else if (choice == removeAllOption) {
                    System.out.print("\nAre you sure you want to remove all items? (yes/no): ");
                    String confirm = scanner.nextLine().trim().toLowerCase();
                    if (confirm.equals("yes") || confirm.equals("y")) {
                        currentOrder.clearOrder();
                        System.out.println("\n✓ All items removed from your order.");
                        removing = false;
                    } else {
                        System.out.println("\nOperation cancelled.");
                    }
                } else if (choice == returnOption) {
                    removing = false;
                } else {
                    System.out.println("Invalid choice. Please try again.\n");
                }
            }
        }
    }
    
    private void displayDiscountOptions(double originalTotal) {
        System.out.println("\n--- Pricing Options ---");
        double priceNoDiscount = originalTotal;
        System.out.println("Price (No Discount): $" + DisplayFormatter.formatPrice(priceNoDiscount));
        double pricePercentDiscount = calculatePercentageDiscount(originalTotal, 10.0);
        System.out.println("Price (10% Discount): $" + DisplayFormatter.formatPrice(pricePercentDiscount));
        double priceFlatDiscount = calculateFlatDiscount(originalTotal, 2.0);
        System.out.println("Price ($2.00 Flat Discount): $" + DisplayFormatter.formatPrice(priceFlatDiscount));
    }
    
    private double calculatePercentageDiscount(double originalPrice, double percentage) {
        double discountAmount = originalPrice * percentage / 100;
        return originalPrice - discountAmount;
    }
    
    private double calculateFlatDiscount(double originalPrice, double discountAmount) {
        double discountedPrice = originalPrice - discountAmount;
        return discountedPrice < 0 ? 0 : discountedPrice;
    }
}