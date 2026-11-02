package MarsColony.Facilities;

public class Equipment {

    // private final String name;
    private final EquipmentType type;
    private boolean operational = true;
    private boolean available = true;

    public Equipment(EquipmentType type) {

        if (type == null) {
            throw new IllegalArgumentException("Equipment type cannot be null");
        }
        this.type = type;

    }

    public EquipmentType getType() {
        return type;
    }

    public boolean isOperational() {
        return operational;
    }

    public void setOperational(boolean operational) {
        this.operational = operational;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void damage() {
        this.operational = false;
    }

    public void repair() {
        this.operational = true;
    }

    @Override
    public String toString() {
        return "Equipment{" + "type=" + type + ", operational=" + operational + '}';

    }
}