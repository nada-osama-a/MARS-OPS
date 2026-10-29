public class Resource {
    private String type ;
    private int quantity ;
    public String getType() {
        return type;
    }
    public void setType(String type) {
       if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or blank");
        }
        this.type = type;
    }
    public int getQuantity() {

        return quantity;
    }
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }
    public Resource(String type, int quantity) {
        setType(type) ;
        setQuantity(quantity) ;
    } 
    
    public void addQuantity(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to add cannot be negative");
        }
        this.quantity += amount;
    }
    public void removeQuantity(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to remove cannot be negative");
        }
        if (amount > this.quantity) {
            throw new IllegalArgumentException("Cannot remove more than available quantity");
        }
        this.quantity -= amount;
    }
    
}
