import java.util.ArrayList;

public abstract class Facility {
private String name;
private int id;
private int capacity;
private String location;
private boolean isOperational;
private String[] equipment;
private ArrayList<Resource> resources = new ArrayList<>();

public String[] getEquipment() {
    return equipment;
}

public void setEquipment(String[] equipment) {
   if (equipment == null) {
        throw new IllegalArgumentException("Equipment cannot be null");
    }
    this.equipment = equipment;
}

public String getName() {
    return name;
}

public void setName(String name) {
    if (name == null || name.trim().isEmpty()) {
        throw new IllegalArgumentException("Name cannot be null or blank");
    }
    this.name = name;
}

public int getId() {
    return id;
}

public void setId(int id) {
    if (id < 0) {
        throw new IllegalArgumentException("ID cannot be negative");
    }
    this.id = id;
}

public int getCapacity() {
    return capacity;
}

public void setCapacity(int capacity) {
    if (capacity < 0) {
        throw new IllegalArgumentException("Capacity cannot be negative");
    }
    this.capacity = capacity;
}

public String getLocation() {
    return location;
}

public void setLocation(String location) {
    if (location == null || location.trim().isEmpty()) {
        throw new IllegalArgumentException("Location cannot be null or blank");
    }
    this.location = location;
}

public boolean isOperational() {
    return isOperational;
}

public void setOperational(boolean isOperational) {
    this.isOperational = isOperational;
}

public ArrayList<Resource> getResources() {
    return new ArrayList<>(resources);
}

public Facility(String name, int id, int capacity, String location, boolean isOperational ,String[] equipment) {
    setName(name);
    setId(id);
    setCapacity(capacity);
    setLocation(location);
    setOperational(isOperational);
    setEquipment(equipment);
}

}
