package config;

/**
 * AppConfig - Singleton pattern implementation
 * Manages application-wide configuration settings
 */
public class AppConfig {
    // Private static instance variable
    private static AppConfig instance;
    
    // Configuration fields
    private String appName;
    private String version;
    
    /**
     * Private constructor to prevent instantiation
     */
    private AppConfig() {
        this.appName = "Food Delivery App";
        this.version = "1.0";
    }
    
    /**
     * Public static method to get the singleton instance
     * @return The single instance of AppConfig
     */
    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }
    
    /**
     * Getter for app name
     * @return The application name
     */
    public String getAppName() {
        return appName;
    }
    
    /**
     * Getter for version
     * @return The application version
     */
    public String getVersion() {
        return version;
    }
}
