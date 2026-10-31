public abstract class Agents {
    protected String name;
    protected String id;
    protected String currentLocation;
    private boolean availability;
    private String mission;
    
    public Agents(String name, String id, String currentLocation, boolean availability, String mission) {
        this.name = name;
        this.id = id;
        this.currentLocation = currentLocation;
        this.availability = availability;
        this.mission = mission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public abstract boolean canPerform(String mission); // 34an n7dd 3la asass anhy mission hn7dd el perform for example
}
