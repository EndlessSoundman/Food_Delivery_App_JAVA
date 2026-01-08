package user;

/**
 * User class - Represents a user in the food delivery system
 */
public class User {
    private String userId;
    private String name;
    private String email;
    
    /**
     * Constructor to create a new User
     * @param userId Unique identifier for the user
     * @param name User's name
     * @param email User's email address
     */
    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
    
    /**
     * Getter for user ID
     * @return The user ID
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * Getter for name
     * @return The user's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Getter for email
     * @return The user's email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Place an order (placeholder implementation)
     */
    public void placeOrder() {
        System.out.println("Order placed by " + name);
    }
}
