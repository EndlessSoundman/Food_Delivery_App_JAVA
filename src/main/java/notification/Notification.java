package notification;

import java.util.Date;

/**
 * Notification class - Represents a notification for order status updates
 */
public class Notification {
    private String message;
    private Date timestamp;
    
    /**
     * Constructor to create a new Notification
     * @param message The notification message
     */
    public Notification(String message) {
        this.message = message;
        this.timestamp = new Date();
    }
    
    /**
     * Get the notification message
     * @return The message
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Get the timestamp when the notification was created
     * @return The timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }
    
    /**
     * Send notification to the user
     */
    public void sendNotification() {
        // Notification is sent when created, this method is for future extension
        notifyUser();
    }
    
    /**
     * Notify the user about the status update
     */
    public void notifyUser() {
        // Display notification below the current window without resetting it
        System.out.println("\n[NOTIFICATION] " + message);
        // Reprint the prompt below the notification so user can continue entering their choice
        System.out.print("Enter your choice: ");
    }
    
    /**
     * Update the status of an order
     * @param orderId The order ID
     * @param newStatus The new status
     * @return Notification object for the status update
     */
    public static Notification updateStatus(String orderId, String newStatus) {
        String message = "Order " + orderId + " status updated to: " + newStatus;
        Notification notification = new Notification(message);
        notification.notifyUser();
        return notification;
    }
}
