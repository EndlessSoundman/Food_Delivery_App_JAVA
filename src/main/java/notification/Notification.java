package notification;

public class Notification {
    private String message;
    
    public Notification(String message) {
        this.message = message;
    }
    
    public void notifyUser() {
        System.out.println("\n[NOTIFICATION] " + message);
        System.out.print("Enter your choice: ");
    }
    
    public static Notification updateStatus(String orderId, String newStatus) {
        String message = "Order " + orderId + " status updated to: " + newStatus;
        Notification notification = new Notification(message);
        notification.notifyUser();
        return notification;
    }
}