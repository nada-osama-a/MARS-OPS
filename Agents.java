public abstract class Agents {
    protected String name;
    protected String id;
    protected String currentLocation;
    private boolean availability;
    private Mission mission;
    private static int timeCounter = 1;

    public Agents(String name, String id, String currentLocation, boolean availability, Mission mission) {
        this.name = name;
        this.id = id;
        this.currentLocation = currentLocation;
        this.availability = availability;
        this.mission = mission;
    }

    public void advanceTime() {
        timeCounter++;

        if (this instanceof Robots) {
            ((Robots) this).setBatteryLevel(timeCounter);
        }
        if (timeCounter < 0) {
            throw new IllegalArgumentException("Time cannot be negative");
        } else if (timeCounter == 0) {
            return;
        } else if (timeCounter > 3) {
            if (this instanceof CrewMember) {
                CrewMember crewMember = (CrewMember) this;
                if (crewMember.getFatigueLevelHuman().equals("LOW")) {
                    crewMember.setFatigueLevelHuman("MEDIUM");
                } else if (crewMember.getFatigueLevelHuman().equals("MEDIUM")) {
                    crewMember.setFatigueLevelHuman("HIGH");
                } else if (crewMember.getFatigueLevelHuman().equals("HIGH")) {
                    crewMember.setHealthStatus(false);
                }
            }
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == "0" || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        this.id = id;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        if (currentLocation == null || currentLocation.trim().isEmpty()) {
            throw new IllegalArgumentException("location cannot be null or empty");
        }
        this.currentLocation = currentLocation;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public abstract boolean canPerform(Mission mission); // 34an n7dd 3la asass anhy mission hn7dd el perform for
                                                         // example
    public abstract boolean canHandleEmergency(Emergency emergency);
   
    public abstract boolean isHuman();

    public abstract boolean isRobot();                                                     
}
