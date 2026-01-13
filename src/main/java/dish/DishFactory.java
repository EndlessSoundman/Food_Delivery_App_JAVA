package dish;

public class DishFactory {
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