public class Equipment {

    private final String name;
    private final String type;
    private boolean operational;

    public Equipment(String name, String type, boolean operational) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }

        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or blank");
        }

        this.name = name;
        this.type = type;
        this.operational = operational;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isOperational() {
        return operational;
    }

    public void setOperational(boolean operational) {
        this.operational = operational;
    }

    @Override
    public String toString() {
        return "Equipment{name='" + name + '\''
                + ", type='" + type + '\''
                + ", operational=" + operational + '}';
    }
}