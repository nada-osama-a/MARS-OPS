public class Equipment {

    private final String name;
    private final EquipmentType type;
    private boolean operational = true;

    public Equipment(String name, EquipmentType type) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        this.name = name;

        if (type == null) {
            throw new IllegalArgumentException("Equipment type cannot be null");
        }
        this.type = type;

    }

    public String getName() {
        return name;
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

    public void damage() {
        this.operational = false;
    }

    public void repair() {
        this.operational = true;
    }

    @Override
    public String toString() {
        return "Equipment{name='" + name + '\''
                + ", type=" + type
                + ", operational=" + operational + '}';
    }
}
