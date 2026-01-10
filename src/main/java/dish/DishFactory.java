package dish;

/**
 * DishFactory - Factory Pattern implementation for creating dish objects
 * Creates appropriate dish type based on the type parameter
 */
public class DishFactory {
    /**
     * Create a dish based on the type specified
     * @param type Type of dish ("VEG", "NON_VEG", "VEGAN")
     * @param name Name of the dish
     * @param price Price of the dish
     * @param description Description of the dish
     * @return Dish object of the specified type
     * @throws IllegalArgumentException if type is unknown
     */
    public static Dish createDish(String type, String name, double price, String description) {
        switch (type.toUpperCase()) {
            case "VEG":
                return new VegDish(name, price, description);
            case "NON_VEG":
                return new NonVegDish(name, price, description);
            case "VEGAN":
                return new VeganDish(name, price, description);
            default:
                throw new IllegalArgumentException("Unknown dish type: " + type);
        }
    }
}
