public class ExplorationRover extends Robots {

    public ExplorationRover(String name, String id, int batteryLevel, boolean healthDamageStatus,
            String currentLocation, boolean availability, Mission mission) {

        super(name, id, batteryLevel, healthDamageStatus, currentLocation, availability, mission);
    }

    @Override
    public void capabilities() {
        System.out.println(getName() + " travels across the surface, carries equipment, and performs exploration.");
    }

    @Override
    public boolean canPerform(Mission mission) {
        if (mission instanceof ExplorationMission)

            return isAvailable() && !isHealthDamageStatus() && getBatteryLevel() > 20;
        else
            return false;

    }
}