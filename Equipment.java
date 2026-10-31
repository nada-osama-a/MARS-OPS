public class Equipment {

private final String name;
private final EquipmentType type;
private boolean operational;

public Equipment(String name, EquipmentType type, boolean operational) {
    if (name == null || name.trim().isEmpty()) {
        throw new IllegalArgumentException("Name cannot be null or blank");
    }

    if (type == null) {
        throw new IllegalArgumentException("Type cannot be null");
    }

    this.name = name;
    this.type = type;
    this.operational = operational;
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

@Override
public String toString() {
    return "Equipment{name='" + name + '\''
            + ", type=" + type
            + ", operational=" + operational + '}';
}
}
