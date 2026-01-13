package order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import user.User;
import dish.Dish;
import restaurant.Restaurant;

public class Order {
    private String orderId;
    private User user;
    private List<Dish> dishes;
    private Map<Dish, Restaurant> dishRestaurantMap;
    private String status;
    
    public Order(String orderId, User user) {
        this.orderId = orderId;
        this.user = user;
        this.dishes = new ArrayList<>();
        this.dishRestaurantMap = new HashMap<>();
        this.status = "PENDING";
    }
    
    public void addDish(Dish dish, Restaurant restaurant) {
        dishes.add(dish);
        dishRestaurantMap.put(dish, restaurant);
    }
    
    public void addDish() {
        System.out.println("Please use addDish(Dish dish, Restaurant restaurant) to add dishes with restaurant information.");
    }
    
    public double calculateTotal() {
        double total = 0.0;
        for (Dish dish : dishes) {
            total += dish.getPrice();
        }
        return total;
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public User getUser() {
        return user;
    }
    
    public List<Dish> getDishes() {
        return new ArrayList<>(dishes);
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getItemCount() {
        return dishes.size();
    }
    
    public Restaurant getRestaurantForDish(Dish dish) {
        return dishRestaurantMap.get(dish);
    }
    
    public Map<String, List<Dish>> getGroupedDishes() {
        Map<String, List<Dish>> grouped = new HashMap<>();
        for (Dish dish : dishes) {
            String name = dish.getName();
            grouped.putIfAbsent(name, new ArrayList<>());
            grouped.get(name).add(dish);
        }
        return grouped;
    }
    
    public int getDishQuantity(String dishName) {
        int count = 0;
        for (Dish dish : dishes) {
            if (dish.getName().equals(dishName)) {
                count++;
            }
        }
        return count;
    }
    
    public int removeDishQuantity(String dishName, int quantity) {
        int removedCount = 0;
        List<Dish> toRemove = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.getName().equals(dishName) && removedCount < quantity) {
                toRemove.add(dish);
                removedCount++;
            }
        }
        for (Dish dish : toRemove) {
            dishes.remove(dish);
            dishRestaurantMap.remove(dish);
        }
        return removedCount;
    }
    
    public void clearOrder() {
        dishes.clear();
        dishRestaurantMap.clear();
        setStatus("PENDING");
    }
}