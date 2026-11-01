package MarsColony.Facilities;
public class Resource {

    private final ResourceType type;
    private int quantity;

    public Resource(ResourceType type, int quantity) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        this.type = type;
        this.quantity = quantity;
    }

    public ResourceType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to add cannot be negative");
        }

        if (amount > Integer.MAX_VALUE - quantity) {
            throw new IllegalArgumentException("Quantity exceeds maximum allowed value");
        }

        quantity += amount;
    }

    public void removeQuantity(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to remove cannot be negative");
        }

        if (amount > quantity) {
            throw new IllegalArgumentException("Cannot remove more than available quantity");
        }

        quantity -= amount;
    }

    @Override
    public String toString() {
        return "Resource{type=" + type + ", quantity=" + quantity + "}";
    }
}