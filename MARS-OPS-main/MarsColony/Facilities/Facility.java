package MarsColony.Facilities;
import java.util.ArrayList;

public abstract class Facility {

    private String name;
    private final int id;
    private int capacity;
    private String location;
    private boolean isOperational;
    private int unitConsumption = 1;

    private final ArrayList<Resource> resources = new ArrayList<>();

    public Facility(String name, int id, int capacity, String location, boolean isOperational) {

        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }

        this.id = id;
        setName(name);
        setCapacity(capacity);
        setLocation(location);
        setOperational(isOperational);
    }

    public void advanceTime() {
        if (!isOperational) {
            return;
        }
        
        for (Resource resource : resources) {
            if (resource.getQuantity() >= unitConsumption) {
                resource.removeQuantity(unitConsumption);
            } else {
                resource.removeQuantity(resource.getQuantity());
            }
        }
    }

    public boolean providesCommunication() {
        return false;
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }

        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public final void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }

        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public final void setLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or blank");
        }

        this.location = location;
    }

    public boolean isOperational() {
        return isOperational;
    }

    public final void setOperational(boolean isOperational) {
        this.isOperational = isOperational;
    }

    // public List<Equipment> getEquipment() {
    //     return new ArrayList<>(equipment);
    // }

    // public void addEquipment(Equipment item) {
    //     if (item == null) {
    //         throw new IllegalArgumentException("Equipment cannot be null");
    //     }

    //     equipment.add(item);
    // }

    // public boolean removeEquipment(Equipment item) {
    //     return equipment.remove(item);
    // }

    // public ArrayList<Resource> getResources() {
    //     ArrayList<Resource> copies = new ArrayList<>();

    //     for (Resource resource : resources) {
    //         copies.add(new Resource(resource.getType(), resource.getQuantity()));
    //     }

    //     return copies;
    // }

    public void addResource(Resource resource) {
        if (resource == null) {
            throw new IllegalArgumentException("Resource cannot be null");
        }

        for (Resource current : resources) {
            if (current.getType() == resource.getType()) {
                current.addQuantity(resource.getQuantity());
                return;
            }
        }

        resources.add(new Resource(resource.getType(), resource.getQuantity()));
    }

    public int getResourceQuantity(ResourceType type) {
        if (type == null) {
            throw new IllegalArgumentException("Resource type cannot be null");
        }

        for (Resource resource : resources) {
            if (resource.getType() == type) {
                return resource.getQuantity();
            }
        }

        return 0;
    }


    public boolean removeResourceQuantity(ResourceType type, int amount) {
        if (type == null) {
            throw new IllegalArgumentException("Resource type cannot be null");
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        if (getResourceQuantity(type) < amount) {
            return false;
        }

        for (Resource resource : resources) {
            if (resource.getType() == type) {
                resource.removeQuantity(amount);
                return true;
            }
        }

        return amount == 0;
    }

    @Override
    public String toString() {
        return "Facility{name='" + name + '\''
                + ", id=" + id
                + ", capacity=" + capacity
                + ", location='" + location + '\''
                + ", operational=" + isOperational
                //+ ", equipment=" + equipment
                + ", resources=" + resources + '}';
    }
}